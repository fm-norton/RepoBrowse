package fieldmarshal.repobrowse.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.models.Repo
import fieldmarshal.repobrowse.util.Constants
import fieldmarshal.repobrowse.util.DateUtils
import fieldmarshal.repobrowse.util.initTextView
import kotlinx.android.synthetic.main.repo_cardview.view.*

/**
 * Created by fieldmarshal on 01.11.17.
 */


class RepoAdapter(private val context: Context,
                  private val repos: List<Repo>,
                  val listener: (Repo) -> Unit) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fullnameText: TextView
        var descText: TextView
        var starIcon: ImageView
        var starCount: TextView
        var forkIcon: ImageView
        var forkCount: TextView
        var dateIcon: ImageView
        var dateCreated: TextView

        init {
            fullnameText = itemView.findViewById(R.id.fullnameText)
            descText = itemView.findViewById(R.id.descText)
            starIcon = itemView.findViewById(R.id.starIcon)
            starCount = itemView.findViewById(R.id.starCount)
            forkIcon = itemView.findViewById(R.id.forkIcon)
            forkCount = itemView.findViewById(R.id.forkCount)
            dateIcon = itemView.findViewById(R.id.dateIcon)
            dateCreated = itemView.findViewById(R.id.dateCreated)
        }

        fun bind(repo: Repo, listener: (Repo) -> Unit) = with(itemView) {
            initTextView(context, fullnameText, repo.fullName, Constants.ROBOTO_BOLD)
            initTextView(context, descText, repo.description, Constants.ROBOTO_REGULAR)
            initTextView(context, starCount, repo.stargazers.toString(), Constants.ROBOTO_LIGHT)
            initTextView(context, forkCount, repo.forks.toString(), Constants.ROBOTO_LIGHT)
            val prettyDateString = DateUtils.getPrettyDate(resources.getString(R.string.prettyDateFormat), repo.createdAt)
            initTextView(context, dateCreated, prettyDateString, Constants.ROBOTO_LIGHT)
            setOnClickListener { listener(repo) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val repoView = LayoutInflater.from(parent.context).inflate(R.layout.repo_cardview, parent, false)
        return ViewHolder(repoView)
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(repos[position], listener)

}