package me.tabacowang.giveyouapunch.ui.punch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import me.tabacowang.giveyouapunch.AppExecutors
import me.tabacowang.giveyouapunch.R
import me.tabacowang.giveyouapunch.binding.FragmentDataBindingComponent
import me.tabacowang.giveyouapunch.databinding.PunchFragmentBinding
import me.tabacowang.giveyouapunch.di.Injectable
import me.tabacowang.giveyouapunch.ui.common.PunchListAdapter
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

    var adapter by autoCleared<PunchListAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val databinding = DataBindingUtil.inflate<PunchFragmentBinding>(
                inflater,
                R.layout.punch_fragment,
                container,
                false,
                dataBindingComponent
        )

        binding = databinding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        punchViewModel = ViewModelProviders.of(this, viewModelFactory).get(PunchViewModel::class.java)

        initRecycleView()
        initFab()

        val punchAdapter = PunchListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors
        ) { punch ->
            navController().navigate(PunchFragmentDirections.showPunchDetail(punch.id))
        }

        binding.punchList.adapter = punchAdapter
        adapter = punchAdapter
    }

    override fun onResume() {
        super.onResume()
        punchViewModel.fetchData()
    }

    private fun initRecycleView() {
        punchViewModel.items.observe(this, Observer { result ->
            adapter.submitList(result)
        })
    }

    private fun initFab() {
        activity?.findViewById<FloatingActionButton>(R.id.punch_fab)?.let {
            it.setImageResource(R.drawable.ic_add)
            it.setOnClickListener {
                navController().navigate(PunchFragmentDirections.addPunch(null))
            }
        }
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}