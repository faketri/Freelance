package com.hivework.domain.service.rating;

import com.hivework.domain.entity.rating.Rating;
import com.hivework.domain.entity.rating.RatingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Rating> findByUserTo(Long id, Pageable pageable){
        return ratingRepository.findByUserTo_Id(id, pageable);
    }

    public Page<Rating> findByUserFrom(Long id, Pageable pageable){
        return ratingRepository.findByUserFrom_Id(id, pageable);
    }

    public Rating save(Rating rating){
        return ratingRepository.save(rating);
    }
}
