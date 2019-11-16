package com.hyecheon.eatgo.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RestaurantTests {

	@Test
	internal fun creation() {
		val restaurant = Restaurant("Bob zip", "Seoul")
		assertEquals(restaurant.name, "Bob zip")
		assertEquals(restaurant.address, "Seoul")

	}

	@Test
	internal fun information() {
		val restaurant = Restaurant("Bob zip", "Seoul")
		assertEquals(restaurant.getInformation(), "Bob zip in Seoul")
	}
}