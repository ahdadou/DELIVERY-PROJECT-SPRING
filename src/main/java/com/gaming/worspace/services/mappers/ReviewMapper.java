package com.gaming.worspace.services.mappers;

import com.gaming.worspace.models.Review;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.response.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") //Creates a Spring Bean automatically
public interface ReviewMapper {


    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    List<ReviewResponse>  toReviewReponse(List<Review> reviews);


}
