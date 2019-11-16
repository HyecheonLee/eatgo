package com.hyecheon.eatgo.domain

class Restaurant(val name: String, val address: String) {

	fun getInformation(): String = "$name in $address"

}