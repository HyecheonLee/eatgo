package com.hyecheon.eatgo.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RestaurantTests {

	@Test
	internal fun creation() {
		val restaurant = Restaurant(1004, "Bob zip", "Seoul")
		assertEquals(restaurant.getId(), 1004)
		assertEquals(restaurant.name, "Bob zip")
		assertEquals(restaurant.address, "Seoul")

	}

	@Test
	internal fun information() {
		val restaurant = Restaurant(name = "Bob zip", address = "Seoul")
		assertEquals(restaurant.getInformation(), "Bob zip in Seoul")
	}
}