package com.nasa.apod.ui.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nasa.apod.R
import com.nasa.apod.databinding.ActivityMainBinding
import com.nasa.apod.ui.view.fragments.PastImageFragment
import com.nasa.apod.ui.view.fragments.RandomImageFragment
import com.nasa.apod.ui.view.fragments.TodayImageFragment
import com.nasa.apod.ui.view.fragments.WelcomeInfoFragment
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

        val welcomeDialog = WelcomeInfoFragment()
        welcomeDialog.show(supportFragmentManager, "infoDialog")

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


