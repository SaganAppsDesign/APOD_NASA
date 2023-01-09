package com.example.apod.ui.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apod.databinding.ActivityMainBinding
import com.example.apod.ui.view.recyclerview.APODAdapter
import com.example.apod.ui.viewmodel.ApodViewModel

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: APODAdapter
    private val apodViewModel: ApodViewModel by viewModels()
    private val apodImages = mutableListOf<String?>()
    private val apodTitle = mutableListOf<String?>()
    private val apodDescrip = mutableListOf<String?>()
    private val apodDate = mutableListOf<String?>()
    private val apodMediaType = mutableListOf<String?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)

        initRecycler()
    }

    private fun initRecycler(){
        adapter = APODAdapter(this, apodImages, apodTitle, apodDescrip, apodDate, apodMediaType)
        binding.rvAPODs.layoutManager = LinearLayoutManager (this)
        binding.rvAPODs.adapter = adapter
    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun searchByDate(query:String){
//        CoroutineScope(Dispatchers.IO).launch {
//            val call = getRetrofit().create(APIService::class.java).getAPODByDate("apod?api_key=$APOD_API_KEY&date=$query&concept_tags=True")
//            val apod = call.body()
//            runOnUiThread {
//                if (call.isSuccessful) {
//                    val images = mutableListOf(apod?.url)
//                    apodImages.clear()
//                    apodImages.addAll(images)
//                    adapter.notifyDataSetChanged()
//
//                } else {
//                    showError()
//                }
//                hideKeyboard()
//            }
//        }
//        }


    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onQueryTextSubmit(query: String?): Boolean {
        apodImages.clear()
        apodTitle.clear()
        apodDescrip.clear()
        apodDate.clear()
        apodMediaType.clear()
        if(!query.isNullOrEmpty()){
            apodViewModel.getApodData(query)
            apodViewModel.apodLiveData.observe(this){
                for (i in 0 until it.size){
                    apodImages.addAll(mutableListOf(it[i]?.url))
                    apodTitle.addAll(mutableListOf(it[i]?.title))
                    apodDescrip.addAll(mutableListOf(it[i]?.explanation))
                    apodDate.addAll(mutableListOf(it[i]?.date))
                    apodMediaType.addAll(mutableListOf(it[i]?.mediaType))
                }
                adapter.notifyDataSetChanged()
            }

            apodViewModel.isLoading.observe(this){
                    binding.pbAPOD.isVisible = it
            }

            hideKeyboard()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}


