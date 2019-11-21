package com.hyecheon.eatgo.application

import com.hyecheon.eatgo.domain.MenuItemRepository
import com.hyecheon.eatgo.domain.Restaurant
import com.hyecheon.eatgo.domain.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantService(private val restaurantRepository: RestaurantRepository,
                        private val menuItemRepository: MenuItemRepository) {


	fun getRestaurant(id: Long): Restaurant {
		val restaurant = restaurantRepository.findById(id).get()
		restaurant.getId()?.let {
			val menuItems = menuItemRepository.findAllByRestaurantId(it)
			menuItems
		}?.let {
			restaurant.setMenuItem(it)
		}
		return restaurant
	}

	fun getRestaurants(): List<Restaurant> {

		return restaurantRepository.findAll()
	}

	fun addRestaurant(restaurant: Restaurant): Restaurant {
		return restaurantRepository.save(restaurant)
	}
}