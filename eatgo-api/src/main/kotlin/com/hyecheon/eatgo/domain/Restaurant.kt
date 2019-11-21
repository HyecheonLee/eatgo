package com.hyecheon.eatgo.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Restaurant(val name: String, var address: String) {
	constructor(_id: Long, name: String, address: String) : this(name, address) {
		id = _id
	}

	@Id
	private var id: Long? = null

	@Transient
	val menuItems: MutableList<MenuItem> = mutableListOf()

	fun getInformation(): String = "$name in $address"
	fun getId() = id
	fun addMenuItem(menuItem: MenuItem) {
		menuItems.add(menuItem)
	}

	fun setMenuItem(menuItems: List<MenuItem>) {
		menuItems.forEach { addMenuItem(it) }
	}
}