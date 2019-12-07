package com.hyecheon.eatgo.application

import com.hyecheon.eatgo.domain.Review
import com.hyecheon.eatgo.domain.ReviewRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class ReviewServiceTest {

	lateinit var reviewService: ReviewService

	@Mock
	lateinit var reviewRepository: ReviewRepository
	private lateinit var review: Review
	@BeforeEach
	internal fun setUp() {
		MockitoAnnotations.initMocks(this)
		reviewService = ReviewService(reviewRepository)
		review = Review(name = "JOKER", score = 3, description = "Mat-it-da")
	}

	@Test
	internal fun addReview() {
		given(reviewRepository.save(review)).willReturn(review)

		reviewService.addReview(1004L, review)

		verify(reviewRepository).save(review)
	}

	private fun <T> any(): T {
		Mockito.any<T>()
		return null as T
	}
}