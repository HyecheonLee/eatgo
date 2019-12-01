package com.hyecheon.eatgo.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuItemService {
	@Autowired
	lateinit var menuItemRepository: MenuItemRepository

	fun bulkUpdate(restaurantId: Long, menuItems: List<MenuItem>) {
		menuItems.map { it ->
			it.restaurantId = restaurantId
			it
		}.forEach(this::update)
	}

	private fun update(menuItem: MenuItem) {
		if (menuItem.destroy) {
			menuItem.id?.also { id -> menuItemRepository.deleteById(id) }
		} else {
			menuItemRepository.save(menuItem)
		}
	}
}