package com.example.tommy_3tib.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tommy_3tib.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        // 1. Pasang adapter dulu
        binding.viewPagerTutorial.adapter = TutorialFragmentAdapter(this, fragmentsList)

        // 2. Baru hubungkan DotsIndicator
        binding.dotIndicator.attachTo(binding.viewPagerTutorial)
    }
}
