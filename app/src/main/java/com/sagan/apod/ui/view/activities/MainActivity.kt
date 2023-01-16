package com.sagan.apod.ui.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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

        //Borrado inicial de la BBDD local
        lifecycleScope.launch {
           repository.deleteAPODLast30FromDataBase()
        }

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
    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy() Activity","onDestroy()")



    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {}

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}


