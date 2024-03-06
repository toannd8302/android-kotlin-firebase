package com.example.leaningapplicationusingfirebase.activities
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.leaningapplicationusingfirebase.databinding.ActivityUploadBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class UploadActivity : AppCompatActivity() {
    private lateinit var firebaseStorage: FirebaseStorage // using to upload File
    private lateinit var binding: ActivityUploadBinding
    private lateinit var firebaseDatabase: FirebaseDatabase // using to store url of file
    private var pdfUri: Uri? = null // store the uri of file which is selected by user
    private lateinit var choosingButton: Button
    private lateinit var uploadButton: Button
    private lateinit var tvFileName: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        choosingButton = binding.btnSelectFile
        uploadButton = binding.btnUpload
        tvFileName = binding.tvFileName
        progressBar = binding.progressBar

        // Return an object of firebase storage
        firebaseStorage = FirebaseStorage.getInstance()
        // Return an object of firebase database
        firebaseDatabase = FirebaseDatabase.getInstance()

        choosingButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                == android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                selectFile()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    9
                )
            }
        }

        uploadButton.setOnClickListener {
            if (pdfUri != null) { // User has selected a file
                uploadFile(pdfUri!!)
            } else {
                Toast.makeText(
                    this,
                    "Select a file",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun uploadFile(pdfUri: Uri) {
        progressBar.visibility = ProgressBar.VISIBLE

        val fileName: String = System.currentTimeMillis().toString() // Name of file in storage
        val storageReference = firebaseStorage.reference
        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
            .addOnSuccessListener {
                // Return url of your uploaded file
                storageReference.child("Uploads").child(fileName).downloadUrl.addOnSuccessListener {
                    val url = it.toString()
                    val reference = firebaseDatabase.reference
                    reference.child(fileName).setValue(url).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "File is uploaded",
                                Toast.LENGTH_SHORT
                            ).show()
                            progressBar.visibility = ProgressBar.GONE
                        } else {
                            Toast.makeText(
                                this,
                                "File is not uploaded",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "File is failed to upload: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("UploadActivity", "File upload failed with exception", e)
            }
            .addOnProgressListener { taskSnapshot ->
                try {
                    val progress = (100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount
                    progressBar.progress = progress.toInt()
                } catch (e: Exception) {
                    Log.e("UploadActivity at ProgressListener", "Error: ${e.message}")

                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 9 && grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            selectFile()
        } else {
            Toast.makeText(
                this,
                "Please provide permission",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun selectFile() {
       try {
           // Offer user to select file from phone
           val intent = Intent()
           intent.type = "application/pdf"
           intent.action = Intent.ACTION_GET_CONTENT
           getContent.launch(intent)
       }catch (e: Exception){
           Toast.makeText(this, "Có lỗi trong quá trình tải File vui lòng thử lại", Toast.LENGTH_SHORT).show()
           Log.e("UploadActivity", "Error: ${e.message}")
       }
    }
    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            // Check whether user has selected file or not
            data?.data?.let { uri ->
                pdfUri = uri
                tvFileName.text = "A file is selected: ${uri.lastPathSegment}" // Display the name of selected file
            } ?: run {
                Toast.makeText(this, "Please select a file", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please select a file", Toast.LENGTH_SHORT).show()
        }
    }

}
