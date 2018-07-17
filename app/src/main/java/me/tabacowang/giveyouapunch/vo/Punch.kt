package me.tabacowang.giveyouapunch.vo

import me.tabacowang.giveyouapunch.firestore.FirestoreModel

data class Punch(
        val title: String? = null,
        val content: String? = null
) : FirestoreModel() {
}