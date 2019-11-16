package com.hyecheon.eatgo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EatgoApplication

fun main(args: Array<String>) {
	runApplication<EatgoApplication>(*args)
}

