package me.tabacowang.giveyouapunch.di

import dagger.Component
import me.tabacowang.giveyouapunch.PunchApplication
import me.tabacowang.giveyouapunch.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(punchApplication: PunchApplication)
}