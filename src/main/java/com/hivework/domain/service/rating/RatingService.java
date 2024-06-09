package com.hivework.domain.service.rating;

import com.hivework.domain.dto.request.RatingRequestDto;
import com.hivework.domain.entity.rating.Rating;
import com.hivework.domain.entity.rating.RatingRepository;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;

    public RatingService(RatingRepository ratingRepository, UserService userService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    public Rating findById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public Page<Rating> findByUserTo(Long id, Pageable pageable) {
        return ratingRepository.findByUserTo_Id(id, pageable);
    }

    public Page<Rating> findByUserFrom(Long id, Pageable pageable) {
        return ratingRepository.findByUserFrom_Id(id, pageable);
    }

    public Rating create(RatingRequestDto ratingRequestDto, Long userToId) {
        Users users = userService.findById(userToId);
        Users usersFrom = userService.getCurrentUser();

        Rating rating = new Rating();

        rating.setDescription(ratingRequestDto.getDescription());
        rating.setGrade(ratingRequestDto.getGrade());
        rating.setTitle(ratingRequestDto.getTitle());
        rating.setUserTo(users);
        rating.setUserFrom(usersFrom);

        return ratingRepository.save(rating);
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }
}
