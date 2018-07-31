package me.tabacowang.giveyouapunch.vo

import com.google.firebase.Timestamp
import me.tabacowang.giveyouapunch.firestore.FirestoreModel

data class Punch(
        var title: String? = null,
        var content: String? = null,
        var count: Int = 0,
        var created_at: Timestamp? = null
) : FirestoreModel() {
}