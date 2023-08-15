package com.awakelab.oskar.apitelefonosv1.presentacion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.awakelab.oskar.apitelefonosv1.R
import com.awakelab.oskar.apitelefonosv1.data.local.MovilEntity
import com.awakelab.oskar.apitelefonosv1.databinding.ItemMovilesBinding

class AdapterMoviles : RecyclerView.Adapter<AdapterMoviles.ItemMovilesViewHolder>() {

    lateinit var binding: ItemMovilesBinding
    private val listItemMoviles = mutableListOf<MovilEntity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AdapterMoviles.ItemMovilesViewHolder {
       binding = ItemMovilesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemMovilesViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return listItemMoviles.size
    }

    override fun onBindViewHolder(holder: AdapterMoviles.ItemMovilesViewHolder, position: Int) {
        val movilItem = listItemMoviles[position]
        holder.render(movilItem)
    }

    fun setData(moviles: List<MovilEntity>){
        this.listItemMoviles.clear()
        this.listItemMoviles.addAll(moviles)
        notifyDataSetChanged()
    }


    class ItemMovilesViewHolder(val moviles : ItemMovilesBinding) :RecyclerView.ViewHolder(moviles.root) {
        @SuppressLint("SetTextI18n")
        fun render(movilItem: MovilEntity) {
            moviles.tvName.text = movilItem.name
            moviles.tvPrice.text = "$ " + movilItem.price.toString()
            moviles.img.load(movilItem.image)
            moviles.cv.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", movilItem.id)
                Navigation.findNavController(moviles.root).navigate(R.id.action_listadoMovilesFragment_to_detalleFragment,bundle)
            }
        }

    }
}