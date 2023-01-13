package com.sagan.apod.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sagan.apod.databinding.FragmentTodayImageBinding
import com.sagan.apod.ui.view.activities.DetailActivity
import com.sagan.apod.ui.viewmodel.ApodViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TodayImageFragment : Fragment() {

    private lateinit var binding : FragmentTodayImageBinding
    private val apodViewModel: ApodViewModel by viewModels()
    private val apodImages = mutableListOf<String?>()
    private val apodTitle = mutableListOf<String?>()
    private val apodDescrip = mutableListOf<String?>()
    private val apodDate = mutableListOf<String?>()
    private val apodMediaType = mutableListOf<String?>()

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        binding = FragmentTodayImageBinding.inflate(inflater, container, false)

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val current = formatter.format(time)

        apodViewModel.getApodByDate(current)
        apodViewModel.apodByDateLiveData.observe(viewLifecycleOwner){
            removeApodList()
            apodImages.addAll(mutableListOf(it?.url))
            apodTitle.addAll(mutableListOf(it?.title))
            apodDescrip.addAll(mutableListOf(it?.explanation))
            apodDate.addAll(mutableListOf(it?.date))
            apodMediaType.addAll(mutableListOf(it?.mediaType))
            Log.i("apodImages[0]", apodImages[0].toString())

            Picasso.get().load(apodImages[0]).into(binding.ivApodFragment)
            binding.tvTitle.text = apodTitle[0]
            binding.tvDate.text = apodDate[0]
            binding.tvMediaType.text = apodMediaType[0]

            binding.ivApodFragment.setOnClickListener(){
                passData(apodTitle[0], apodImages[0], apodDescrip[0], apodMediaType[0])
            }
        }
        apodViewModel.isLoading.observe(viewLifecycleOwner){
            binding.pbAPOD.isVisible = it
        }

        binding.bnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, apodImages[0])
            startActivity(Intent.createChooser(shareIntent, "Share APOD"))
        }

       return binding.root
    }

    private fun removeApodList(){
        apodImages.clear()
        apodTitle.clear()
        apodDescrip.clear()
        apodDate.clear()
        apodMediaType.clear()
    }

    private fun passData (title: String?, image: String?, description: String?, mediaType: String?){
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("image", image)
        intent.putExtra("description", description)
        intent.putExtra("mediaType", mediaType)

        context?.startActivity(intent)
     }
}