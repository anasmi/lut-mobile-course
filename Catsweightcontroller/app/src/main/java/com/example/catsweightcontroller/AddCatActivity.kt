package com.example.catsweightcontroller

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddCatActivity : AppCompatActivity() {
    var currentPhotoPath = ""
    lateinit var catsFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cat)

        catsFile = File(this.filesDir, R.string.cats_file_name.toString())

        val catNameField = findViewById<TextView>(R.id.catNameTextField)
        val catWeightField = findViewById<TextView>(R.id.currentWeight)
        val catBirthdayField = findViewById<TextView>(R.id.birthDate)

        val addPictureButton = findViewById<Button>(R.id.addPictureBtn)
        addPictureButton.setOnClickListener {
            dispatchTakePictureIntent()
            galleryAddPic()
        }

        //Don't show take picture button if there is no
        //camera available to a user
        //https://stackoverflow.com/questions/1944117/check-if-device-has-a-camera
        if(!applicationContext.packageManager
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            val layout =  addPictureButton.parent as ViewGroup
            layout.removeView(addPictureButton)
        }
        val saveCatBtn = findViewById<Button>(R.id.saveCatBtn)
        saveCatBtn.setOnClickListener {
            var valid = true
            val catName = catNameField.text
            val catWeight = catWeightField.text
            val birthday = catBirthdayField.text
            if (catName.isNullOrEmpty()) {
                catNameField.error = "Name must be present!"
                valid = false
            }
            if (catWeight.isNullOrEmpty()) {
                catWeightField.error = " Weight must be set!"
                valid = false
            }
            if (birthday.isNullOrEmpty()) {
                catBirthdayField.error = "Birthday must be set!"
                valid = false
            }
            if (valid) {
                var line = catName.toString()
                    .plus(";")
                    .plus(catWeight)
                    .plus(";")
                    .plus(birthday)
                if (!currentPhotoPath.isEmpty()) {
                    line = line.plus(";").plus(currentPhotoPath)
                }
                line = line.plus("\n")
                catsFile.appendText(line)
                // Clear fields
                catNameField.text = ""
                catWeightField.text = ""
                catBirthdayField.text = ""
            }
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.catsweightcontroller",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, 2)
                }
            }
        }
    }

    private fun galleryAddPic() {
        val f = File(currentPhotoPath)
        MediaScannerConnection.scanFile(
            applicationContext,
            arrayOf(f.toString()), arrayOf(f.name), null
        )
        /*
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }*/
    }
}