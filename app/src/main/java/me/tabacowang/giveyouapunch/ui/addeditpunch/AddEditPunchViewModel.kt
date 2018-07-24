package me.tabacowang.giveyouapunch.ui.addeditpunch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import me.tabacowang.giveyouapunch.firestore.FirestoreResource
import me.tabacowang.giveyouapunch.repository.PunchRepository
import me.tabacowang.giveyouapunch.util.AbsentLiveData
import me.tabacowang.giveyouapunch.vo.Punch
import javax.inject.Inject

class AddEditPunchViewModel
@Inject constructor(
        private val punchRepository: PunchRepository
) : ViewModel() {
    private val _punchId: MutableLiveData<PunchId> = MutableLiveData()

    val punch: LiveData<FirestoreResource<Punch>> = Transformations
            .switchMap(_punchId) { input ->
                input.ifExists { punchId ->
                    punchRepository.loadPunch(punchId)
                }
            }

    fun setId(punchId: String?) {
        _punchId.value = PunchId(punchId)
    }

    fun updatePunch() {

    }

    data class PunchId(val punchId: String?) {
        fun <T> ifExists(f: (String) -> LiveData<T>): LiveData<T> {
            return if (punchId.isNullOrBlank()) {
                AbsentLiveData.create()
            }
            else {
                f(punchId!!)
            }
        }
    }
}