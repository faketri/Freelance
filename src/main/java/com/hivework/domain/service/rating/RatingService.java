package com.hivework.domain.service.rating;

import com.hivework.domain.entity.rating.Rating;
import com.hivework.domain.entity.rating.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating findById(Long id){
        return ratingRepository.findById(id).orElse(null);
    }
}
