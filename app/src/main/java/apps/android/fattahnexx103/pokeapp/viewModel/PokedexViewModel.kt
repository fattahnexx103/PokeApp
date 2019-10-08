package apps.android.fattahnexx103.pokeapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import apps.android.fattahnexx103.pokeapp.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PokedexViewModel(application: Application): AndroidViewModel(application) {

    val pokemonList by lazy { MutableLiveData<List<Results>>() }
    val pokemonNamesList by lazy {MutableLiveData<List<Forms>>()}
    val pokemonName by lazy {MutableLiveData<String>()}
    val loadingError by lazy {MutableLiveData<Boolean>()}
    val loading by lazy {MutableLiveData<Boolean>()}
    val loadingPokemon by lazy {MutableLiveData<Boolean>()}


    private val disposable = CompositeDisposable()
    private val apiService = ApiService()



    fun refresh(){
        loading.value = true
        getPokemon(53)
        getPokemon(25)
        getPokemon(11)
        //getPokedexData()
    }

    private fun getPokedexData() {
        disposable.add(
            apiService.getPokedex() //calling the apiService class to execute the getPokedex which does the retrofit call
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PokedexModel>() {
                    override fun onSuccess(listObject: PokedexModel) {
                        loadingError.value = false
                        Log.d("RESULT OBJECT:", "%%% ${listObject.results.get(3)} %%%")
                        pokemonList.value = listObject.results //the list is now the list value since we got a response
                        loading.value = false

//                        var listCount: Int? = listObject.results.size
//                        getPokemon(2)
//                        for (count in 0..listCount!!){
//                            getPokemon(count)
//                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        pokemonList.value = null //set the list to null
                        loadingError.value = true
                    }
                })
        )
    }

    private fun getPokemon(number: Int){
            disposable.add(
                apiService.getPokemon(number)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<PokeemonModel>() {
                        override fun onSuccess(pokemon: PokeemonModel) {
                         //   Log.d("POKEMON IS .... ",  " .. ${pokemon.forms[number].name}")
                            pokemonNamesList.value = pokemon.forms
                            pokemonName.value = pokemon.forms[0].name
                            loading.value = false
                            loadingPokemon.value = false;
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                            loading.value = false
                            pokemonList.value = null //set the list to null
                            loadingError.value = true
                        }

                    })
                            )
        }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}