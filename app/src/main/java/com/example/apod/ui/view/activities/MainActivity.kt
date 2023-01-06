package com.example.apod.ui.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apod.R
import com.example.apod.data.network.APIService
import com.example.apod.core.RetrofitHelper.getRetrofit
import com.example.apod.databinding.ActivityMainBinding
import com.example.apod.ui.view.APODAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: APODAdapter
    private val apodImages = mutableListOf<String?>()
    private val apodTitle = mutableListOf<String?>()
    private val apiKey = "TCx9Wm5GIDBfk6tl5QlGA9Vf1fc7jU48f3SA7fcq"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        initRecycler()
    }

    private fun initRecycler(){
        adapter = APODAdapter(apodImages, apodTitle)
        binding.rvAPODs.layoutManager = LinearLayoutManager (this)
        binding.rvAPODs.adapter = adapter
    }


    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByDate(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAPODByDate("apod?api_key=$apiKey&date=$query&concept_tags=True")
            val apod = call.body()
            //Para poder salir de esa corrutina utilizaremos runOnUiThread{} y lo que esté entre esas llaves se hará en el hilo principal
            // aunque esté dentro de una corrutina.
            runOnUiThread {
                if (call.isSuccessful) {
                    val images = mutableListOf(apod?.url)
                    apodImages.clear()
                    apodImages.addAll(images)
                    adapter.notifyDataSetChanged()

                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByCount(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAPODByCount("apod?api_key=$apiKey&count=$query")
            val apod = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    apodImages.clear()
                    apodTitle.clear()
                    for(i in 0 until apod?.size!!){
                       apodImages.addAll(mutableListOf(apod[i].url))
                       apodTitle.addAll(mutableListOf(apod[i].title))
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByCount(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}


