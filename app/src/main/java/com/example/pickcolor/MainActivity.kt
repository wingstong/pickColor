package com.example.pickcolor

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.SeekBar
import android.graphics.Color
import android.view.View
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonPickColor = findViewById<Button>(R.id.colorbutton)
        buttonPickColor.setOnClickListener {
            showColorPickerDialog()
        }

    }

    private fun showColorPickerDialog() {
        val dialogView = layoutInflater.inflate(R.layout.color, null)
        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
        alertDialog.setPositiveButton("Применить") { dialog, _ ->
            val editTextRed = dialogView.findViewById<EditText>(R.id.RR)
            val editTextGreen = dialogView.findViewById<EditText>(R.id.G)
            val editTextBlue = dialogView.findViewById<EditText>(R.id.B)

            val seekBarRed = dialogView.findViewById<SeekBar>(R.id.RED)
            val seekBarGreen = dialogView.findViewById<SeekBar>(R.id.GREEN)
            val seekBarBlue = dialogView.findViewById<SeekBar>(R.id.BLUE)

            val red = editTextRed.text.toString().toIntOrNull() ?: seekBarRed.progress
            val green = editTextGreen.text.toString().toIntOrNull() ?: seekBarGreen.progress
            val blue = editTextBlue.text.toString().toIntOrNull() ?: seekBarBlue.progress

            val color = Color.rgb(red, green, blue)
            // Найти корневой элемент разметки и изменить его цвет фона
            val rootView = findViewById<View>(R.id.color_bg)
            rootView.setBackgroundColor(color)

            dialog.dismiss()
        }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }
}