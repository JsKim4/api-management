package me.kjs.apimanagement.common

import kotlin.reflect.KClass

object MapDatabase {
	private val tables: List<Pair<String, MutableMap<String, Any>>> = mutableListOf()

	fun <T : Any> getTableByName(kClass: KClass<T>): MutableMap<String, T> {
		val result = tables.find {
			it.first == kClass.simpleName
		}
		if (result == null) {
			val element = Pair(kClass.simpleName, mutableMapOf<String, Any>())
			tables.plus(element)
			return element.second as MutableMap<String, T>
		}
		return result.second as MutableMap<String, T>
	}

	fun clear() {
		for (table in tables) {
			table.second.clear()
		}
	}

}