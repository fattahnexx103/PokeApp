package apps.android.fattahnexx103.pokeapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import apps.android.fattahnexx103.pokeapp.R
import apps.android.fattahnexx103.pokeapp.adapters.PokedexListAdapter
import apps.android.fattahnexx103.pokeapp.model.*
import apps.android.fattahnexx103.pokeapp.viewModel.PokedexViewModel
import kotlinx.android.synthetic.main.fragment_pokedex.*


class PokedexFragment : Fragment() {

    private lateinit var viewModel: PokedexViewModel
    private var listAdapter  = PokedexListAdapter(arrayListOf())
    private var counter = 1

    private val pokemonObserver = Observer<PokeemonModel>{
        if(it != null){
            listAdapter.updateNameList(it)
        }
    }

//    private val isItemLoadedObserver = Observer<Boolean>{isItemLoaded ->
//        if(isItemLoaded){
//
//        }else{
//
//        }
//    }

    private val loadingObserver = Observer<Boolean>{isLoading ->
        if(isLoading){
            loading_progressbar.visibility = View.VISIBLE
            listError_textView.visibility = View.GONE

        }else{
            loading_progressbar.visibility = View.GONE
        }
    }

    private val listErrorObserver = Observer<Boolean>{isError ->
        if(isError){
            listError_textView.visibility = View.VISIBLE
            pokedex_recyclerView.visibility = View.GONE
        }else{
            listError_textView.visibility = View.GONE
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //instantiate view model through viewmodel providers class
        viewModel = ViewModelProviders.of(this)
            .get(PokedexViewModel::class.java)

        viewModel.loading.observe(this,loadingObserver )
        viewModel.loadingError.observe(this, listErrorObserver)
        viewModel.pokemonData.observe(this,pokemonObserver)
        viewModel.refresh(counter)

        //configure recyclerview
        pokedex_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        pokedex_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!recyclerView.canScrollVertically(1)){
                    Toast.makeText(context, "Last", Toast.LENGTH_LONG).show()
                    counter+=19
                    viewModel.refresh(counter)
                }
            }
        })



    }


}
