package com.example.coo_eat

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val TAG : String = "Ingredients : "
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val user_email = pref.getString("email", "no email")
        val user_ingredients = db.collection("${user_email}").document("ingredient")


        btn_cook.setOnClickListener {
            user_ingredients.get()
                .addOnSuccessListener { document ->
                    val item = document["my"].toString().replace("[","").replace("]","").split(",")
                    if(item.size > 1) {
                        val intent = Intent(this, RecipeActivity::class.java)
                        startActivity(intent)
                        android.util.Log.d(TAG, "재료 나왔다 *^^* : ${item.size}")
                        Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val intent = Intent(this, IngredientActivity::class.java)
                        startActivity(intent)
                        android.util.Log.d(TAG, "재료 나왔다 *^^* : ${item.size}")
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                    }
                }


        }
        btn_main_refrigerator.setOnClickListener{
            val intent= Intent(this,IngredientActivity::class.java)
            startActivity(intent)
        }
    }
}