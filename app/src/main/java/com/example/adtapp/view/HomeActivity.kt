package com.example.adtapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.adtapp.R
import com.example.adtapp.model.data.Character
import com.example.adtapp.view.adapter.CharacterAdapter
import com.example.adtapp.view.fragment.CharacterDetailsFragment
import com.example.adtapp.viewmodel.RickyMortyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), CharacterAdapter.CharacterPickDelegate {

    private lateinit var viewModel: RickyMortyViewModel
    private val characterDetailsFragment = CharacterDetailsFragment()

    private val characterAdapter = CharacterAdapter(mutableListOf(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel =
            ViewModelProviders.of(this).get(RickyMortyViewModel::class.java) //For the sake of time

        main_recyclerview.adapter = characterAdapter

        viewModel.getCharacters().observe(this, Observer {
            updateView(it.results)
        })
    }

    private fun updateView(characters: List<Character>) {
        characterAdapter.characters = characters as MutableList<Character>
        characterAdapter.notifyDataSetChanged()
    }

    override fun selectCharacter(character: Character) {
        val bundle = Bundle().also {
            it.putSerializable(CharacterDetailsFragment.CharacterKey, character)
        }
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.main_frame, characterDetailsFragment.also {
                it.arguments = bundle
            })
            .addToBackStack(characterDetailsFragment.tag)
            .commit()
    }
}


//1:28 PM
//Create a sample application to show the list of Characters from the show Rick and Morty.
//
//API Doc: https://rickandmortyapi.com/documentation/#get-all-characters
//
//The application:
//• Should fetch the list of characters (https://rickandmortyapi.com/api/character/)
//• Should show a scrollable list of those characters including Name, Status, Species and an image of the character
//• When a character is clicked, it should show a popup or dialog stating the character’s location Name (location.name)
//
//
//Plus Points:
//• Can work both on Landscape (Grid View) and Portrait (Vertical List)
//• The list of characters consists of several pages; can implement fetching additional pages of characters as the User scrolls
//
//
//NOTE:
//• The code must be shared in GitHub or BitBucket, with frequent commits.

// [3:13 PM] Amick, Patrick
//knaftel@teksystems.com


