package com.sagan.apod.ui.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagan.apod.data.APODRepository
import com.sagan.apod.databinding.FragmentPastImageBinding
import com.sagan.apod.ui.view.recyclerview.APODAdapter
import com.sagan.apod.ui.viewmodel.ApodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PastImageFragment:Fragment(){

    private lateinit var binding : FragmentPastImageBinding
    private lateinit var adapter: APODAdapter
    private val apodViewModel: ApodViewModel by viewModels()
    private val apodImages = mutableListOf<String?>()
    private val apodTitle = mutableListOf<String?>()
    private val apodDescrip = mutableListOf<String?>()
    private val apodDate = mutableListOf<String?>()
    private val apodMediaType = mutableListOf<String?>()
    private val apodThumbnail = mutableListOf<String?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val last1 = Calendar.getInstance()
        val last50 = Calendar.getInstance()
        last1.add(Calendar.DAY_OF_YEAR, -1)
        last50.add(Calendar.DAY_OF_YEAR, -30)
        val lastDay = formatter.format(last50.time)
        val yesterday = formatter.format(last1.time)

        loadAPODs(lastDay, yesterday)
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
        return binding.root
    }

    private fun initRecycler(){
        adapter = APODAdapter(requireActivity(), apodImages, apodTitle, apodDescrip, apodDate, apodMediaType, apodThumbnail)
        binding.rvAPODs.layoutManager = LinearLayoutManager (requireActivity())
        binding.rvAPODs.adapter = adapter
    }

    private fun loadAPODs(lastDay: String, yesterday: String){
        apodViewModel.getApodLast30(lastDay, yesterday)
        apodViewModel.apodLast50LiveData.observe(requireActivity()){
            removeApodList()
            for (i in 0 until it.size){
                apodImages.addAll(mutableListOf(it[i]?.url))
                apodTitle.addAll(mutableListOf(it[i]?.title))
                apodDescrip.addAll(mutableListOf(it[i]?.explanation))
                apodDate.addAll(mutableListOf(it[i]?.date))
                apodMediaType.addAll(mutableListOf(it[i]?.mediaType))
                apodThumbnail.addAll(mutableListOf(it[i]?.thumbnail_url))
            }
            adapter.notifyDataSetChanged()

        }
        apodViewModel.isLoading.observe(requireActivity()){
            binding.animationView.isVisible = it
            binding.tvLoading.isVisible = it
            initAnimation()
        }
    }

    private fun removeApodList(){
        apodImages.clear()
        apodTitle.clear()
        apodDescrip.clear()
        apodDate.clear()
        apodMediaType.clear()
    }

    private fun initAnimation(){
        binding.animationView.playAnimation()
    }
}
