package fieldmarshal.repobrowse.util

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by fieldmarshal on 03.11.17.
 */

fun ImageView.loadAndCrop(url: String) {
    Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}

fun ImageView.load(url: String) {
    Glide.with(context).load(url).into(this)
}

fun initTextView(context: Context, view: TextView, text: String?, fontPath: String) {
    view.text = text
    view.typeface = Typeface.createFromAsset(context.assets, fontPath)
}

fun shortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun longToast(context: Context, message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun toggleProgressBar(pb: ProgressBar) {
    pb.visibility = if (pb.visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

fun nothing() {}   // empty function for debugging