package com.hyecheon.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
class Review() {
	constructor(id: Long? = null, name: String = "", score: Int? = null, description: String = "", restaurantId: Long? = null) : this() {
		this.id = id
		this.name = name
		this.score = score
		this.description = description
		this.restaurantId = restaurantId
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null

	@NotEmpty
	lateinit var name: String
	@NotNull
	var score: Int? = null
	@NotEmpty
	lateinit var description: String
	var restaurantId: Long? = null
}