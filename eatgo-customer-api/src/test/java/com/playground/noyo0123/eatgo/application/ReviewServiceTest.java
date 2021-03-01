package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.application.ReviewService;
import com.playground.noyo0123.eatgo.domain.Review;
import com.playground.noyo0123.eatgo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ReviewServiceTest {


    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReivew() {
        reviewService.addReview(1004L, "JOKER", 3, "Mat-it-da");

        verify(reviewRepository).save(any());
    }
}