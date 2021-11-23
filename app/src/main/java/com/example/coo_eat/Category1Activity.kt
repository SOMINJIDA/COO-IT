package com.example.coo_eat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.category_1.*
import kotlinx.android.synthetic.main.scrap.*

class Category1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_1)

        // 뒤로가기 버튼 클릭
        btn_category1_back.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
        // 카테고리 이동
        btn_category1_rice.setOnClickListener {
            val intent = Intent(this, Category2Activity::class.java)
            startActivity(intent)
        }
        btn_category1_vegan.setOnClickListener {
            val intent = Intent(this, Category3Activity::class.java)
            startActivity(intent)
        }
        btn_category1_snack.setOnClickListener {
            val intent = Intent(this, Category4Activity::class.java)
            startActivity(intent)
        }
    }

}