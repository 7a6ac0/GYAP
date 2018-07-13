package me.tabacowang.giveyouapunch.ui.punchdetail

import android.arch.lifecycle.ViewModel
import me.tabacowang.giveyouapunch.repository.PunchRepository
import javax.inject.Inject

class PunchDetailViewModel
@Inject constructor(
        punchRepository: PunchRepository
) : ViewModel() {
}