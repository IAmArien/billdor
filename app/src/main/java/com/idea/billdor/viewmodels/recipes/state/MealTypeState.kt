package com.idea.billdor.viewmodels.recipes.state

sealed class MealTypeState (val type: String, val label: String) {

    data class All(
        val name: String = "All",
        val text: String = "All Recipes"
    ) : MealTypeState(type = name, label = text)

    data class Breakfast(
        val name: String = "breakfast",
        val text: String = "For Breakfast"
    ) : MealTypeState(type = name, label = text)

    data class Lunch(
        val name: String = "lunch",
        val text: String = "For Lunch"
    ) : MealTypeState(type = name, label = text)

    data class Dinner(
        val name: String = "dinner",
        val text: String = "For Dinner"
    ) : MealTypeState(type = name, label = text)

    data class Snacks(
        val name: String = "snack",
        val text: String = "For Snacks"
    ) : MealTypeState(type = name, label = text)
}
