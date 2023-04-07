package com.example.chemp_podject.fragments

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chemp_podject.MakeOrder
import com.example.chemp_podject.R
import com.example.chemp_podject.databinding.ItemFragmentDateTimeBinding
import com.example.chemp_podject.models.DateTimeModel
import java.util.Queue

class ItemListDialogFragmentDateTime (private val listener: MakeOrder): BottomSheetDialogFragment() {

    private var _binding: ItemFragmentDateTimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var time: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =
            ItemFragmentDateTimeBinding.inflate(inflater, container, false)
        return binding.root

    }
     fun putDateTime(listener: MakeOrder){
         var count = 0
         binding.buttonStrelkaVniz.setOnClickListener(){
             with(binding){
                 if(count == 0){
                     linDatePicker.visibility = View.VISIBLE
                     count = 1
                 }
                 else{
                     linDatePicker.visibility = View.GONE
                     count = 0
                 }
             }
         }
         binding.datePicker.setOnDateChangeListener { view, year, month, dayOfMonth ->
             binding.putTextDateCalendar.text = "$dayOfMonth.${month + 1}.$year"
         }
         binding.buttonConfirm.setOnClickListener(){
             var DateTime: DateTimeModel
             DateTime = DateTimeModel(binding.putTextDateCalendar.text.toString(), time)
             listener.GetdateTime(DateTime)
         }
         binding.textTime10.setOnClickListener(){
             time = binding.textTime10.text.toString()
             with(binding){
                 textTime10.setBackgroundResource(R.drawable.button_home_blue_style)
                 textTime13.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime14.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime15.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime16.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime18.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime19.setBackgroundResource(R.drawable.button_home_white_style)
             }
         }
         binding.textTime13.setOnClickListener(){
             time = binding.textTime13.text.toString()
             with(binding){
                 textTime10.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime13.setBackgroundResource(R.drawable.button_home_blue_style)
                 textTime14.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime15.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime16.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime18.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime19.setBackgroundResource(R.drawable.button_home_white_style)
             }
         }
         binding.textTime14.setOnClickListener(){
             time = binding.textTime14.text.toString()
             with(binding){
                 textTime10.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime13.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime14.setBackgroundResource(R.drawable.button_home_blue_style)
                 textTime15.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime16.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime18.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime19.setBackgroundResource(R.drawable.button_home_white_style)
             }
         }
         binding.textTime15.setOnClickListener(){
             time = binding.textTime15.text.toString()
             with(binding){
                 textTime10.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime13.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime14.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime15.setBackgroundResource(R.drawable.button_home_blue_style)
                 textTime16.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime18.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime19.setBackgroundResource(R.drawable.button_home_white_style)
             }
         }
         binding.textTime16.setOnClickListener(){
             time = binding.textTime16.text.toString()
             with(binding){
                 textTime10.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime13.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime14.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime15.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime16.setBackgroundResource(R.drawable.button_home_blue_style)
                 textTime18.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime19.setBackgroundResource(R.drawable.button_home_white_style)
             }
         }
         binding.textTime18.setOnClickListener(){
             time = binding.textTime18.text.toString()
             with(binding){
                 textTime10.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime13.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime14.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime15.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime16.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime18.setBackgroundResource(R.drawable.button_home_blue_style)
                 textTime19.setBackgroundResource(R.drawable.button_home_white_style)
             }
         }
         binding.textTime19.setOnClickListener(){
             time = binding.textTime19.text.toString()
             with(binding){
                 textTime10.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime13.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime14.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime15.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime16.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime18.setBackgroundResource(R.drawable.button_home_white_style)
                 textTime19.setBackgroundResource(R.drawable.button_home_blue_style)
             }
         }
     }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        putDateTime(listener)
    }

    interface Listener{
        fun GetdateTime(dateTime: DateTimeModel)
    }
}