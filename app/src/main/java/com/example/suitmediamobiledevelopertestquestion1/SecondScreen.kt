package com.example.suitmediamobiledevelopertestquestion1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class SecondScreen : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var usernameTextView: TextView
    private lateinit var selectedUserNameTextView: TextView
    private lateinit var btnChooseUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_screen)

        btnBack = findViewById(R.id.btnBack)
        usernameTextView = findViewById(R.id.usernameTextView)
        selectedUserNameTextView = findViewById(R.id.selectedUserNameTextView)
        btnChooseUser = findViewById(R.id.btnChooseUser)

        val username = intent.getStringExtra("userName")
        usernameTextView.text = username
        selectedUserNameTextView.text = username

        val usernameFromScreen3 = intent.getStringExtra("SELECTED_USER")
        usernameFromScreen3?.let { selectedUserName ->
            usernameTextView.text = selectedUserName
            selectedUserNameTextView.text = selectedUserName
        }


        btnBack.setOnClickListener {
            startActivity(Intent(this, FirstScreen::class.java))
        }

        btnChooseUser.setOnClickListener {
            startActivity(Intent(this, ThirdScreen::class.java))
        }
    }


}