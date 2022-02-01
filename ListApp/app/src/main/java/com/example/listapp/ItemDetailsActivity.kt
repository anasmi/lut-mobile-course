package com.example.listapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        val index = intent.getIntExtra("com.example.listapp.ITEM_INDEX", -1)

        if(index >= 0) {
            val pic = getImage(index)
            val imageView = findViewById<ImageView>(R.id.imageView)
            scaleImg(imageView,pic)
        }
    }

    fun getImage(index: Int): Int {
        return when (index) {
            0 -> R.drawable.apple
            1 -> R.drawable.carrot
            2 -> R.drawable.reissumies
            else -> -1
        }
    }

    fun scaleImg(image: ImageView, pic: Int) {
        val screen = windowManager.defaultDisplay
        val options = BitmapFactory.Options()

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, pic, options)

        val imageWidth = options.outWidth
        val screenWidth = screen.width
        if (imageWidth > screenWidth) {
            val ratio = Math.round(imageWidth.toFloat() / screenWidth.toFloat())
            options.inSampleSize = ratio
        }

        options.inJustDecodeBounds = false;
        val scaledImg = BitmapFactory.decodeResource(resources, pic, options)
        image.setImageBitmap(scaledImg)
    }
}