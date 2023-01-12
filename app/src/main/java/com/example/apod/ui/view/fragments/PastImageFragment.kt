package com.example.apod.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apod.databinding.FragmentPastImageBinding
import com.example.apod.ui.view.recyclerview.APODAdapter
import com.example.apod.ui.viewmodel.ApodViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PastImageFragment : Fragment() {


    private lateinit var binding : FragmentPastImageBinding
    private lateinit var adapter: APODAdapter
    private val apodViewModel: ApodViewModel by viewModels()
    private val apodImages = mutableListOf<String?>()
    private val apodTitle = mutableListOf<String?>()
    private val apodDescrip = mutableListOf<String?>()
    private val apodDate = mutableListOf<String?>()
    private val apodMediaType = mutableListOf<String?>()
    private var apodNumber = "0"
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPastImageBinding.inflate(inflater, container, false)
        binding = FragmentPastImageBinding.inflate(layoutInflater)

        initRecycler()

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val today = formatter.format(Calendar.getInstance().time)
        val last50 = Calendar.getInstance()
        last50.add(Calendar.DAY_OF_YEAR, -50)
        val lastDay = formatter.format(last50.time)

        loadAPODs(lastDay, today)

        return binding.root
    }

    private fun initRecycler(){
        adapter = APODAdapter(requireActivity(), apodImages, apodTitle, apodDescrip, apodDate, apodMediaType)
        binding.rvAPODs.layoutManager = LinearLayoutManager (requireActivity())
        binding.rvAPODs.adapter = adapter
    }

//    private fun initScrollListener() {
//        binding.rvAPODs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
//                if (!isLoading) {
//                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == 10) {
//                        loadAPODs("20")
//                        isLoading = true
//                    }
//                }
//            }
//        })
//    }

    private fun loadAPODs(today: String, lastDay: String){
        apodViewModel.getApodLast50(today, lastDay)
        apodViewModel.apodLast50LiveData.observe(requireActivity()){
            removeApodList()
            for (i in 0 until it.size){
                apodImages.addAll(mutableListOf(it[i]?.url))
                apodTitle.addAll(mutableListOf(it[i]?.title))
                apodDescrip.addAll(mutableListOf(it[i]?.explanation))
                apodDate.addAll(mutableListOf(it[i]?.date))
                apodMediaType.addAll(mutableListOf(it[i]?.mediaType))
            }
            adapter.notifyDataSetChanged()
        }

        apodViewModel.isLoading.observe(requireActivity()){
            binding.pbAPOD.isVisible = it
        }
    }

    private fun removeApodList(){
        apodImages.clear()
        apodTitle.clear()
        apodDescrip.clear()
        apodDate.clear()
        apodMediaType.clear()
    }
}
