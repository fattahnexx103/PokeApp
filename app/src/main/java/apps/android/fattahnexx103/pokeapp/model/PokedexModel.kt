package apps.android.fattahnexx103.pokeapp.model

import com.google.gson.annotations.SerializedName

data class PokedexModel(

    val count: Int?,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val results: List<Results>
)


data class Results(
    val name: String?,
    val url: String?
)
