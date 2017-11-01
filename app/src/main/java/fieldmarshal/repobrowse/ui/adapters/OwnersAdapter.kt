package fieldmarshal.repobrowse.ui.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.models.Owner

/**
 * Created by fieldmarshal on 30.10.17.
 */

fun ImageView.loadUrlAndCropCircle(url: String) {
    Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}

class OwnersAdapter(private val context: Context,
                    private val users: List<Owner>,
                    val listener: (Owner) -> Unit) : RecyclerView.Adapter<OwnersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userCardView: CardView
        var avatarView: ImageView
        var usernameView: TextView
        var reposText: TextView

        init {
            userCardView = itemView.findViewById(R.id.userCard)
            avatarView = itemView.findViewById(R.id.avatar)
            usernameView = itemView.findViewById(R.id.username)
            reposText = itemView.findViewById(R.id.reposText)
        }

        fun bind(owner: Owner, listener: (Owner) -> Unit) = with(itemView) {
            avatarView.loadUrlAndCropCircle(owner.avatarUrl)
            usernameView.text = owner.login
            val repoString = context.getString(R.string.repos) + " " + owner.reposUrl
            reposText.text = repoString
            setOnClickListener { listener(owner) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.user_cardview, parent, false)
        return ViewHolder(userView)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
            = viewHolder.bind(users[position], listener)
}
