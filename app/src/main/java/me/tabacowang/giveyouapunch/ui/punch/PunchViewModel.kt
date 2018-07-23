package me.tabacowang.giveyouapunch.ui.punch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import me.tabacowang.giveyouapunch.firestore.FirestoreResource
import me.tabacowang.giveyouapunch.repository.PunchRepository
import me.tabacowang.giveyouapunch.util.AbsentLiveData
import me.tabacowang.giveyouapunch.vo.Punch
import me.tabacowang.giveyouapunch.vo.PunchDataSourceFactory
import javax.inject.Inject

class PunchViewModel
@Inject constructor(
        private val punchRepository: PunchRepository
) : ViewModel() {
    private val isFetchData = MutableLiveData<Boolean>()

    private val results: LiveData<FirestoreResource<List<Punch>>> = Transformations
            .switchMap(isFetchData) {search ->
                if (search) {
                    punchRepository.loadPunches()
                }
                else
                    AbsentLiveData.create()
            }

    val items: LiveData<PagedList<Punch>> = Transformations
            .switchMap(results) { result ->
                val pagedListConfig = PagedList.Config.Builder()
                            .setEnablePlaceholders(false)
                            .setInitialLoadSizeHint(20)//定义第一页加载项目的数量
                            .setPageSize(20)//定义从DataSource中每一次加载的项目数量
                            .build()
                val pagedList = LivePagedListBuilder<Int, Punch>(PunchDataSourceFactory(result?.data), pagedListConfig).build()
                pagedList
            }

    fun fetchData() {
        isFetchData.value = false
    }

    fun addNewPunch() {
        punchRepository.addPunch(Punch("title1", "content1"))
    }
}