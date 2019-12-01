package com.hyecheon.eatgo.interfaces

import com.hyecheon.eatgo.domain.MenuItem
import com.hyecheon.eatgo.domain.MenuItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MenuItemController {

	@Autowired
	lateinit var menuItemService: MenuItemService

	@PatchMapping("/restaurants/{restaurantId}/menuitems")
	fun bulkUpdate(@PathVariable restaurantId: Long, @RequestBody menuItems: List<MenuItem>): String {
		menuItemService.bulkUpdate(restaurantId,menuItems)
		return "";
	}
}