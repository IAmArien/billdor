package com.idea.billdor.viewmodels.recipes.state

sealed class MealTypeState (val type: String) {
    data class All(val name: String = "All") : MealTypeState(type = name)
    data class Breakfast(val name: String = "Breakfast") : MealTypeState(type = name)
    data class Lunch(val name: String = "Lunch") : MealTypeState(type = name)
    data class Dinner(val name: String = "Dinner") : MealTypeState(type = name)
    data class Snacks(val name: String = "Snacks") : MealTypeState(type = name)
}
