package com.example.apod.ui.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apod.R
import com.example.apod.databinding.ActivityMainBinding
import com.example.apod.ui.view.fragments.PastImageFragment
import com.example.apod.ui.view.fragments.RandomImageFragment
import com.example.apod.ui.view.fragments.TodayImageFragment
import com.example.apod.ui.view.recyclerview.APODAdapter
import com.example.apod.ui.viewmodel.ApodViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(TodayImageFragment())
        binding.bnMainActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.today -> {
                    loadFragment(TodayImageFragment())
                    true
                }
                R.id.past -> {
                    loadFragment(PastImageFragment())
                    true
                }
                R.id.random -> {
                    loadFragment(RandomImageFragment())
                    true
                }
                else -> {false}
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {}

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}


