package com.hyecheon.eatgo.interfaces

import com.hyecheon.eatgo.application.RestaurantService
import com.hyecheon.eatgo.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@CrossOrigin
@RestController
class RestaurantController {

	@Autowired
	private lateinit var restaurantService: RestaurantService

	@GetMapping("/restaurants")
	fun lst(): List<Restaurant> {
		val restaurants = restaurantService.getRestaurants()
		return restaurants
	}

	@GetMapping("/restaurants/{id}")
	fun detail(@PathVariable id: Long): Restaurant {
		val restaurant = restaurantService.getRestaurant(id)

		return restaurant
	}

	@PostMapping("/restaurants")
	fun create(@RequestBody resource: Restaurant): ResponseEntity<*> {
		val name = resource.name
		val address = resource.address

		val restaurant = Restaurant(1234, name, address)
		restaurantService.addRestaurant(restaurant)
		val localtion = URI("/restaurants/${restaurant.getId()}")
		return ResponseEntity.created(localtion).body("{}")
	}
}