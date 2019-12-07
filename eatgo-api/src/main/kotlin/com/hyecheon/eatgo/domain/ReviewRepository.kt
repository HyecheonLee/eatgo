package com.hyecheon.eatgo.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
	fun findAllByRestaurantId(restaurantId: Long): List<Review>

}