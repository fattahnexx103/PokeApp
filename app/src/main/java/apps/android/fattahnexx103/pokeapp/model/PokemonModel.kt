package apps.android.fattahnexx103.pokeapp.model

import com.google.gson.annotations.SerializedName

data class PokeemonModel(

    @SerializedName("abilities")
    val abilities: List<Abilities>,
    val base_experience: Int?,
    val forms: List<Forms>

)

data class Abilities(
    @SerializedName("ability")
    val ability: Ability?,
    val is_hidden: Boolean?,
    val slot: Int?
)

data class Ability(
    val name: String?,
    val uri: String?
)

data class Forms(
    val name: String?,
    val uri: String?
)


