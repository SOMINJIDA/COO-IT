package com.example.coo_eat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import com.bumptech.glide.Glide
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

        //뒤로가기 버튼
        back_btn.setOnClickListener{
            val intent= Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }

        //스크랩 버튼
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

        //레시피 이름 가져오기
        val thisRecipe = intent.getStringExtra("recipeName")
        val thisImage = intent.getStringExtra("recipeImage")
        val thisCategory = intent.getStringExtra("recipeCategory")
        val thisIngredient = intent.getStringExtra("recipeIngredient")
        val thisCookings = intent.getStringArrayListExtra("cookings")


        println(thisRecipe)
        println(thisCategory)
        println(thisIngredient)

        //상세 데이터 보여지게

        val recipeName = findViewById(R.id.recipe_name) as TextView
        recipeName.setText(thisRecipe) //이름 수정

        val recipeImage = findViewById(R.id.detailMain) as ImageView
        Glide.with(this@DetailActivity).load(thisImage).into(recipeImage) //메인 사진 수정

        val recipeCategory = findViewById(R.id.detailCategory) as TextView
        recipeCategory.setText(thisCategory) //카테고리 수정

        val recipeIngredient = findViewById(R.id.detailIngredient) as TextView
        recipeIngredient.setText(thisIngredient) //재료 수정

        val cookingLayout : LinearLayout = findViewById(R.id.detailCooking)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )

        layoutParams.gravity = Gravity.CENTER
        layoutParams.setMargins(0,15,0,0)

        if (thisCookings != null) {
            for (i in 0..(thisCookings.toArray().size -1)) {
                println(thisCookings[i])

                var cooking = TextView(this)

                cooking.layoutParams = layoutParams

                cooking.setText(thisCookings[i])
                cooking.setTextSize(Dimension.SP,15.0f)
                cooking.setTextColor(Color.BLACK)

                cookingLayout.addView(cooking)




            }
        }









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