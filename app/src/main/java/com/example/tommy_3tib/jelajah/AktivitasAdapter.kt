package com.example.tommy_3tib.jelajah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tommy_3tib.databinding.ItemAktivitasBinding

class AktivitasAdapter(
    private val aktivitasList: List<AktivitasModel>,
    private val onItemClick: (AktivitasModel) -> Unit
) : RecyclerView.Adapter<AktivitasAdapter.AktivitasViewHolder>() {

    inner class AktivitasViewHolder(val binding: ItemAktivitasBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AktivitasViewHolder {
        val binding = ItemAktivitasBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AktivitasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AktivitasViewHolder, position: Int) {
        val item = aktivitasList[position]

        with(holder.binding) {
            tvNama.text = item.name
            tvDurasi.text = item.duration

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgAktivitas)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = aktivitasList.size
}