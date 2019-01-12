package oth.archaeologicalfieldwork.views.sitelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_content_site.view.*
import oth.archaeologicalfieldwork.R
import oth.archaeologicalfieldwork.helpers.readImageFromPath
import oth.archaeologicalfieldwork.models.sites.SiteModel
import java.lang.Math.abs


interface SiteClickListener {
    fun onSiteClick(site: SiteModel)
}

class SiteAdapter constructor(
    private var sites: List<SiteModel>,
    private val listener: SiteClickListener
) : RecyclerView.Adapter<SiteAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_content_site,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val site = sites[holder.adapterPosition]
        holder.bind(site, listener)
    }

    override fun getItemCount(): Int = sites.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(site: SiteModel, listener: SiteClickListener) {
            itemView.title.text = site.title
            itemView.description.text = site.description
            itemView.sitelist_visit_checkbox.isChecked = site.hasVisited

            if (abs(site.location.lng) > 0.0001) {
                itemView.location_info_text_list.visibility = View.VISIBLE
                itemView.location_info_text_list.text = itemView.resources.getString(
                    R.string.location_text,
                    site.location.lat.toString(),
                    site.location.lng.toString()
                )
            } else {
                itemView.location_info_text_list.visibility = View.GONE
            }

            if (!site.images.isEmpty()) {
                itemView.image_icon.setImageBitmap(readImageFromPath(itemView.context, site.images[0]))
            } else {
                itemView.image_icon.setImageResource(R.drawable.logo)
            }

            itemView.setOnClickListener { listener.onSiteClick(site) }
        }
    }
}