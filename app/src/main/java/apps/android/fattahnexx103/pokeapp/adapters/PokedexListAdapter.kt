package apps.android.fattahnexx103.pokeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import apps.android.fattahnexx103.pokeapp.R
import apps.android.fattahnexx103.pokeapp.model.PokeemonModel
import apps.android.fattahnexx103.pokeapp.util.getProgressDrawable
import apps.android.fattahnexx103.pokeapp.util.loadImageUri
import apps.android.fattahnexx103.pokeapp.view.PokedexFragmentDirections
import kotlinx.android.synthetic.main.pokedex_item.view.*

class PokedexListAdapter(private val itemList: ArrayList<PokeemonModel> = arrayListOf()):RecyclerView.Adapter<PokedexListAdapter.ItemViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pokedex_item, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newItem: PokeemonModel){
        itemList.add(newItem)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
       return itemList.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data
        holder.view.pokdex_item_name.text = itemList[position].name
        holder.view.pokedex_item_ability.text = itemList[position].abilities!![0].ability!!.name
        holder.view.pokedex_icon_image.loadImageUri(itemList[position].sprites!!.back_default, getProgressDrawable(holder.view.context))

        holder.view.item_layout.setOnClickListener {
           // Toast.makeText(holder.view.context, "ITEM..... ${position} ... is... ${itemList[position].name}",Toast.LENGTH_LONG).show()
            val action =
                PokedexFragmentDirections.actionPokedexFragmentToPokemonFragment(
                    itemList[position]
                )
            Navigation.findNavController(holder.view).navigate(action)
        }

    }

    class ItemViewHolder(var view: View): RecyclerView.ViewHolder(view){

    }
}