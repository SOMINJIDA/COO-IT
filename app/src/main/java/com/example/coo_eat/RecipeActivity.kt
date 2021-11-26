package com.example.coo_eat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_recipe.*


class RecipeActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val TAG : String = "RecipeActivity : "
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        recipe_today_title.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val user_email = pref.getString("email", "no email")
        val user_ingredients = db.collection("${user_email}").document("ingredient")

        // 재료 배열로 받아옴
        user_ingredients.get()
           .addOnSuccessListener { document ->
               if (document != null) {
                   val item = document["my"]
                   Log.d(TAG, "재료 나왔다 *^^* : ${item}")
               }
           }
        // 상세페이지 버튼 클릭
        btn_recipe_food1.setOnClickListener{
            val intent= Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 버튼 클릭
        btn_recipe_back.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 스크랩 버튼 클릭
        btn_recipe_scrap.setOnClickListener{
            val intent= Intent(this, ScrapActivity::class.java)
            startActivity(intent)
        }

        // 카테고리별 버튼 클릭
        btn_recipe_soup.setOnClickListener{
            val intent= Intent(this, Category1Activity::class.java)
            startActivity(intent)
        }
        btn_recipe_rice.setOnClickListener{
            val intent= Intent(this, Category2Activity::class.java)
            startActivity(intent)
        }
        btn_recipe_vegan.setOnClickListener{
            val intent= Intent(this, Category3Activity::class.java)
            startActivity(intent)
        }
        btn_recipe_snack.setOnClickListener{
            val intent= Intent(this, Category4Activity::class.java)
            startActivity(intent)
        }

    }
}