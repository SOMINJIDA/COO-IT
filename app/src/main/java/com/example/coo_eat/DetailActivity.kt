package com.example.coo_eat

import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val back_btn = findViewById(R.id.backbutton) as ImageView
        back_btn.bringToFront() //뒤로가기 버튼 맨 앞으로

        val scrap_btn = findViewById(R.id.pick_recipe) as ImageButton
        val recipe_name = findViewById(R.id.recipe_name) as TextView

        var check_btn = false

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val user_email = pref.getString("email", "no email") //내 이메일 가져오기

        scrap_btn.setOnClickListener(View.OnClickListener {
            if (!check_btn) {
                scrap_btn.setSelected(true)
                check_btn = true
                db.collection(user_email.toString()).document("scrap").update("my", FieldValue.arrayUnion(recipe_name.text.toString()))


            }
            else {
                check_btn = false
                scrap_btn.setSelected(false)
                db.collection(user_email.toString()).document("scrap").update("my", FieldValue.arrayRemove(recipe_name.text.toString()))
            }
        })



//        val scrap_btn = ImageButton(this)
//        scrap_btn.setOnClickListener {
//            if
//        }
//        scrap_btn.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                scrap_btn.setSelected(true);
//            }
//        });


    }
}