package com.example.coo_eat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_recipe.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val TAG : String = "RecipeActivity : "
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        coroutine()

        recipe_today_title.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        // 추천 레시피 클릭
        btn_recipe_food1.setOnClickListener{
            val intent= Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
        btn_recipe_food2.setOnClickListener{
            val intent= Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
        btn_recipe_food3.setOnClickListener{
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

    @RequiresApi(Build.VERSION_CODES.N)
    fun coroutine() {
        CoroutineScope(Dispatchers.Main).launch {
            val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
            val user_email = pref.getString("email", "no email")
            val user_ingredients = db.collection("${user_email}").document("ingredient")
            var items = mutableListOf<String>()
            var foodNames = mutableListOf<String>()
            var foodImages = mutableListOf<String>()
            var foodCategories = mutableListOf<String>()

            // 재료 배열로 받아옴
            user_ingredients.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val item = document["my"].toString().replace("[","").replace("]","").split(", ")
                        for (i in 0..item.size-1) {
                            items.add(item[i])
                        }
                        Log.d(TAG, "재료 나왔다 *^^* : ${items}")
                    }
                }

            // 사용자가 선택한 재료가 포함된 레시피 정보를 API에서 추출
            CoroutineScope(Dispatchers.IO).async {
                val key : String = "cf8505a99bb545f8882c"
                val url : String = "http://openapi.foodsafetykorea.go.kr/api/" + key + "/COOKRCP01/xml/1/1000"
                val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)

                xml.documentElement.normalize()
                println("Root element : " + xml.documentElement.nodeName)

                val list : NodeList = xml.getElementsByTagName("row")

                val detail_pref = getSharedPreferences("detail_pref", Context.MODE_PRIVATE)
                val edit = detail_pref.edit()  //수정모드
                var cnt = 0
                for (i in 0..list.length - 1) {
                    var n : Node = list.item(i)
                    if (n.getNodeType() == Node.ELEMENT_NODE) {
                        val elem = n as Element
                        val map = mutableMapOf<String, String>()

                        for (j in 0..elem.attributes.length - 1) {
                            map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
                        }

                        val ingredients = elem.getElementsByTagName("RCP_PARTS_DTLS").item(0).textContent
                        val ingredientsArray = ingredients.split(" ")

                        val equal = ingredientsArray.intersect(items.toList())
                        if (equal.size >= 2) {
                            Log.d(TAG, "추천 레시피: ${elem.getElementsByTagName("RCP_NM").item(0).textContent}")
                            edit.putString("name${cnt}","${elem.getElementsByTagName("RCP_NM").item(0).textContent}")
                            edit.apply()
                            cnt++
                            foodNames.add(elem.getElementsByTagName("RCP_NM").item(0).textContent)
                            foodImages.add(elem.getElementsByTagName("ATT_FILE_NO_MAIN").item(0).textContent)
                            foodCategories.add(elem.getElementsByTagName("RCP_PAT2").item(0).textContent)
                        }
                    }
                }
            }.await()

            // 레시피 이름 설정
            var foodName1 = findViewById(R.id.foodName1) as TextView
            var foodName2 = findViewById(R.id.foodName2) as TextView
            var foodName3 = findViewById(R.id.foodName3) as TextView
            foodName1.setText(foodNames[0])
            foodName2.setText(foodNames[1])
            foodName3.setText(foodNames[2])

            // 레시피 이미지 설정
            var foodImage1 = findViewById(R.id.btn_recipe_food1) as ImageButton
            var foodImage2 = findViewById(R.id.btn_recipe_food2) as ImageButton
            var foodImage3 = findViewById(R.id.btn_recipe_food3) as ImageButton
            Glide.with(this@RecipeActivity).load(foodImages[0]).into(foodImage1)
            Glide.with(this@RecipeActivity).load(foodImages[1]).into(foodImage2)
            Glide.with(this@RecipeActivity).load(foodImages[2]).into(foodImage3)

            // 레시피 카테고리 설정
            var foodCategory1 = findViewById(R.id.foodCategory1) as TextView
            var foodCategory2 = findViewById(R.id.foodCategory2) as TextView
            var foodCategory3 = findViewById(R.id.foodCategory3) as TextView
            foodCategory1.setText("#" + foodCategories[0])
            foodCategory2.setText("#" + foodCategories[1])
            foodCategory3.setText("#" + foodCategories[2])
        }
    }
}