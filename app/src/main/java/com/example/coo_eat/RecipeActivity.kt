package com.example.coo_eat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_recipe.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread


class RecipeActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val TAG : String = "RecipeActivity : "
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @RequiresApi(Build.VERSION_CODES.N)
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

        thread(start = true) {
            val key : String = "cf8505a99bb545f8882c"
            val url : String = "http://openapi.foodsafetykorea.go.kr/api/" + key + "/COOKRCP01/xml/1/1000"
            val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)

            xml.documentElement.normalize()
            println("Root element : " + xml.documentElement.nodeName)

            val list : NodeList = xml.getElementsByTagName("row")

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
                    var l = ArrayList<String>()
                    l.add("달걀")
                    l.add("양파")

                    val equal = ingredientsArray.intersect(l)
                    if (equal.size >= 2) {
                        println("추천 레시피: ${elem.getElementsByTagName("RCP_NM").item(0).textContent}")
                    }
                }
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