package com.example.database_2

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var listUser: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val insert = findViewById<Button>(R.id.buttonInsert)
        val delete = findViewById<Button>(R.id.buttonDelete)
        val update = findViewById<Button>(R.id.buttonUp)
        val fetch = findViewById<Button>(R.id.buttonFetch)
        val id_1 = findViewById<EditText>(R.id.editId)
        val pass = findViewById<EditText>(R.id.editPassword)
        val name = findViewById<EditText>(R.id.editName)

        val textFetch=findViewById<TextView>(R.id.textView)
        val usernameInput = name.text.toString()
        val passwordInput = pass.text.toString()
        val id_2 = id_1.text.toString()

        val db = DatabaseManager(this)

        insert.setOnClickListener {
            db.insert(usernameInput, passwordInput)
        }

        fetch.setOnClickListener {
            val cursor: Cursor = db.fetch()
            if (cursor.moveToFirst()) {
                do {
                    val indexId = cursor.getColumnIndex(DatabaseHelper.USER_ID)
                    val id = cursor.getString(if (indexId >= 0) indexId else 0)
                    val indexUsername = cursor.getColumnIndex(DatabaseHelper.USER_NAME)
                    val username = cursor.getString(if (indexUsername >= 0) indexUsername else 0)
                    val indexPassword = cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD)
                    val password = cursor.getString(if (indexPassword >= 0) indexPassword else 0)

                    var Users = User(id = id, name = username, pass = password)
                    listUser.add(Users)

                } while (cursor.moveToNext())

                textFetch.text="$listUser"

            }

        }

        delete.setOnClickListener {
            db.delete(id_2.toLong())
        }

        update.setOnClickListener {
            db.update(id_2.toLong(), usernameInput, passwordInput)
        }
    }
}