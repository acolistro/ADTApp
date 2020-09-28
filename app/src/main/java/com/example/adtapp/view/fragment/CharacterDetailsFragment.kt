package com.example.adtapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.adtapp.R
import com.example.adtapp.model.data.Character
import kotlinx.android.synthetic.main.character_fragment_layout.*
import kotlinx.android.synthetic.main.character_item_layout.view.*

class CharacterDetailsFragment: Fragment() {

    companion object{
        const val CharacterKey = "LOCATION_KEY"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.character_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getSerializable(CharacterKey)?.let {
            val character = it as Character
            character_location_textview.text = "Character ${character.name} in location: ${character.location.name}"
            Glide.with(requireActivity())
                .load(character.image)
                .placeholder(R.drawable.ic_default)
                .into(character_details_imageview)
        }
    }
}