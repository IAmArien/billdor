package com.idea.billdor.viewmodels.navigation.state

sealed class BottomAppBarState (val state: String) {
    data class Recipes(val name: String = "recipes") : BottomAppBarState(name)
    data class Tags(val name: String = "tags") : BottomAppBarState(name)
    data class MyProfile(val name: String = "my-profile") : BottomAppBarState(name)
}
