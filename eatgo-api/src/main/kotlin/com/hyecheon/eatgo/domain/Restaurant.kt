package com.hyecheon.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
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
	@JsonInclude(JsonInclude.Include.NON_NULL)
	val menuItems: MutableList<MenuItem> = mutableListOf()

	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	val reviews: MutableList<Review> = mutableListOf()

	constructor(id: Long? = null, name: String = "", address: String = "") : this() {
		this.id = id
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

	fun setReviews(vararg reviews: Review) {
		reviews.forEach { this.reviews.add(it) }
	}
}