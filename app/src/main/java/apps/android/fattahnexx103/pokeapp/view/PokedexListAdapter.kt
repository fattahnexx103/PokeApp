package apps.android.fattahnexx103.pokeapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.android.fattahnexx103.pokeapp.R
import apps.android.fattahnexx103.pokeapp.model.Forms
import apps.android.fattahnexx103.pokeapp.model.Results
import kotlinx.android.synthetic.main.pokedex_item.view.*

class PokedexListAdapter(private val itemNameList: ArrayList<Forms>) : RecyclerView.Adapter<PokedexListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pokedex_item, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newNameslist: List<Forms>){
        //itemNameList.clear()
        itemNameList.addAll(newNameslist)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return itemNameList.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the name
        holder.view.pokdex_item_name.text = itemNameList[position].name

    }

    class ItemViewHolder(var view: View): RecyclerView.ViewHolder(view){

    }
}