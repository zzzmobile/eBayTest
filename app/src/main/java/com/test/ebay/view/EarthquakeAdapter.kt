package com.test.ebay.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.ebay.R
import com.test.ebay.model.Earthquake
import com.test.ebay.model.Earthquakes
import kotlinx.android.synthetic.main.layout_earthquake_item.view.*
import kotlin.math.abs

class EarthquakeAdapter(
    private var listData: Earthquakes
) :
    RecyclerView.Adapter<EarthquakeAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_earthquake_item,
            parent,
            false
        )
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        vh.bind(listData.earthQuakes!![position])
    }

    override fun getItemCount(): Int {
        return listData.earthQuakes!!.size
    }

    fun update(data: Earthquakes) {
        listData = data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvId: TextView = view.tvID
        private val tvTime: TextView = view.tvTime
        private val tvLocation: TextView = view.tvLocation
        private val tvDepth: TextView = view.tvDepth
        private val tvSource: TextView = view.tvSource
        private val tvMagnitude: TextView = view.tvMagnitude

        fun bind(data: Earthquake) {
            tvId.text = data.eqid
            tvTime.text = data.datetime

            val cLat = if (data.latitude > 0.0) "N" else "S"
            val cLng = if (data.longitude > 0.0) "E" else "W"
            val lat = abs(data.latitude)
            val lng = abs(data.longitude)
            tvLocation.text = "$cLat $lat, $cLng $lng"

            val depth = data.depth
            tvDepth.text = "$depth km"
            tvSource.text = data.source
            val magnitude = data.magnitude
            tvMagnitude.text = "$magnitude"

            if (data.magnitude >= 8) {
                tvMagnitude.setTextColor(Color.RED)
            } else {
                tvMagnitude.setTextColor(Color.BLACK)
            }
        }
    }
}
