package me.tabacowang.giveyouapunch.ui.punchdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import me.tabacowang.giveyouapunch.AppExecutors
import me.tabacowang.giveyouapunch.MainActivity
import me.tabacowang.giveyouapunch.R
import me.tabacowang.giveyouapunch.binding.FragmentDataBindingComponent
import me.tabacowang.giveyouapunch.databinding.PunchDetailFragmentBinding
import me.tabacowang.giveyouapunch.di.Injectable
import me.tabacowang.giveyouapunch.util.autoCleared
import me.tabacowang.giveyouapunch.util.setupActionBar
import me.tabacowang.giveyouapunch.vo.Punch
import javax.inject.Inject

class PunchDetailFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var punchDetailViewModel: PunchDetailViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<PunchDetailFragmentBinding>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        punchDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(PunchDetailViewModel::class.java)
        val params = PunchDetailFragmentArgs.fromBundle(arguments)
        punchDetailViewModel.setId(params.id)

        initFab()

        punchDetailViewModel.punch.observe(this, Observer { resource ->
            binding.punch = resource?.data
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val databinding = DataBindingUtil.inflate<PunchDetailFragmentBinding>(
                inflater,
                R.layout.punch_detail_fragment,
                container,
                false,
                dataBindingComponent
        )

        databinding.callback = object : ButtonClickCallBack {
            override fun click(punch: Punch, v: View) {
                punchDetailViewModel.incrementPunchCount(punch)
            }
        }

        val activity = activity as? MainActivity
        activity?.setupActionBar(R.id.toolbar){
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        setHasOptionsMenu(true)

        binding = databinding
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val activity = activity as? MainActivity
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initFab() {
        activity?.findViewById<FloatingActionButton>(R.id.punch_fab)?.let {
            it.setImageResource(R.drawable.ic_edit)
            it.setOnClickListener {
                navController().navigate(PunchDetailFragmentDirections.editPunch(binding.punch?.id))
            }
        }
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}