package br.com.promotion.ui_component

interface AppPreferences {
    fun save(key: String, value: String?)
    fun get(key: String): String?
}