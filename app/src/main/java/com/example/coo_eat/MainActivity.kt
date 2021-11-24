package com.example.coo_eat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_cook.setOnClickListener{
            val intent= Intent(this,RecipeActivity::class.java)
            startActivity(intent)
        }
        btn_main_refrigerator.setOnClickListener{
            val intent= Intent(this,IngredientActivity::class.java)
            startActivity(intent)
        }
    }
}