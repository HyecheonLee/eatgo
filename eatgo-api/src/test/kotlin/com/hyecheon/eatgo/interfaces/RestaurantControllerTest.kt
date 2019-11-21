package com.hyecheon.eatgo.interfaces

import com.hyecheon.eatgo.application.RestaurantService
import com.hyecheon.eatgo.domain.MenuItem
import com.hyecheon.eatgo.domain.Restaurant
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

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
	internal fun detail() {
		given(restaurantService.getRestaurant(1004))
				.willReturn(
						Restaurant(1004, "Bob zip", "Seoul")
								.apply { addMenuItem(MenuItem("Kimchi")) })
		given(restaurantService.getRestaurant(2020))
				.willReturn(Restaurant(2020, "Cyber Food", "Seoul"))
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

		mvc.perform(get("/restaurants/2020"))
				.andExpect(status().isOk)
				.andExpect(content().string(containsString("""
					"name":"Cyber Food"
				""".trimIndent())))
				.andExpect(content().string(containsString("""
					"id":2020
				""".trimIndent())))
	}

	@Test
	internal fun create() {
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

	private fun <T> any(): T {
		Mockito.any<T>()
		return null as T
	}
}