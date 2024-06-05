package com.hivework.domain.controller;

import com.hivework.domain.dto.response.RatingResponseDto;
import com.hivework.domain.entity.rating.Rating;
import com.hivework.domain.mapper.RatingMapper;
import com.hivework.domain.service.rating.RatingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping(path = "/api/v1/rating", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/{id}")
    public RatingResponseDto findById(@PathVariable("id") Long id){
        return RatingMapper.toResponse(ratingService.findById(id));
    }

    @RequestMapping("/user/{id}")
    public Page<RatingResponseDto> findByUserTo(@PathVariable("id") Long id,
                                     final @RequestParam(name = "number", required = true, defaultValue = "0") Integer pageNumber,
                                     final @RequestParam(name = "size", required = true, defaultValue = "20") Integer pageSize){
        return ratingService.findByUserTo(id, PageRequest.of(pageNumber, pageSize)).map(RatingMapper::toResponse);
    }

    @RequestMapping("/user/from/{id}")
    public Page<RatingResponseDto> findByUserFrom(@PathVariable("id") Long id,
                                     final @RequestParam(name = "number", required = true, defaultValue = "0") Integer pageNumber,
                                     final @RequestParam(name = "size", required = true, defaultValue = "20") Integer pageSize){
        return ratingService.findByUserFrom(id, PageRequest.of(pageNumber, pageSize)).map(RatingMapper::toResponse);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RatingResponseDto save(@RequestBody Rating rating){
        return RatingMapper.toResponse(ratingService.save(rating));
    }
}
