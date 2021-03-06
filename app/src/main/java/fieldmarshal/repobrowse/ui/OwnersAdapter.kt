package fieldmarshal.repobrowse.ui

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.models.Owner
import fieldmarshal.repobrowse.util.Constants
import fieldmarshal.repobrowse.util.initTextView
import fieldmarshal.repobrowse.util.loadAndCrop

/**
 * Created by fieldmarshal on 30.10.17.
 */


class OwnersAdapter(private val context: Context,
                    private val users: List<Owner>,
                    val listener: (Owner) -> Unit) :
        RecyclerView.Adapter<OwnersAdapter.ViewHolder>() {

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
            avatarView.loadAndCrop(owner.avatarUrl)
            initTextView(context, usernameView, owner.login, Constants.ROBOTO_BOLD)
            val repoString = StringBuilder()
                    .append(context.getString(R.string.repos))
                    .append(" ")
                    .append(owner.reposUrl)
                    .toString()

            initTextView(context, reposText, repoString, Constants.ROBOTO_REGULAR)
            setOnClickListener { listener(owner) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.user_cardview, parent, false)
        return ViewHolder(userView)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(users[position], listener)

}
