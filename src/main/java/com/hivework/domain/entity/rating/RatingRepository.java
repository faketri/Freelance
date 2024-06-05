package com.hivework.domain.entity.rating;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {


    Page<Rating> findByUserTo_Id(Long id, Pageable pageable);

    Page<Rating> findByUserFrom_Id(Long id, Pageable pageable);
}