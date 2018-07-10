package me.tabacowang.giveyouapunch

import android.app.Application
import me.tabacowang.giveyouapunch.di.ApplicationComponent
import me.tabacowang.giveyouapunch.di.ApplicationModule
import me.tabacowang.giveyouapunch.di.DaggerApplicationComponent

class PunchApplication : Application() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}