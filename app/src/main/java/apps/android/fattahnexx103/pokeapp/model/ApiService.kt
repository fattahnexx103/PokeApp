package apps.android.fattahnexx103.pokeapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    //https://pokeapi.co/api/v2/pokemon/
    private val baseUrl = "https://pokeapi.co/"

    private var count = 0;
    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RetrofitApi::class.java)


    fun getPokemon(number: Int): Single<PokeemonModel>{
        return api.getPokemon(number.toString())
    }
}