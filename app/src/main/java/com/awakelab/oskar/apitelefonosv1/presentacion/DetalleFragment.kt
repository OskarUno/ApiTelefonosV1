package com.awakelab.oskar.apitelefonosv1.presentacion

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        movilVM.getDetalleMovilVM(idParam)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetalleBinding.inflate(layoutInflater, container, false)
        movilVM.getDetalleMovilVM(idParam)
        initListener()
        movilVM.getDetalleMovilVM(idParam)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "IntentReset")
    private fun initListener() {

        idParam?.let { it ->
            //   if(it != null) {
            movilVM.detalleLiveData(it).observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.tvNameD.text = it.name
                    binding.tvId.text = it.id.toString()
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

        binding.cvContactar.setOnClickListener {
            val producto = binding.tvNameD.text.toString()
            val id = binding.tvId.text.toString()
            enviarEmail(producto)
        }
    }

    private fun enviarEmail(producto: String) {

        val para = arrayOf("info@novaera.cl")
        val copia = arrayOf("")
        val asunto = "Consulta " + producto + " id " + idParam
        val mensaje =
            "Hola \nVi el movil " + producto + " de codigo " + idParam + " y me gustaria que me contactaran a este correo o al siguiente numero _________ \n\nQuedo atento"

        val emailIntent = Intent(Intent.ACTION_SEND)
        Uri.parse("mailto:").also { emailIntent.data = it }
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, para)
        emailIntent.putExtra(Intent.EXTRA_CC, copia)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje)

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            Log.i("Detalle Fragment Email", "Finished sending email...")
        } catch (ex: ActivityNotFoundException) {
            Log.i("Detalle Fragment Email", "Email no enviado")
        }
    }

}