package com.example.coo_eat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.category2.*
import kotlinx.android.synthetic.main.category_1.*

class Category2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category2)

        // 뒤로가기 버튼 클릭
        btn_category2_back.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
        // 카테고리 이동
        btn_category2_soup.setOnClickListener {
            val intent = Intent(this, Category1Activity::class.java)
            startActivity(intent)
        }
        btn_category2_vegan.setOnClickListener {
            val intent = Intent(this, Category3Activity::class.java)
            startActivity(intent)
        }
        btn_category2_snack.setOnClickListener {
            val intent = Intent(this, Category4Activity::class.java)
            startActivity(intent)
        }
    }
}