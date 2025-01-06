package com.example.suitmediamobiledevelopertestquestion1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstScreen : AppCompatActivity() {

    private lateinit var inputName: EditText
    private lateinit var inputPalindrome: EditText
    private lateinit var btnCheck: Button
    private lateinit var btnNext: Button

    companion object {
        const val EXTRA_USER_NAME = "userName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_screen)

        inputName = findViewById(R.id.inputName)
        inputPalindrome = findViewById(R.id.inputPalindrome)
        btnCheck = findViewById(R.id.btnCheck)
        btnNext = findViewById(R.id.btnNext)

        btnCheck.setOnClickListener {
            val kalimat = inputPalindrome.text.toString().trim()
            if (kalimat.isEmpty()) {
                showDialog("Please enter a sentence to check.")
                return@setOnClickListener
            }
            if (isPalindrome(kalimat)) {
                showDialog("isPalindrome")
            } else {
                showDialog("not palindrome")
            }
        }

        btnNext.setOnClickListener {
            val name = inputName.text.toString().trim()
            if (name.isEmpty()) {
                showDialog("Please enter your name.")
                return@setOnClickListener
            }
            startActivity(Intent(this, SecondScreen::class.java).apply {
                putExtra(EXTRA_USER_NAME, name)
            })
        }
    }

    private fun isPalindrome(kalimat: String): Boolean {
        val cleanedSentence = kalimat.replace("\\s".toRegex(), "").lowercase()
        return cleanedSentence == cleanedSentence.reversed()
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
