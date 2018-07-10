package me.tabacowang.giveyouapunch.core

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.tabacowang.giveyouapunch.R
import me.tabacowang.giveyouapunch.util.obtainViewModel
import javax.inject.Inject

class PunchActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.punch_activity)
    }

    fun obtainViewModel(): PunchViewModel = obtainViewModel(viewModelFactory) {}
}
