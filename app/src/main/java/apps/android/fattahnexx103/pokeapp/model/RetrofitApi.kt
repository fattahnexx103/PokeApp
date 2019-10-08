package apps.android.fattahnexx103.pokeapp.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    @GET("/api/v2/pokemon")
    fun getPokedex(): Single<PokedexModel>

    @GET("/api/v2/pokemon/{pokeNumber}")
    fun getPokemon(@Path("pokeNumber")pokenumber: String): Single<PokeemonModel>

}