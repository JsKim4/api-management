package me.kjs.apimanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiManagementApplication {
	fun main(args: Array<String>) {
		runApplication<ApiManagementApplication>(*args)
	}
}
