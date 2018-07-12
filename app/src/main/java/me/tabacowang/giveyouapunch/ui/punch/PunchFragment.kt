package me.tabacowang.giveyouapunch.ui.punch

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment
import me.tabacowang.giveyouapunch.AppExecutors
import me.tabacowang.giveyouapunch.binding.FragmentDataBindingComponent
import me.tabacowang.giveyouapunch.databinding.PunchFragmentBinding
import me.tabacowang.giveyouapunch.di.Injectable
import me.tabacowang.giveyouapunch.util.autoCleared
import javax.inject.Inject

class PunchFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var punchViewModel: PunchViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<PunchFragmentBinding>()
}