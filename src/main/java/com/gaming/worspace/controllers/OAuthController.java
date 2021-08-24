package com.gaming.worspace.controllers;

import com.gaming.worspace.models.CustomUserDetails;
import com.gaming.worspace.models.Role;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.TokenDto;
import com.gaming.worspace.models.enumerated.Gender;
import com.gaming.worspace.services.RoleService;
import com.gaming.worspace.services.UserServices;
import com.gaming.worspace.services.security.JwtTokenProvider;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/oauth")
@CrossOrigin("*")
public class OAuthController {


    //    @Value("${app.google.clientId}")
    String googleClientId="516206682835-o88khmnnc4h7q5j80j16s52hdllmo83f.apps.googleusercontent.com";

    //    @Value("${app.secretPsw}")
    String secretPsw="password";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtProvider;

    @Autowired
    UserServices usuarioService;

    @Autowired
    RoleService rolService;


    @PostMapping("/google")
    public ResponseEntity<TokenDto> google(@RequestBody TokenDto tokenDto) throws IOException {
        final NetHttpTransport transport = new NetHttpTransport();
        final GsonFactory jacksonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier =
                new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        User usuario = new User();
        if(usuarioService.existsByEmail(payload.getEmail()))
        {
            usuario = usuarioService.getUserByEmail(payload.getEmail());
        }
        else
        {
            usuario = googlePaylodSave(payload);
        }
        TokenDto tokenRes = login(usuario);
        return new ResponseEntity<TokenDto>(tokenRes, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "hello test";
    }






    @PostMapping("/facebook")
    public ResponseEntity<TokenDto> facebook(@RequestBody TokenDto tokenDto) throws IOException {
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        final String [] fields = {"email", "picture","name","gender","location"};
        org.springframework.social.facebook.api.User user = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        System.out.println("-------------------FACEBOOK LOGIN ------------------ : => ");

        System.out.println(user.getEmail());
        System.out.println(user.getName());
        System.out.println(user.getGender());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getLocation());
        System.out.println(user.getCover());

        System.out.println(user.getEmail());

        User usuario = new User();
        if(usuarioService.existsByEmail(user.getEmail()))
            usuario = usuarioService.getUserByEmail(user.getEmail());
        else
            usuario = saveUsuario(user.getEmail());
        TokenDto tokenRes = login(usuario);
        return new ResponseEntity(tokenRes, HttpStatus.OK);
    }



    private TokenDto login(User usuario){
        System.out.println("-------------------LOGIN------------");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getEmail(), secretPsw)
        );
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(customUserDetails);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setValue(jwt);
        tokenDto.setImage(usuario.getImage());
        tokenDto.setEmail(customUserDetails.getEmail());
        tokenDto.setFirstname(customUserDetails.getFirstname());
        tokenDto.setLastname(customUserDetails.getLastname());

        return tokenDto;
    }



    private User saveUsuario(String email){
        User usuario = new User();
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(secretPsw));
        Set<Role> roles= new HashSet<>(rolService.findAll());
        roles.removeIf(Role::isAdminRole);
        usuario.setRoles(roles);
        usuario.setActive(true);
        usuario.setEmailVerified(true);

        return usuarioService.save(usuario);
    }


//    Google Payload To User

    public User googlePaylodSave(GoogleIdToken.Payload payload){
        User user = new User();
        user.setImage(payload.get("picture").toString());
        user.setEmail(payload.getEmail());
        user.setPassword(passwordEncoder.encode(secretPsw));
        Set<Role> roles= new HashSet<>(rolService.findAll());
        roles.removeIf(Role::isAdminRole);
        user.setRoles(roles);
        user.setFirstname(payload.get("given_name").toString());
        user.setLastname(payload.get("family_name").toString());
        user.setGender(Gender.MALE);

        user.setActive(true);
        user.setEmailVerified(true);
        return usuarioService.save(user);
    }


}
