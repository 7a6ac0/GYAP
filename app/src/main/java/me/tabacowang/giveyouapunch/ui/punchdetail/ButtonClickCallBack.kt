package me.tabacowang.giveyouapunch.ui.punchdetail

import android.view.View
import me.tabacowang.giveyouapunch.vo.Punch

interface ButtonClickCallBack {
    fun click(punch: Punch, v: View)
}