package me.tabacowang.giveyouapunch.core

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import me.tabacowang.giveyouapunch.data.PunchRepository
import javax.inject.Inject

class PunchViewModel
@Inject constructor(
        context: Application,
        private val punchRepository: PunchRepository
) : AndroidViewModel(context) {

}