package com.hyecheon.eatgo.application

import com.hyecheon.eatgo.domain.MenuItemRepository
import com.hyecheon.eatgo.domain.Restaurant
import com.hyecheon.eatgo.domain.RestaurantNoFoundException
import com.hyecheon.eatgo.domain.RestaurantRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional
class RestaurantService(private val restaurantRepository: RestaurantRepository,
                        private val menuItemRepository: MenuItemRepository) {


	@Transactional(readOnly = true)
	fun getRestaurant(id: Long): Restaurant {
		val restaurant = restaurantRepository.findById(id).orElseThrow { RestaurantNoFoundException(id) }
		restaurant.getId()?.let {
			val menuItems = menuItemRepository.findAllByRestaurantId(it)
			menuItems
		}?.let {
			restaurant.setMenuItem(it)
		}
		return restaurant
	}

	@Transactional(readOnly = true)
	fun getRestaurants(): List<Restaurant> {

		return restaurantRepository.findAll()
	}

	fun addRestaurant(restaurant: Restaurant): Restaurant {
		return restaurantRepository.save(restaurant)
	}

	fun updateRestaurant(id: Long, name: String, address: String): Restaurant {
		val found = restaurantRepository.findById(id).orElseThrow { throw RuntimeException("Id Error") }
		found.address = address
		found.name = name
		return found
	}
}