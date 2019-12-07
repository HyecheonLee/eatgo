package com.hyecheon.eatgo.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class MenuItemServiceTest {

	@Mock
	private lateinit var menuItemRepository: MenuItemRepository
	private lateinit var menuItemService: MenuItemService
	
	@BeforeEach
	internal fun setUp() {
		MockitoAnnotations.initMocks(this)
		menuItemService = MenuItemService()
		menuItemService.menuItemRepository = menuItemRepository
	}

	@Test
	internal fun bulkUpdate() {
		val menuItems = arrayListOf<MenuItem>()
		menuItems.add(MenuItem(name = "Kimchi"))
		menuItems.add(MenuItem(id = 12, name = "Gukbob"))
		menuItems.add(MenuItem(id = 1004, destroy = true))
		menuItemService.bulkUpdate(1, menuItems)
		verify(menuItemRepository, times(2)).save(any())
		verify(menuItemRepository, times(1)).deleteById(eq(1004L))
	}

	private fun <T> any(): T {
		Mockito.any<T>()
		return null as T
	}
}