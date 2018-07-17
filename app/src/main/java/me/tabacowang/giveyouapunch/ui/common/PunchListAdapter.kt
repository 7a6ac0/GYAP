package me.tabacowang.giveyouapunch.ui.common

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import me.tabacowang.giveyouapunch.AppExecutors
import me.tabacowang.giveyouapunch.R
import me.tabacowang.giveyouapunch.databinding.PunchItemBinding
import me.tabacowang.giveyouapunch.vo.Punch

class PunchListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val punchClickCallback: ((Punch) -> Unit)?
) : DataBoundListAdapter<Punch, PunchItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Punch>() {
            override fun areItemsTheSame(oldItem: Punch, newItem: Punch): Boolean {
                return oldItem.title ==newItem.title
            }

            override fun areContentsTheSame(oldItem: Punch, newItem: Punch): Boolean {
                return oldItem.content == newItem.content
            }
        }
) {
    override fun createBinding(parent: ViewGroup): PunchItemBinding {
        val binding = DataBindingUtil.inflate<PunchItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.punch_item,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.punch?.let {
                punchClickCallback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: PunchItemBinding, item: Punch) {
        binding.punch = item
    }
}