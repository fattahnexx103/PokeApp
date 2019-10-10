package apps.android.fattahnexx103.pokeapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import apps.android.fattahnexx103.pokeapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//method to make the drawable circular
fun getProgressDrawable(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

//this method is used to make an image circular and then load it using glide library
fun ImageView.loadImageUri(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    //use glide to load the image
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}