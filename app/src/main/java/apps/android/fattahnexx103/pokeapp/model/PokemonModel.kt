package apps.android.fattahnexx103.pokeapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

//all of our data classes extend from Parcelable because of Android Navigation to work

data class PokeemonModel(

    @SerializedName("abilities")
    val abilities: List<Abilities>?,
    val name: String?,
    @SerializedName("stats")
    val stats: List<Stats>?,
    @SerializedName("sprites")
    val sprites: Sprites?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Abilities),
        parcel.readString(),
        parcel.createTypedArrayList(Stats),
        parcel.readParcelable(Sprites::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(abilities)
        parcel.writeString(name)
        parcel.writeTypedList(stats)
        parcel.writeParcelable(sprites, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokeemonModel> {
        override fun createFromParcel(parcel: Parcel): PokeemonModel {
            return PokeemonModel(parcel)
        }

        override fun newArray(size: Int): Array<PokeemonModel?> {
            return arrayOfNulls(size)
        }
    }
}


data class Abilities(
    @SerializedName("ability")
    val ability: Ability?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable<Ability>(Ability::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(ability, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Abilities> {
        override fun createFromParcel(parcel: Parcel): Abilities {
            return Abilities(parcel)
        }

        override fun newArray(size: Int): Array<Abilities?> {
            return arrayOfNulls(size)
        }
    }
}

data class Ability(
    val name: String?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ability> {
        override fun createFromParcel(parcel: Parcel): Ability {
            return Ability(parcel)
        }

        override fun newArray(size: Int): Array<Ability?> {
            return arrayOfNulls(size)
        }
    }
}

data class Stats(
    val base_stat: String?,
    @SerializedName("stat")
    val stat: Stat?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Stat::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(base_stat)
        parcel.writeParcelable(stat, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stats> {
        override fun createFromParcel(parcel: Parcel): Stats {
            return Stats(parcel)
        }

        override fun newArray(size: Int): Array<Stats?> {
            return arrayOfNulls(size)
        }
    }
}

data class Stat(
    val name: String?,
    val url: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stat> {
        override fun createFromParcel(parcel: Parcel): Stat {
            return Stat(parcel)
        }

        override fun newArray(size: Int): Array<Stat?> {
            return arrayOfNulls(size)
        }
    }
}



data class Sprites(
    val back_default: String?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(back_default)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sprites> {
        override fun createFromParcel(parcel: Parcel): Sprites {
            return Sprites(parcel)
        }

        override fun newArray(size: Int): Array<Sprites?> {
            return arrayOfNulls(size)
        }
    }
}


