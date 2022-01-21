package com.example.roomdb_muamarzidantriantoro_21_xirpl5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_muamarzidantriantoro_21_xirpl5.room.Anime
import kotlinx.android.synthetic.main.list_anime.view.*
import java.util.*

class AnimeAdapter(private val animes: ArrayList<Anime>, private val listener: OnAdapterListener) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_anime, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animes[position]
        holder.view.text_title.text = anime.title
        holder.view.text_title.setOnClickListener {
            listener.onClick( anime )
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate( anime )
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete( anime )
        }
    }

    override fun getItemCount() = animes.size

    class AnimeViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Anime>) {
        animes.clear()
        animes.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(anime: Anime)
        fun onUpdate(anime: Anime)
        fun onDelete(anime: Anime)

    }

}
