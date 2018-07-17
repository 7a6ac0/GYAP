package me.tabacowang.giveyouapunch.ui.punch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import me.tabacowang.giveyouapunch.firestore.FirestoreResource
import me.tabacowang.giveyouapunch.repository.PunchRepository
import me.tabacowang.giveyouapunch.util.AbsentLiveData
import me.tabacowang.giveyouapunch.vo.Punch
import javax.inject.Inject

class PunchViewModel
@Inject constructor(
        punchRepository: PunchRepository
) : ViewModel() {
    private val isFetchData = MutableLiveData<Boolean>()

    val results: LiveData<FirestoreResource<List<Punch>>> = Transformations
            .switchMap(isFetchData) {search ->
                if (search)
                    punchRepository.loadPunches()
                else
                    AbsentLiveData.create()
            }

    fun fetchData() {
        isFetchData.value = true
    }
}