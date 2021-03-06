package com.hyecheon.eatgo.interfaces

import com.hyecheon.eatgo.application.RestaurantService
import com.hyecheon.eatgo.domain.MenuItem
import com.hyecheon.eatgo.domain.Restaurant
import com.hyecheon.eatgo.domain.RestaurantNoFoundException
import com.hyecheon.eatgo.domain.Review
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(RestaurantController::class)
internal class RestaurantControllerTest {

	@Autowired
	private lateinit var mvc: MockMvc

	@MockBean
	lateinit var restaurantService: RestaurantService

	@Test
	fun list() {
		val restaurants: MutableList<Restaurant> = mutableListOf()
		restaurants += Restaurant(1004, "JOKER House", "Seoul")
		given(restaurantService.getRestaurants()).willReturn(restaurants)

		mvc.perform(get("/restaurants"))
				.andExpect(status().isOk)
				.andExpect(content().string(containsString("""
					"name":"JOKER House"
				""".trimIndent())))
				.andExpect(content().string(containsString("""
					"id":1004
				""".trimIndent())))

	}

	@Test
	internal fun detailWithExisted() {
		val restaurant = Restaurant(1004, "Bob zip", "Seoul")
				.apply { addMenuItem(MenuItem(name = "Kimchi")) }
		val review = Review(name = "JOKER", score = 5, description = "Great!")
		restaurant.setReviews(review)
		given(restaurantService.getRestaurant(1004))
				.willReturn(
						restaurant)

		mvc.perform(get("/restaurants/1004"))
				.andExpect(status().isOk)
				.andExpect(content().string(containsString("""
					"name":"Bob zip"
				""".trimIndent())))
				.andExpect(content().string(containsString("""
					"id":1004
				""".trimIndent())))
				.andExpect((content().string(
						containsString("Kimchi")
				)))
				.andExpect(content().string(
						containsString("Great!")
				))

	}

	@Test
	internal fun detailWithNotExisted() {
		given(restaurantService.getRestaurant(404)).willThrow(RestaurantNoFoundException(404))
		mvc.perform(get("/restaurants/404"))
				.andExpect(status().isNotFound)
				.andExpect(content().string("{}"))
	}


	@Test
	internal fun createWithValidData() {
		mvc.perform(post("/restaurants")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
							{
							"name":"BeRyong",
							"address":"Seoul"
							}
						""".trimIndent()))
				.andExpect(status().isCreated)
				.andExpect(header().string("location", "/restaurants/1234"))
				.andExpect(content().string("{}"))

		verify(restaurantService).addRestaurant(any())
	}

	@Test
	internal fun createWithInvalidData() {
		mvc.perform(post("/restaurants")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
							{
							"name":"",
							"address":""
							}
						""".trimIndent()))
				.andExpect(status().isBadRequest)
	}

	@Test
	internal fun updateWithValidData() {
		mvc.perform(patch("/restaurants/1004")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{"name":"JOKER Bar","address":"Busan"}
				""".trimIndent()))
				.andExpect(status().isOk)

		verify(restaurantService).updateRestaurant(1004, "JOKER Bar", "Busan")

	}

	@Test
	internal fun updateWithInvalidData() {
		mvc.perform(patch("/restaurants/1004")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{"name":"","address":""}
				""".trimIndent()))
				.andExpect(status().isBadRequest)
	}

	private fun <T> any(): T {
		Mockito.any<T>()
		return null as T
	}
}