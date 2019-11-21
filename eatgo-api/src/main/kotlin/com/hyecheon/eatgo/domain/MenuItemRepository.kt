package com.hyecheon.eatgo.domain

import com.hyecheon.eatgo.domain.MenuItem
import org.springframework.data.repository.CrudRepository

interface MenuItemRepository : CrudRepository<MenuItem, Long> {
	fun findAllByRestaurantId(restaurantId: Long): List<MenuItem>
}
