package com.sagan.apod.ui.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sagan.apod.R
import com.sagan.apod.databinding.FragmentByDateImageBinding
import com.sagan.apod.ui.view.activities.DetailActivity
import com.sagan.apod.ui.viewmodel.ApodViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ByDateImageFragment : Fragment() {

    private lateinit var binding : FragmentByDateImageBinding
    private val apodViewModel: ApodViewModel by viewModels()
    private val apodImages = mutableListOf<String?>()
    private val apodTitle = mutableListOf<String?>()
    private val apodDescrip = mutableListOf<String?>()
    private val apodDate = mutableListOf<String?>()
    private val apodMediaType = mutableListOf<String?>()
    private val apodThumbnail = mutableListOf<String?>()
    var today = "1995-06-16"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       }

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentByDateImageBinding.inflate(inflater, container, false)
        binding.avAstronaut.isVisible = true
        binding.avAstronaut.playAnimation()
        binding.avAstronaut.repeatCount = 10000
        binding.etDatePicker.setText(today)
        initViewModel()
        binding.etDatePicker.setOnClickListener{
            showDatePickerDialog()
        }

        binding.btDataPicker.setOnClickListener{
            initViewModel()
        }
        return binding.root
    }

    private fun passData (title: String?, image: String?, description: String?, mediaType: String?){
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("image", image)
        intent.putExtra("description", description)
        intent.putExtra("mediaType", mediaType)

        context?.startActivity(intent)
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { year, month, day -> onDateSelected(year, month, day) }
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    private fun onDateSelected(year: Int, month: Int, day: Int){
        this.binding.etDatePicker.setText("$year-${month+1}-$day")
    }

    private fun initViewModel(){
        today = binding.etDatePicker.text.toString()
        apodViewModel.getApodByDate(today)
        apodViewModel.apodByDateLiveData.observe(viewLifecycleOwner){ it ->
            removeApodList()
            apodImages.addAll(mutableListOf(it?.url))
            apodTitle.addAll(mutableListOf(it?.title))
            apodDescrip.addAll(mutableListOf(it?.explanation))
            apodDate.addAll(mutableListOf(it?.date))
            apodMediaType.addAll(mutableListOf(it?.mediaType))
            apodThumbnail.addAll(mutableListOf(it?.thumbnail_url))

            if (it?.mediaType == "other"){
                Picasso.get().load(R.drawable.no_image).into(binding.ivApodFragment)
                binding.tvTitle.visibility = View.GONE
                binding.tvDate.visibility = View.GONE
                binding.bnShare.visibility = View.GONE
                binding.tvMediaType.visibility = View.GONE
                binding.bnShare.isEnabled = false
                binding.animationView.visibility = View.GONE

            } else {
                    binding.tvTitle.text = apodTitle[0]
                    binding.tvDate.text = apodDate[0]

                    if(apodMediaType[0] == "video"){
                        Picasso.get().load(apodThumbnail[0]).into(binding.ivApodFragment)
                    } else {
                        Picasso.get().load(apodImages[0]).into(binding.ivApodFragment)
                    }
                    binding.tvMediaType.text = apodMediaType[0]

                    binding.ivApodFragment.setOnClickListener(){
                        passData(apodTitle[0], apodImages[0], apodDescrip[0], apodMediaType[0])
                    }
                    apodViewModel.isLoading.observe(viewLifecycleOwner){
                        binding.animationView.isVisible = it
                        initAnimation()
                    }
                    binding.bnShare.setOnClickListener {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, apodImages[0])
                        startActivity(Intent.createChooser(shareIntent, "Share APOD"))
                    }
                }
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