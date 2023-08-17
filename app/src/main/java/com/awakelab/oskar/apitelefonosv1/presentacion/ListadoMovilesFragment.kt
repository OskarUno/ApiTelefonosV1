package com.awakelab.oskar.apitelefonosv1.presentacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.awakelab.oskar.apitelefonosv1.databinding.FragmentListadoMovilesBinding

class ListadoMovilesFragment : Fragment() {
    lateinit var binding: FragmentListadoMovilesBinding
    private val movilVM: MovilViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListadoMovilesBinding.inflate(layoutInflater, container, false)
        initAdapter()
        movilVM.getAllMovilesVM()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterMoviles()
        binding.rv.adapter = adapter

        movilVM.movilLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }


}