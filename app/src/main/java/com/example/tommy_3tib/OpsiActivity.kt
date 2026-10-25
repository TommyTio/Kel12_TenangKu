package com.example.tommy_3tib

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tommy_3tib.databinding.ActivityOpsiBinding

class OpsiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpsiBinding

    private val dataList = listOf(
        "Profil Saya",
        "Privasi & Keamanan",
        "Tentang TenangKu"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOpsiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.setNavigationOnClickListener { finish() }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            dataList
        )

        binding.listOpsi.adapter = adapter

        binding.listOpsi.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataList[position]
            Toast.makeText(this, "Kamu memilih: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}