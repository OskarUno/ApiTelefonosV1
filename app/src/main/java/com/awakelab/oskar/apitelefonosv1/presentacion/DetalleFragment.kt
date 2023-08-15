package com.awakelab.oskar.apitelefonosv1.presentacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.awakelab.oskar.apitelefonosv1.databinding.FragmentDetalleBinding

private const val ARG_PARAM1 = "id"

class DetalleFragment : Fragment() {
    private lateinit var binding: FragmentDetalleBinding
    private val movilVM: MovilViewModel by activityViewModels()

    private var idParam: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idParam = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetalleBinding.inflate(layoutInflater,container,false)
        movilVM.getDetalleMovilVM(idParam)
        initListener()

        return binding.root
    }

    private  fun initListener() {

        idParam?.let { it ->
            movilVM.detalleLiveData(it).observe(viewLifecycleOwner){
               binding.tvNameD.text = idParam.toString()
            }
        }

    }

}