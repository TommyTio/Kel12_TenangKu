package com.example.tommy_3tib.jelajah

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class JelajahTabsAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TipsFragment()
            1 -> AfirmasiFragment()
            2 -> AktivitasFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}