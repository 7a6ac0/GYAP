package me.tabacowang.giveyouapunch.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.tabacowang.giveyouapunch.databinding.PunchFragmentBinding

class PunchFragment : Fragment() {
    private lateinit var punchFragmentBinding: PunchFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        punchFragmentBinding = PunchFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as PunchActivity).obtainViewModel()
        }
        setHasOptionsMenu(true)
        return punchFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance() = PunchFragment()
        private const val TAG = "PunchFragment"
    }
}