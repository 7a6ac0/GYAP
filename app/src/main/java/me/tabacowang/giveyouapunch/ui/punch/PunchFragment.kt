package me.tabacowang.giveyouapunch.ui.punch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.navigation.fragment.findNavController
import me.tabacowang.giveyouapunch.AppExecutors
import me.tabacowang.giveyouapunch.MainActivity
import me.tabacowang.giveyouapunch.R
import me.tabacowang.giveyouapunch.binding.FragmentDataBindingComponent
import me.tabacowang.giveyouapunch.databinding.PunchFragmentBinding
import me.tabacowang.giveyouapunch.di.Injectable
import me.tabacowang.giveyouapunch.ui.common.PunchListAdapter
import me.tabacowang.giveyouapunch.util.autoCleared
import me.tabacowang.giveyouapunch.util.setupActionBar
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

        val activity = activity as? MainActivity
        activity?.setupActionBar(R.id.toolbar){
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        binding = databinding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        punchViewModel = ViewModelProviders.of(this, viewModelFactory).get(PunchViewModel::class.java)
//        binding.button.setOnClickListener {
//            navController().navigate(PunchFragmentDirections.showPunchDetail())
//        }

        initRecycleView()

        val punchAdapter = PunchListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors
        ) {

        }

        binding.punchList.adapter = punchAdapter
        adapter = punchAdapter
    }

    override fun onDestroyView() {
        val activity = activity as? MainActivity
        activity?.setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(false)
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val activity = activity as? MainActivity
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.drawerLayout?.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        punchViewModel.fetchData()
    }

    private fun initRecycleView() {
        binding.punchList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                }
            }
        })
        punchViewModel.results.observe(this, Observer { result ->
            adapter.submitList(result?.data)
        })

    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}