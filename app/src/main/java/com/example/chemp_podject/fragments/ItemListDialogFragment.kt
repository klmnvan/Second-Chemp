package com.example.chemp_podject.fragments

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.databinding.ItemFragmentBlockBinding

class ItemListDialogFragment : BottomSheetDialogFragment() {
    private var _binding: ItemFragmentBlockBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var block: BlockModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemFragmentBlockBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        block = arguments?.getSerializable("key") as BlockModel
        with(binding){
            textName.text = block.name
            textAllDescription.text = block.description
            textAllPodgotovka.text = block.preparation
            textAllBiomaterial.text = block.bio
            textAllResults.text = block.time_result
            buttonDobavit.text = "Добавить за " + block.price + " ₽"
        }
    }
}