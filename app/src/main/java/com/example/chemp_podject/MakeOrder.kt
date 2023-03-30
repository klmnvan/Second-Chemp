package com.example.chemp_podject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.chemp_podject.databinding.ActivityMakeOrderBinding
import com.example.chemp_podject.fragments.ItemListDialogFragmentAddress
import com.example.chemp_podject.models.AddressModel

class MakeOrder : AppCompatActivity(), ItemListDialogFragmentAddress.Listener {
    lateinit var binding: ActivityMakeOrderBinding
    lateinit var addressThis: AddressModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        textChecked()
    }

    fun textChecked(){
        if(binding.inputTextAddress.text.isNotEmpty() && binding.inputTextDateTime.text.isNotEmpty() && binding.inputTextPhoneNumber.text.isNotEmpty()
            /*&& index != 0*/){
            startActivity(Intent(this@MakeOrder, Oplata::class.java))
        }

    }

    fun init(){
        with(binding){
            /*spinnerPacient.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    index = position
                    if(position == 0){
                        textWhoAnalizs1.setTextColor(Color.RED)
                    }
                    else{
                        textWhoAnalizs1.setTextColor(Color.BLACK)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }*/
            inputTextAddress.setOnClickListener(){
                val itemListDialogFragment = ItemListDialogFragmentAddress(this@MakeOrder)
                itemListDialogFragment.show(supportFragmentManager, "StartAddressFrag")
            }
            inputTextDateTime.setOnClickListener(){

            }
            /*inputTextAddress.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(inputTextAddress.text.isNotEmpty()){
                        textAdress1.setTextColor(Color.GRAY)
                    }
                    else{
                        textAdress1.setTextColor(Color.RED)
                    }
                }
            })*/
            inputTextDateTime.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(inputTextDateTime.text.isNotEmpty()){
                        textDateTime1.setTextColor(Color.GRAY)
                    }
                    else{
                        textDateTime1.setTextColor(Color.RED)
                    }
                }
            })
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
    }
}