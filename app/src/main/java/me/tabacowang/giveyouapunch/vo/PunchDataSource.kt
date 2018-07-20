package me.tabacowang.giveyouapunch.vo

import android.arch.paging.ItemKeyedDataSource

class PunchDataSource(private val data: List<Punch>?) : ItemKeyedDataSource<Int, Punch>() {

    companion object {
        private const val MAX_PAGE_SIZE = 20
    }

    private val items: MutableList<Punch> = ArrayList<Punch>()

    init {
        data?.let {
            for (item in it) {
                items.add(item)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Punch>) {
        val pageSize = minOf(MAX_PAGE_SIZE, params.requestedLoadSize)
        val firstItem = inRange(params.requestedInitialKey ?: 0,0, items.size)
        val lastItem = inRange(firstItem + pageSize, 0, items.size)
        val data = if (firstItem == lastItem) emptyList<Punch>() else items.subList(firstItem, lastItem)
        if (params.placeholdersEnabled) {
            callback.onResult(data, firstItem, items.size)
        } else {
            callback.onResult(data)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Punch>) {
        val pageSize = minOf(MAX_PAGE_SIZE, params.requestedLoadSize)
        val firstItem = inRange(params.key + 1, 0, items.size)
        val lastItem = inRange(firstItem + pageSize, 0, items.size)
        val data = if (firstItem == lastItem) emptyList<Punch>() else items.subList(firstItem, lastItem)
        callback.onResult(data)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Punch>) {
        val pageSize = minOf(MAX_PAGE_SIZE, params.requestedLoadSize)
        val lastItem = inRange(params.key - 1, 0, items.size)
        val firstItem = inRange(lastItem - pageSize, 0, items.size)
        val data = if (firstItem == lastItem) emptyList<Punch>() else items.subList(firstItem, lastItem)
        callback.onResult(data)
    }

    override fun getKey(item: Punch) = items.indexOfFirst { it.id == item.id }

    fun inRange(position: Int, start: Int, end: Int): Int {
        if (position < start) return start
        if (position > end) return end
        return position
    }
}