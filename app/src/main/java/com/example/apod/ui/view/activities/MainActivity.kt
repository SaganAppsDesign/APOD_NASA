package com.example.apod.ui.view.activities

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apod.databinding.ActivityMainBinding
import com.example.apod.ui.view.recyclerview.APODAdapter
import com.example.apod.ui.viewmodel.ApodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: APODAdapter
    private val apodViewModel: ApodViewModel by viewModels()
    private val apodImages = mutableListOf<String?>()
    private val apodTitle = mutableListOf<String?>()
    private val apodDescrip = mutableListOf<String?>()
    private val apodDate = mutableListOf<String?>()
    private val apodMediaType = mutableListOf<String?>()
    private var apodNumber = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.svAPOD.setOnQueryTextListener(this)

        initRecycler()

        binding.btNumber.setOnClickListener{
            apodNumber = binding.etNumber.text.toString()

//            if(apodNumber.toInt() > 60 || apodNumber.isEmpty()){
////                binding.btNumber.isEnabled = false
////                binding.btNumber.alpha = 0.5F
//                Toast.makeText(this, "Introduce una cantidad menor a 60, por favor", Toast.LENGTH_SHORT).show()
//                binding.pbAPOD.isVisible = false
//
//            } else{
////                binding.btNumber.isEnabled = true
////                binding.btNumber.alpha = 1F
//
//                apodViewModel.getApodByCount(apodNumber)
//                apodViewModel.apodByCountLiveData.observe(this){
//                    removeApodList()
//                    for (i in 0 until it.size){
//                        apodImages.addAll(mutableListOf(it[i]?.url))
//                        apodTitle.addAll(mutableListOf(it[i]?.title))
//                        apodDescrip.addAll(mutableListOf(it[i]?.explanation))
//                        apodDate.addAll(mutableListOf(it[i]?.date))
//                        apodMediaType.addAll(mutableListOf(it[i]?.mediaType))
//                    }
//                    adapter.notifyDataSetChanged()
//                }
//
//                apodViewModel.isLoading.observe(this){
//                    binding.pbAPOD.isVisible = it
//                }
//                hideKeyboard()
//            }

            apodViewModel.getApodByDate(apodNumber)
            apodViewModel.apodByDateLiveData.observe(this){
                removeApodList()
                    apodImages.addAll(mutableListOf(it?.url))
                    apodTitle.addAll(mutableListOf(it?.title))
                    apodDescrip.addAll(mutableListOf(it?.explanation))
                    apodDate.addAll(mutableListOf(it?.date))
                    apodMediaType.addAll(mutableListOf(it?.mediaType))
                }
                adapter.notifyDataSetChanged()

                apodViewModel.isLoading.observe(this){
                    binding.pbAPOD.isVisible = it
                }
                hideKeyboard()

        }

    }

    private fun initRecycler(){
        adapter = APODAdapter(this, apodImages, apodTitle, apodDescrip, apodDate, apodMediaType)
        binding.rvAPODs.layoutManager = LinearLayoutManager (this)
        binding.rvAPODs.adapter = adapter
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

//    @SuppressLint("NotifyDataSetChanged")
//    override fun onQueryTextSubmit(query: String?): Boolean {
//        if(!query.isNullOrEmpty()){
//            apodViewModel.getApodByCount(query)
//            apodViewModel.apodByCountLiveData.observe(this){
//                removeApodList()
//                for (i in 0 until it.size){
//                    apodImages.addAll(mutableListOf(it[i]?.url))
//                    apodTitle.addAll(mutableListOf(it[i]?.title))
//                    apodDescrip.addAll(mutableListOf(it[i]?.explanation))
//                    apodDate.addAll(mutableListOf(it[i]?.date))
//                    apodMediaType.addAll(mutableListOf(it[i]?.mediaType))
//                }
//                adapter.notifyDataSetChanged()
//            }
//
//            apodViewModel.isLoading.observe(this){
//                    binding.pbAPOD.isVisible = it
//            }
//            hideKeyboard()
//        }
//        return true
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        return true
//    }

    private fun removeApodList(){
        apodImages.clear()
        apodTitle.clear()
        apodDescrip.clear()
        apodDate.clear()
        apodMediaType.clear()
    }
}


