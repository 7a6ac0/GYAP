package me.tabacowang.giveyouapunch.repository

import javax.inject.Inject

interface PunchRepository {
    fun punches()
    fun punchDetail()

    class Network
    @Inject constructor() : PunchRepository {
        override fun punches() {

        }

        override fun punchDetail() {

        }
    }
}