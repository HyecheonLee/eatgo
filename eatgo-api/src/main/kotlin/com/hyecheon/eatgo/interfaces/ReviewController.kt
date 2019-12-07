package com.hyecheon.eatgo.interfaces

import com.hyecheon.eatgo.application.ReviewService
import com.hyecheon.eatgo.domain.Review
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid


@RestController
class ReviewController(val reviewService: ReviewService) {

	@PostMapping("/restaurants/{restaurantId}/reviews")
	fun create(@PathVariable restaurantId: Long, @Valid @RequestBody resource: Review): ResponseEntity<*> {
		val review = reviewService.addReview(restaurantId, resource)
		val uri = URI("/restaurants/${restaurantId}/reviews/${review.id}")
		return ResponseEntity.created(uri).body("{}")
	}
}