package com.example.chemp_podject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chemp_podject.MakeOrder
import com.example.chemp_podject.databinding.ItemFragmentAddressBinding
import com.example.chemp_podject.models.AddressModel
import com.example.chemp_podject.models.PolzovatModel
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
            //getActivity()?.getFragmentManager()?.popBackStack();
            address = AddressModel(binding!!.inputTextAddress.text.toString(), binding!!.inputTextDolgota.text.toString(), binding!!.inputTextShirota.text.toString(),
                binding!!.inputTextVisota.text.toString(), binding!!.inputTextKvartira.text.toString(), binding!!.inputTextPodyezd.text.toString(),
                binding!!.inputTextEtazh.text.toString(), binding!!.inputTextDomophon.text.toString())
            listener.GetData(address)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        putAddress(listener)
    }

    interface Listener{
        fun GetData(address: AddressModel)
    }

}