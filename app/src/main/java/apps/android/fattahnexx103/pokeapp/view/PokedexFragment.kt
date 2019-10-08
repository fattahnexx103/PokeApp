package apps.android.fattahnexx103.pokeapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import apps.android.fattahnexx103.pokeapp.R
import apps.android.fattahnexx103.pokeapp.model.Forms
import apps.android.fattahnexx103.pokeapp.model.PokedexModel
import apps.android.fattahnexx103.pokeapp.model.Results
import apps.android.fattahnexx103.pokeapp.viewModel.PokedexViewModel
import kotlinx.android.synthetic.main.fragment_pokedex.*


class PokedexFragment : Fragment() {

    private lateinit var viewModel: PokedexViewModel
    private val listAdapter  = PokedexListAdapter(arrayListOf())

    private val listNamesObserver = Observer<List<Forms>>{ list ->
        list?.let{
            listAdapter.updateNameList(it)
        }
    }

//    private val listOsbserver = Observer<List<Results>>{
//        it?.let {
//            pokedex_recyclerView.visibility = View.VISIBLE
//            listAdapter.updateList(it)
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

        viewModel.pokemonNamesList.observe(this,listNamesObserver )
        viewModel.loading.observe(this,loadingObserver )
        viewModel.loadingError.observe(this, listErrorObserver)
        //viewModel.pokemonList.observe(this, listOsbserver)
        viewModel.refresh()

        //configure recyclerview
        pokedex_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }


}
