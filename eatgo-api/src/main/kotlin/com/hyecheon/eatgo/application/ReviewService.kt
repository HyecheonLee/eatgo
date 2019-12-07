package com.hyecheon.eatgo.application

import com.hyecheon.eatgo.domain.Review
import com.hyecheon.eatgo.domain.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(
		private val reviewRepository: ReviewRepository
) {
	fun addReview(restaurantId: Long, review: Review): Review {
		review.restaurantId = restaurantId
		return reviewRepository.save(review)
	}
}