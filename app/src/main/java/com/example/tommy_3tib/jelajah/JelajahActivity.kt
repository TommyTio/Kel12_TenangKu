package com.example.tommy_3tib.jelajah

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tommy_3tib.R
import com.example.tommy_3tib.databinding.ActivityJelajahBinding
import com.google.android.material.tabs.TabLayoutMediator

class JelajahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJelajahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJelajahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Jelajah Ketenangan"
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { finish() }

        val tabsAdapter = JelajahTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tips"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tips)
                }
                1 -> {
                    tab.text = "Afirmasi"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_afirmasi)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                2 -> {
                    tab.text = "Aktivitas"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_aktivitas)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = AktivitasData.items.size
                }
            }
        }.attach()
    }
}