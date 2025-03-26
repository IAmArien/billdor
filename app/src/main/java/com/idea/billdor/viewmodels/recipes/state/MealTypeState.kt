package com.idea.billdor.viewmodels.recipes.state

sealed class MealTypeState (val type: String, val label: String) {

    data class All(
        val name: String = "All",
        val text: String = "All Recipes"
    ) : MealTypeState(type = name, label = text)

    data class Breakfast(
        val name: String = "Breakfast",
        val text: String = "For Breakfast"
    ) : MealTypeState(type = name, label = text)

    data class Lunch(
        val name: String = "Lunch",
        val text: String = "For Lunch"
    ) : MealTypeState(type = name, label = text)

    data class Dinner(
        val name: String = "Dinner",
        val text: String = "For Dinner"
    ) : MealTypeState(type = name, label = text)

    data class Snacks(
        val name: String = "Snacks",
        val text: String = "For Snacks"
    ) : MealTypeState(type = name, label = text)
}
