package apps.android.fattahnexx103.pokeapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import apps.android.fattahnexx103.pokeapp.R
import apps.android.fattahnexx103.pokeapp.adapters.PokemonAbilitiesListAdapter
import apps.android.fattahnexx103.pokeapp.model.PokeemonModel
import apps.android.fattahnexx103.pokeapp.model.Stats
import apps.android.fattahnexx103.pokeapp.util.getProgressDrawable
import apps.android.fattahnexx103.pokeapp.util.loadImageUri
import kotlinx.android.synthetic.main.fragment_pokemon.*


class PokemonFragment : Fragment() {

    var pokemon: PokeemonModel? = null

    private var listAdapter  = PokemonAbilitiesListAdapter(arrayListOf())
    //private var counter = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pokemon = PokemonFragmentArgs.fromBundle(it).pokemon
            pokemon_image.loadImageUri(pokemon?.sprites?.back_default, getProgressDrawable(view.context))
            for(stats in pokemon!!.stats!!){
                if(stats.stat?.name == "speed"){
                    stat_speed_text.text = "SPEED: ${stats.base_stat}"
                }
                else if(stats.stat?.name == "attack"){
                    stat_attack_text.text = "ATTACK :  ${stats.base_stat}"
                }
                else if(stats.stat?.name == "defense"){
                    stat_defense_text.text = "DEFENSE :  ${stats.base_stat}"
                }
            }
            listAdapter.updateNameList(pokemon!!.abilities!!)

        }

        //configure recyclerview
        abilities_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }


}
