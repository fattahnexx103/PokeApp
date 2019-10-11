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

class PokedexViewModel(application: Application) : AndroidViewModel(application) {

    val pokemonData by lazy {MutableLiveData<PokeemonModel>()}
//    val isItemLoaded by lazy {MutableLiveData<Boolean>()}
    val loadingError by lazy { MutableLiveData<Boolean>()}
    val loading by lazy { MutableLiveData<Boolean>() }


    private val disposable = CompositeDisposable()
    private val apiService = ApiService()
    private var isItemLoaded = true
    private var counter = 1


    fun refresh(count: Int) {
        counter = 1
        getPokemon(count)
        loading.value = false

    }


    private fun getPokemon(number: Int) {

        disposable.add(
            apiService.getPokemon(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PokeemonModel>() {
                    override fun onSuccess(pokemon: PokeemonModel) {
                        pokemonData.value = pokemon
                        if(counter < 18){
                            getSecondPokemon(number + 1 )
                            counter += 1
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        isItemLoaded = false
                        loadingError.value = true
                    }
                })
        )
    }

    private fun getSecondPokemon(number: Int) {

        disposable.add(
            apiService.getPokemon(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PokeemonModel>() {
                    override fun onSuccess(pokemon: PokeemonModel) {
                        pokemonData.value = pokemon
                        if(counter < 19){
                            getPokemon(number+1)
                            counter+= 1
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

//    override fun onCleared() {
//        super.onCleared()
//        disposable.clear()
//    }


}