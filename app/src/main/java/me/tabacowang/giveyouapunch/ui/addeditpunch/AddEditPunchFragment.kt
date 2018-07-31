package me.tabacowang.giveyouapunch.ui.addeditpunch

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
import com.google.firebase.Timestamp
import me.tabacowang.giveyouapunch.MainActivity
import me.tabacowang.giveyouapunch.R
import me.tabacowang.giveyouapunch.binding.FragmentDataBindingComponent
import me.tabacowang.giveyouapunch.databinding.AddEditPunchFragmentBinding
import me.tabacowang.giveyouapunch.di.Injectable
import me.tabacowang.giveyouapunch.ui.punchdetail.PunchDetailFragmentDirections
import me.tabacowang.giveyouapunch.util.autoCleared
import me.tabacowang.giveyouapunch.util.setupActionBar
import me.tabacowang.giveyouapunch.vo.Punch
import javax.inject.Inject

class AddEditPunchFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var addEditPunchViewModel: AddEditPunchViewModel

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<AddEditPunchFragmentBinding>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEditPunchViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddEditPunchViewModel::class.java)
        val params = AddEditPunchFragmentArgs.fromBundle(arguments)
        addEditPunchViewModel.setId(params.id)

        initFab()

        addEditPunchViewModel.punch.observe(this, Observer { resource ->
            binding.punch = resource?.data ?: Punch(created_at = Timestamp.now())
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val databinding = DataBindingUtil.inflate<AddEditPunchFragmentBinding>(
                inflater,
                R.layout.add_edit_punch_fragment,
                container,
                false,
                dataBindingComponent
        )

        val activity = activity as? MainActivity
        activity?.setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        setHasOptionsMenu(true)

        binding = databinding
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
            it.setImageResource(R.drawable.ic_done)
            it.setOnClickListener {
                if (binding.punch?.title.isNullOrBlank() || binding.punch?.content.isNullOrBlank()) {
                    return@setOnClickListener
                }
                addEditPunchViewModel.savePunch(binding.punch!!)
                this@AddEditPunchFragment.activity?.onBackPressed()
            }
        }
    }

}