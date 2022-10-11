package com.example.cv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton

class HomeActivity : AppCompatActivity() {


    private lateinit var imageview: ImageView

    companion object{

        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        imageview = findViewById(R.id.imageView2)
        imageview.setOnClickListener{

            pickImageGallery()

        }


        val btn : Button = findViewById(R.id.NEXT)
        btn.setOnClickListener {
            verification()

        }
    }



    fun verification() : Boolean
        {
            val FN : EditText = findViewById(R.id.fntxt)
            val email : EditText = findViewById(R.id.temail)
            val age : EditText = findViewById(R.id.tage)
            val homme : RadioButton = findViewById(R.id.radhomme)
            val femme : RadioButton = findViewById(R.id.radfemme)
            var sFullname = FN.getText().toString();
            var sage = age.getText().toString();
            var semail = email.getText().toString().trim();
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if(sFullname.isEmpty())
            {
                FN.error = getString(R.string.empty)
                return false
            }
            if (semail.matches(emailPattern.toRegex())) {
            } else {
                email.error = getString(R.string.email)
                return false
            }
            if(sage.isEmpty())
            {
                age.error = getString(R.string.empty)
                return false
            }
            var sendFullname=FN.getText().toString()
            var sendEmail =semail
            var sendAge =   sage
            var sendGender = ""
            if(homme.isChecked)
            {
                sendGender="homme"
            }
            else
            {
                sendGender="femme"
            }
            val intent = Intent(this,SKillsActivity::class.java).apply {
                putExtra("fullname", sendFullname)
                putExtra("email", sendEmail)
                putExtra("age", sendAge)
                putExtra("gender",sendGender)


            }
            startActivity(intent)

            val intent2 = Intent(this,Niveauff::class.java).apply {
                putExtra("fullname", sendFullname)
                putExtra("email", sendEmail)

                putExtra("resID",R.id.imageView)

            }
            startActivity(intent2)
            return true
        }
    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK )
            imageview.setImageURI(data?.data)
    }





}
