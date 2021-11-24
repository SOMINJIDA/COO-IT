package com.example.coo_eat

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ingredient.*
import android.view.ViewGroup
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button

import android.widget.TextView
import java.util.*


class IngredientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient)

        btn_ingredient_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_ingredient_complete.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
        // 야채 버튼 동적 생성 부분입니다......
        val vegetable_layout1: LinearLayout = findViewById(R.id.btn_vegan_1)
        var vegetable1 = mutableListOf<String>(
            "버섯", "마늘", "양파", "두부"
        )
        for (i in vegetable1) {
            var btn_check = false
            val newButton = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 0, 0, 0)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            newButton.text = i

            val dynamicHori = LinearLayout(this)
            dynamicHori.addView(newButton)
            vegetable_layout1.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        val vegetable_layout2: LinearLayout = findViewById(R.id.btn_vegan_2)
        var vegetable2 = mutableListOf<String>(
            "감자", "양상추", "블루베리", "사과"
        )
        for (i in vegetable2) {
            var btn_check = false
            val newButton = Button(this)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 5, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            vegetable_layout2.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        val vegetable_layout3: LinearLayout = findViewById(R.id.btn_vegan_3)
        var vegetable3 = mutableListOf<String>(
            "딸기", "토마토", "콩나물", "당근"
        )
        for (i in vegetable3) {
            var btn_check = false
            val newButton = Button(this)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 5, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            vegetable_layout3.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        val vegetable_layout4: LinearLayout = findViewById(R.id.btn_vegan_4)
        var vegetable4 = mutableListOf<String>(
            "가지", "오이"
        )
        for (i in vegetable4) {
            var btn_check = false
            val newButton = Button(this)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 5, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            vegetable_layout4.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }
        // 육류 버튼 동적 생성 부분입니다......
        val meat_layout1: LinearLayout = findViewById(R.id.btn_meat_1)
        var meat1 = mutableListOf<String>(
            "달걀", "돼지고기", "닭고기", "소고기"
        )
        for (i in meat1) {
            var btn_check = false
            val newButton = Button(this)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 5, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            meat_layout1.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        val meat_layout2: LinearLayout = findViewById(R.id.btn_meat_2)
        var meat2 = mutableListOf<String>(
            "양고기", "오리고기"
        )
        for (i in meat2) {
            var btn_check = false
            val newButton = Button(this)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 5, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            meat_layout2.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        // 가공식품 버튼 동적 생성 부분입니다......
        val pro_food_layout1: LinearLayout = findViewById(R.id.btn_pro_food1)
        var pro_food = mutableListOf<String>(
            "참치캔", "스팸", "옥수수캔", "소시지"
        )
        for (i in pro_food) {
            var btn_check = false
            val newButton = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 0, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            pro_food_layout1.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        // 유제품 버튼 동적 생성 부분입니다......
        val milk_layout1: LinearLayout = findViewById(R.id.btn_milk1)
        var milk = mutableListOf<String>(
            "참치캔", "스팸", "옥수수캔", "소시지"
        )
        for (i in milk) {
            var btn_check = false
            val newButton = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 0, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            milk_layout1.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        // 해산물 버튼 동적 생성 부분입니다......
        val fish_layout1: LinearLayout = findViewById(R.id.btn_fish1)
        var fish = mutableListOf<String>(
            "새우", "낙지", "오징어", "문어"
        )
        for (i in fish) {
            var btn_check = false
            val newButton = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 5, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            fish_layout1.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }

        val fish_layout2: LinearLayout = findViewById(R.id.btn_fish2)
        var fish2 = mutableListOf<String>(
            "전복", "꽃게", "조개"
        )
        for (i in fish2) {
            var btn_check = false
            val newButton = Button(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            newButton.layoutParams = layoutParams
            layoutParams.setMargins(30, 5, 0, 0)
            newButton.text = i
            val dynamicHori = LinearLayout(this)
            newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
            dynamicHori.addView(newButton)
            fish_layout2.addView(dynamicHori)

            newButton.setOnClickListener {
                if (!btn_check) {
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_navy)
                    btn_check = true
                }
                else {
                    btn_check = false
                    newButton.background = resources.getDrawable(R.drawable.ingredient_btn_gray)
                }
            }
        }
    }

}