package com.example.aiboroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = SlideAdapter(this)
        val viewPager = view.findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = adapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->  }.attach()
    }
}


class SlideAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        // TODO: Fix viewModel parameter
        return AccessTokenSettingFragment()
//        return ActionApiFragment()
    }

}