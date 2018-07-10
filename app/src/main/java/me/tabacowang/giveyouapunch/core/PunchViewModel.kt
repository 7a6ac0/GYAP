package me.tabacowang.giveyouapunch.core

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import me.tabacowang.giveyouapunch.data.PunchRepository
import javax.inject.Inject

class PunchViewModel
@Inject constructor(
        context: Application,
        private val punchRepository: PunchRepository
) : AndroidViewModel(context) {

    val noPunchLabel = ObservableField<String>()
    val noPunchIconRes = ObservableField<Drawable>()
    val empty = ObservableBoolean(true)

}