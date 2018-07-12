package me.tabacowang.giveyouapunch.ui.punch

import android.arch.lifecycle.ViewModel
import me.tabacowang.giveyouapunch.repository.PunchRepository
import javax.inject.Inject

class PunchViewModel
@Inject constructor(
        punchRepository: PunchRepository
) : ViewModel() {

}