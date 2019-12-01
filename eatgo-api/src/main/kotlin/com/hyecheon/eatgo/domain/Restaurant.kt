package com.hyecheon.eatgo.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
class Restaurant() {
	@Id
	private var id: Long? = null
	@NotEmpty
	var name: String = ""
	@NotEmpty
	var address: String = ""


	@Transient
	val menuItems: MutableList<MenuItem> = mutableListOf()

	constructor(id: Long?, name: String, address: String) : this() {
		this.id = id
		this.name = name
		this.address = address
	}

	constructor(name: String, address: String) : this() {
		this.name = name
		this.address = address
	}


	fun getInformation(): String = "$name in $address"
	fun getId() = id
	fun addMenuItem(menuItem: MenuItem) {
		menuItems.add(menuItem)
	}

	fun setMenuItem(menuItems: List<MenuItem>) {
		menuItems.forEach { addMenuItem(it) }
	}
}