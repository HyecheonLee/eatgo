package com.hyecheon.eatgo.application

import com.hyecheon.eatgo.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*

internal class RestaurantServiceTest {

	private lateinit var restaurantService: RestaurantService

	@Mock
	private lateinit var restaurantRepository: RestaurantRepository

	@Mock
	private lateinit var menuItemRepository: MenuItemRepository
	@Mock
	private lateinit var reviewRepository: ReviewRepository

	@BeforeEach
	internal fun setUp() {
		MockitoAnnotations.initMocks(this)
		mockRestaurantRepository()
		mockMenuItemRepository()
		mockReviewRepository()
		restaurantService = RestaurantService(restaurantRepository, menuItemRepository, reviewRepository)
	}

	private fun mockMenuItemRepository() {
		val menuItems: MutableList<MenuItem> = mutableListOf()
		menuItems.add(MenuItem(name = "Kimchi"))
		given(menuItemRepository.findAllByRestaurantId(1004)).willReturn(menuItems)
	}

	private fun mockRestaurantRepository() {
		val restaurants: MutableList<Restaurant> = mutableListOf()
		val restaurant = Restaurant(1004, "Bob zip", "Seoul")
		restaurants.add(restaurant)

		given(restaurantRepository.findAll()).willReturn(restaurants)

		given(restaurantRepository.findById(1004)).willReturn(Optional.of(restaurant))

	}

	private fun mockReviewRepository() {
		val reviews = mutableListOf<Review>()
		reviews.add(Review(name = "BeRyong", score = 1, description = "Bad"))
		given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews)
	}

	@Test
	internal fun getRestaurantWithExisted() {
		val restaurant = restaurantService.getRestaurant(1004)

		verify(menuItemRepository).findAllByRestaurantId(eq(1004L))
		verify(reviewRepository).findAllByRestaurantId(eq(1004L))

		assertEquals(restaurant.getId(), 1004)
		val menuItems = restaurant.menuItems
		val menuItem = menuItems[0]
		assertEquals(menuItem.name, "Kimchi")

		val review = restaurant.reviews[0]
		assertThat(review.description).isEqualTo("Bad")
	}

	@Test
	internal fun getRestaurantWithNotExisted() {
		assertThrows(RestaurantNoFoundException::class.java) {
			restaurantService.getRestaurant(404)
		}
	}

	@Test
	internal fun getRestaurants() {
		val restaurants = restaurantService.getRestaurants()
		assertEquals(restaurants[0].getId(), 1004)
	}

	@Test
	internal fun addRestaurant() {
		val restaurant = Restaurant(name = "BeRyong", address = "Busan")
		val saved = Restaurant(1234, "BeRyong", "Busan")

		given(restaurantRepository.save(any())).willReturn(saved)

		val created = restaurantService.addRestaurant(restaurant)

		assertEquals(created.getId(), 1234)

	}

	@Test
	internal fun updateRestaurant() {
		val restaurant = Restaurant(1004, "Bob zip", "Seoul")
		restaurantService.addRestaurant(restaurant)
		val updated = restaurantService.updateRestaurant(1004, "Sool zip", "Busan")
		var found = restaurantService.getRestaurant(1004)
		assertEquals(updated, found)
	}

	private fun <T> any(): T {
		Mockito.any<T>()
		return null as T
	}
}