package com.example.tommy_3tib

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tommy_3tib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = ""
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna")
        binding.tvSapaan.text = "Halo, $username"

        replaceFragment(BerandaFragment(), addToBackStack = false)

        binding.btnBeranda.setOnClickListener { replaceFragment(BerandaFragment()) }
        binding.btnRiwayat.setOnClickListener { replaceFragment(RiwayatFragment()) }
        binding.btnBantuan.setOnClickListener { replaceFragment(BantuanFragment()) }
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

                AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Apakah Anda yakin ingin keluar?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        val editor = sharedPref.edit()
                        editor.clear()
                        editor.apply()
                        dialog.dismiss()

                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}