package com.example.catsweightcontroller

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatRecyclerViewAdapter(
    private val cats: List<String>
) :
    RecyclerView.Adapter<CatRecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     * Could be implemented as an own class
     * References 'my_listview_detail.xml' file
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petName: TextView
        val petImage: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            petName = view.findViewById(R.id.petNameId)
            petImage = view.findViewById(R.id.petImageId)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_pet_entry, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of a view with that element's value

        val line = cats[position]
        val catsValues = line.split(';')
        if (catsValues.size >= 4) {
            viewHolder.petName.text = catsValues[0]
            setPic(catsValues[3], viewHolder.petImage)
        } else if (catsValues.size > 1) {
            viewHolder.petName.text = catsValues[0]
            //Don't display image element, if there is no image available
            (viewHolder.petName.parent as ViewGroup).removeView(viewHolder.petImage)
        }

    }


    private fun setPic(currentPhotoPath: String, imageView: ImageView) {
        // Get the dimensions of the View
        val targetW: Int = imageView.width
        val targetH: Int = imageView.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            if (targetW != 0 && targetH != 0) {
                val scaleFactor: Int = Math.max(1, Math.min(photoW / targetW, photoH / targetH))
                inSampleSize = scaleFactor
            }
            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            imageView.setImageBitmap(bitmap)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    // assuming here, that all of the resources have the same amount of elements
    // Name, description and price
    override fun getItemCount() = cats.size

}