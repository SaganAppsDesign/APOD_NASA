package com.sagan.apod.ui.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sagan.apod.R
import com.sagan.apod.data.APODRepository
import com.sagan.apod.databinding.ActivityMainBinding
import com.sagan.apod.ui.view.fragments.PastImageFragment
import com.sagan.apod.ui.view.fragments.RandomImageFragment
import com.sagan.apod.ui.view.fragments.TodayImageFragment
import com.sagan.apod.ui.view.fragments.WelcomeInfoFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var repository: APODRepository

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deleteLocalDDBB()
        initBottomMenu()
        loadFragment(TodayImageFragment())

        val welcomeDialog = WelcomeInfoFragment()
        welcomeDialog.show(supportFragmentManager, "infoDialog")

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {}

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

    private fun deleteLocalDDBB(){
        lifecycleScope.launch {
            repository.deleteTable()
        }
    }

    private fun initBottomMenu(){
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
}


