package com.example.coo_eat

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.scrap.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory

class ScrapActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scrap)

        val db = Firebase.firestore
        val TAG: String = "MainActivity : " //log출력을 위한 TAG

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val user_email = pref.getString("email", "no email")
        val user_scrap = db.collection("${user_email}").document("scrap")

        //api호출
        val key: String = "cf8505a99bb545f8882c"
        val url: String = "http://openapi.foodsafetykorea.go.kr/api/" + key + "/COOKRCP01/xml/1/10"
        val xml: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)

        xml.documentElement.normalize()
        println("Root element : " + xml.documentElement.nodeName)

        val list: NodeList = xml.getElementsByTagName("row")
        println(list)

        for (i in 0..list.length - 1) {
            var n: Node = list.item(i)
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                val elem = n as Element
                val map = mutableMapOf<String, String>()

                for (j in 0..elem.attributes.length - 1) {
                    map.putIfAbsent(
                        elem.attributes.item(j).nodeName,
                        elem.attributes.item(j).nodeValue
                    )
                }

                val ingredients = elem.getElementsByTagName("RCP_PARTS_DTLS").item(0).textContent
                val ingredientsArray = ingredients.split(" ")

                user_scrap.get() //스크랩 레시피 배열로 받아옴
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d(
                                TAG,
                                "스크랩 레시피 : ${
                                    document["my"].toString().replace("[", "").replace("]", "")
                                        .split(",")
                                }"
                            )
                        }
                        val scrapList =
                            document["my"].toString().replace("[", "").replace("]", "").split(",")
                        for (h in 0..ingredientsArray.size - 1) {
                            for (i in scrapList)
                                if (i == ingredientsArray[h]) {
                                    Log.d(
                                        TAG,
                                        "${elem.getElementsByTagName("RCP_NM").item(0).textContent}"
                                    )
                                }
                        }
                    }
            }
        }


        // 뒤로가기 버튼 클릭
        btn_scrap_back.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }

        // 추천 레시피 버튼 클릭
        btn_scrap_recipe.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
    }
}
