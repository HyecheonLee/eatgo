package com.hyecheon.eatgo.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class MenuItem(val name: String) {
	@Id
	var id: Long? = null
	var restaurantId: Long? = null
}
