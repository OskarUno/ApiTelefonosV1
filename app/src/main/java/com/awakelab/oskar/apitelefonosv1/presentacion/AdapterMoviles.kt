package com.awakelab.oskar.apitelefonosv1.presentacion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        fun render(movilItem: MovilEntity) {
            moviles.tvName.text = movilItem.name
        }

    }
}