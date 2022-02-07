package com.example.catsweightcontroller


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//https://developer.android.com/guide/topics/ui/layout/recyclerview
class WeightRecyclerViewAdapter(
    private val context: Context,
    private val weightsDiary: List<String>
) :
    RecyclerView.Adapter<WeightRecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     * Could be implemented as an own class
     * References 'my_listview_detail.xml' file
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val catNameTV: TextView
        val catNameWeightTV: TextView
        val catWeightAddedDate: TextView

        init {
            // Define click listener for the ViewHolder's View.
            catNameTV = view.findViewById(R.id.catNameLabel)
            catNameWeightTV = view.findViewById(R.id.weightValueLabel)
            catWeightAddedDate = view.findViewById(R.id.dateEnteredLabel)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_weight_entry, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val line = weightsDiary[position]
        val catsValues = line.split(';')
        if (catsValues.size >= 3) {
            viewHolder.catNameTV.text = catsValues[0]
            viewHolder.catNameWeightTV.text = catsValues[1]
            viewHolder.catWeightAddedDate.text = catsValues[2]
        } else if (catsValues.size == 2) {
            viewHolder.catNameTV.text = catsValues[0]
            viewHolder.catNameWeightTV.text = catsValues[1]
        } else if (catsValues.size == 1) {
            viewHolder.catNameTV.text = catsValues[0]
        }
    }

    override fun getItemCount() = weightsDiary.size

}