package com.hyecheon.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Transient

@Entity
class MenuItem() {
	@Id
	var id: Long? = null
	var restaurantId: Long? = null
	var name: String = ""
	@Transient
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	var destroy: Boolean = false

	constructor(id: Long? = null, restaurantId: Long? = null, name: String = "", destroy: Boolean = false) : this() {
		this.id = id
		this.restaurantId = restaurantId
		this.name = name
		this.destroy = destroy
	}
}
