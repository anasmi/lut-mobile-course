package com.example.listapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

//https://developer.android.com/guide/topics/ui/layout/recyclerview
class CustomAdapter(
    private val context: Context,
    private val items: Array<String>,
    private val itemsDescriptions: Array<String>,
    private val prices: Array<String>
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     * Could be implemented as an own class
     * References 'my_listview_detail.xml' file
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewDescription: TextView
        val textViewPrice: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textViewName = view.findViewById(R.id.nameTextView)
            textViewDescription = view.findViewById(R.id.descriptionTextView)
            textViewPrice = view.findViewById(R.id.priceTextView)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_details, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of a view with that element's value
        viewHolder.textViewName.text = items[position]
        viewHolder.textViewDescription.text = itemsDescriptions[position]
        viewHolder.textViewPrice.text = prices[position]

        viewHolder.itemView.setOnClickListener {
            val showDetailsActivity = Intent(context, ItemDetailsActivity::class.java)
            //showDetailsActivity.putExtra("com.example.listapp.ITEM_INDEX",position)
            context.startActivity(showDetailsActivity)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    // assuming here, that all of the resources have the same amount of elements
    // Name, description and price
    override fun getItemCount() = items.size

}
