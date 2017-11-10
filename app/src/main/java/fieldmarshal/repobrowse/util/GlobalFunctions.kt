package fieldmarshal.repobrowse.util

import android.content.Context
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by fieldmarshal on 03.11.17.
 */

fun ImageView.loadUrlAndCropCircle(url: String) {
    Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}

fun initTextView(context: Context, view: TextView, text: String?, fontPath: String) {
    view.text = text
    view.typeface = Typeface.createFromAsset(context.assets, fontPath)
}
