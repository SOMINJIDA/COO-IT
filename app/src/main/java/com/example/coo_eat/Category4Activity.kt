package com.example.coo_eat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.category3.*
import kotlinx.android.synthetic.main.category4.*

class Category4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category4)

        // 뒤로가기 버튼 클릭
        btn_category4_back.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
        // 카테고리 이동
        btn_category4_soup.setOnClickListener {
            val intent = Intent(this, Category1Activity::class.java)
            startActivity(intent)
        }
        btn_category4_rice.setOnClickListener {
            val intent = Intent(this, Category2Activity::class.java)
            startActivity(intent)
        }
        btn_category4_vegan.setOnClickListener {
            val intent = Intent(this, Category3Activity::class.java)
            startActivity(intent)
        }
    }
}