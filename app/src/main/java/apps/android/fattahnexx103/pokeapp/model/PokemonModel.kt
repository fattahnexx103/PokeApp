package apps.android.fattahnexx103.pokeapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

//all of our data classes extend from Parcelable because of Android Navigation to work

data class PokeemonModel(

    @SerializedName("abilities")
    val abilities: List<Abilities>?,
    val forms: List<Forms>?,
    @SerializedName("sprites")
    val sprites: Sprites?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Abilities),
        parcel.createTypedArrayList(Forms),
        parcel.readParcelable(Sprites::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(abilities)
        parcel.writeTypedList(forms)
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

data class Forms(
    val name: String?,
    val uri: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Forms> {
        override fun createFromParcel(parcel: Parcel): Forms {
            return Forms(parcel)
        }

        override fun newArray(size: Int): Array<Forms?> {
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


