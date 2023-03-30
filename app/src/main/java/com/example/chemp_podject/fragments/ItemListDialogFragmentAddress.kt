package com.example.chemp_podject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chemp_podject.MakeOrder
import com.example.chemp_podject.databinding.ItemFragmentAddressBinding
import com.example.chemp_podject.models.AddressModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemListDialogFragmentAddress(private val listener: MakeOrder) : BottomSheetDialogFragment() {

    private var _binding: ItemFragmentAddressBinding? = null

    private val binding get() = _binding!!
    lateinit var address: AddressModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ItemFragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun putAddress(listener: Listener){
        binding.buttonConfirm.setOnClickListener(){
            Toast.makeText(context, "произошел пипец", Toast.LENGTH_SHORT).show()
            getActivity()?.getFragmentManager()?.popBackStack();
            //эта штука уведомление на экран выводит
            address.address = binding!!.inputTextDolgota.text.toString()
            address.domophon = binding!!.inputTextDomophon.text.toString()
            address.etazh = binding!!.inputTextEtazh.text.toString()
            address.kvartira = binding!!.inputTextKvartira.text.toString()
            address.podyezd = binding!!.inputTextPodyezd.text.toString()
            address.vicota = binding!!.inputTextVisota.text.toString()
            address.shirota = binding!!.inputTextShirota.text.toString()
            /*listener.GetData(address)
            dismiss()*/
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*putAddress(listener)*/
    }

    interface Listener{
        fun GetData(address: AddressModel)
    }

}