package me.tabacowang.giveyouapunch.vo

import android.arch.paging.DataSource

class PunchDataSourceFactory(private val data: List<Punch>?) : DataSource.Factory<Int,Punch>() {
    override fun create(): DataSource<Int, Punch> = PunchDataSource(data)
}