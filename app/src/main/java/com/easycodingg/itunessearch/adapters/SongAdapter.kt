package com.easycodingg.itunessearch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easycodingg.itunessearch.R
import com.easycodingg.itunessearch.model.ITunesResponseItem
import kotlinx.android.synthetic.main.song_item_layout.view.*
import java.util.*

class SongAdapter(
    var list: List<ITunesResponseItem>
): RecyclerView.Adapter<SongAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]

        holder.itemView.apply {
            tvType.text = currentItem.wrapperType.capitalize(Locale.ROOT)
            tvTrackName.text = currentItem.trackName
            tvArtist.text = currentItem.artistName
        }
    }
}