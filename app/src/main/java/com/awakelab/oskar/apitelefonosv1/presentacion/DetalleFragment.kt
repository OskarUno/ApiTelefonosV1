package com.awakelab.oskar.apitelefonosv1.presentacion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
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
    ): View {
        binding = FragmentDetalleBinding.inflate(layoutInflater, container, false)
        movilVM.getDetalleMovilVM(idParam)
        initListener()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {

        idParam?.let { it ->
            movilVM.detalleLiveData(it).observe(viewLifecycleOwner) {
                binding.tvNameD.text = it.name
                binding.tvPriceActual.text = "$ " + it.price.toString()
                binding.tvLastPrice.text = "$ " + it.lastPrice.toString()
                binding.imgD.load(it.image)
                binding.tvDescription.text = it.description
                if (it.credit) {
                    binding.tvCredit.visibility = View.VISIBLE
                } else {
                    binding.tvEfectivo.visibility = View.VISIBLE
                }

            }
        }


    }

}