package com.hyecheon.eatgo.domain

import java.lang.RuntimeException

class RestaurantNoFoundException : RuntimeException {
	constructor() : super()
	constructor(message: String?) : super(message)
	constructor(id: Long) : super("Could not find restaurant $id")
	constructor(message: String?, cause: Throwable?) : super(message, cause)
}