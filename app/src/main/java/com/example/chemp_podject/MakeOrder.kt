package com.example.chemp_podject

import Person
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chemp_podject.databinding.ActivityMakeOrderBinding
import com.example.chemp_podject.fragments.ItemListDialogFragmentAddress
import com.example.chemp_podject.fragments.ItemListDialogFragmentDateTime
import com.example.chemp_podject.models.AddressModel
import com.example.chemp_podject.models.DateTimeModel

class MakeOrder : AppCompatActivity(), ItemListDialogFragmentAddress.Listener, ItemListDialogFragmentDateTime.Listener {
    lateinit var binding: ActivityMakeOrderBinding
    lateinit var addressThis: AddressModel
    lateinit var dateTime: DateTimeModel
    private val adapter = AdapterSelectOrder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        textChecked()
        initOrder()
    }

    fun initOrder(){
        with(binding!!){
            listOrder.layoutManager = GridLayoutManager(this@MakeOrder, 1)
            listOrder.adapter = adapter
            if(Person.listOrder.isNotEmpty()) {
                for (element in Person.listOrder) {
                    adapter.addOrders(element)
                }
            }
        }
    }

    fun textChecked(){
        if(binding.inputTextAddress.text.isNotEmpty() && binding.inputTextDateTime.text.isNotEmpty() && binding.inputTextPhoneNumber.text.isNotEmpty()
            /*&& index != 0*/){
            startActivity(Intent(this@MakeOrder, Oplata::class.java))
        }

    }
    val itemListDialogFragment = ItemListDialogFragmentAddress(this@MakeOrder)
    val itemListDialogFragmentDateTime = ItemListDialogFragmentDateTime(this@MakeOrder)
    fun init(){
        with(binding){
            inputTextAddress.setOnClickListener(){
                itemListDialogFragment.show(supportFragmentManager, "StartAddressFrag")
            }
            inputTextDateTime.setOnClickListener(){

            }
            inputTextDateTime.setOnClickListener(){
                itemListDialogFragmentDateTime.show(supportFragmentManager, "StartFateTimeFragmemt")
            }
            inputTextPhoneNumber.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(inputTextPhoneNumber.text.isNotEmpty()){
                        textPhoneNumber1.setTextColor(Color.GRAY)
                    }
                    else{
                        textPhoneNumber1.setTextColor(Color.RED)
                    }
                }
            })
        }
    }

    override fun GetData(address: AddressModel) {
        addressThis = address
        binding.inputTextAddress.setText(addressThis.address + " кв." + addressThis.kvartira)
        /*itemListDialogFragment.onDestroy()*/
        /*itemListDialogFragment.onStop()*/
        itemListDialogFragment.dismiss()
    }

    override fun GetdateTime(DateTime: DateTimeModel) {
        dateTime = DateTime
        binding.inputTextDateTime.setText(dateTime.date + " " + dateTime.time)
    }
}