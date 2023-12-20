package edu.neu.csye6200.dao;


import java.util.List;

import edu.neu.csye6200.model.Review;

public interface ReviewDao {
    List<Review> getAllReviews();

    Review getReviewById(int id);

    void addReview(Review review);

    void updateReview(Review review);

    void deleteReview(int id);
}
