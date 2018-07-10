package me.tabacowang.giveyouapunch.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.tabacowang.giveyouapunch.PunchApplication
import me.tabacowang.giveyouapunch.data.PunchRepository
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: PunchApplication) {
    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun providePunchRepository(dataSource: PunchRepository.Network) : PunchRepository = dataSource
}