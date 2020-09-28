package com.example.adtapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adtapp.R
import com.example.adtapp.model.data.Character
import kotlinx.android.synthetic.main.character_item_layout.view.*

class CharacterAdapter(var characters: MutableList<Character>, val characterPickDelegate: CharacterPickDelegate): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    interface CharacterPickDelegate {
        fun selectCharacter(character: Character)
    }
    inner class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item_layout, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.apply {
            with(characters[position]){
                this@apply.setOnClickListener {
                    characterPickDelegate.selectCharacter(this)
                }

                Log.d("TAG_X", url)
                Glide.with(holder.itemView.context)
                    .load(image)
                    .placeholder(R.drawable.ic_default)
                    .into(this@apply.character_imageview)
                this@apply.name_textview.text = name
                this@apply.species_textview.text = species
                this@apply.status_textview.text = status
            }
        }
    }
}