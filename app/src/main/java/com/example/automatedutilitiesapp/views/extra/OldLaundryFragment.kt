package com.example.automatedutilitiesapp.views.extra

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.automatedutilitiesapp.databinding.FragmentOldLaundryBinding
import kotlinx.android.synthetic.main.fragment_old_laundry.*
import sun.bob.mcalendarview.listeners.OnDateClickListener
import sun.bob.mcalendarview.vo.DateData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class OldLaundryFragment : Fragment() {

    private lateinit var binding: FragmentOldLaundryBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOldLaundryBinding.inflate(inflater)

       //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
       //val dates: ArrayList<DateData> = ArrayList()
       //dates.add(DateData(2020, 3, 26))
       //dates.add(DateData(2020, 3, 12))

       //for (i in 0 until dates.size) {
       //   binding.calendarView.markDate(
       //        dates[i].year,
       //        dates[i].month,
       //        dates[i].day
       //    )
       //}


       //binding.calendarView.setOnDateClickListener(object : OnDateClickListener() {

       //    override fun onDateClick(view: View, date: DateData) {
       //        val data = LocalDate.parse(date.toString(), formatter)

       //        if(dates.contains(data)){
       //            Toast.makeText(context, "zazanczona", Toast.LENGTH_LONG).show()
       //        }else {
       //            Toast.makeText(context, "nie zazanczona", Toast.LENGTH_LONG).show()
       //        }
       //    }
       //})



        return binding.root
    }


}
