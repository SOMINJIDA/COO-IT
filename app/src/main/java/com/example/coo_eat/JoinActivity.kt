package com.example.coo_eat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_join.*


class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth //firebase 인스턴스 선언

    val db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = Firebase.auth
        val TAG:String = "MainActivity : " //log출력을 위한 TAG


        // 뒤로가기 버튼 클릭
        btn_join_back.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // 회원가입 버튼 클릭
        btn_join_complete.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            //생성하고 싶은 이메일,비밀번호 id값 가져오기
            val newEmail : EditText = findViewById(R.id.newEmail)
            val newPassword : EditText = findViewById(R.id.newPassword)

            //생성하고 싶은 이메일,비밀번호 text 가져오기
            val newEmailText = newEmail.getText().toString()
            val newPasswordText = newPassword.getText().toString()



            val data = hashMapOf("" to true) //문서 빈 데이터 넣기


            //신규사용자추가
            auth.createUserWithEmailAndPassword(newEmailText, newPasswordText).addOnCompleteListener { task ->

                if (task.isSuccessful) {
//                        // 신규가입성공
//
                        db.collection(newEmailText).document("ingredient").set(data)
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

                        db.collection(newEmailText).document("scrap").set(data)
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

                        Toast.makeText(baseContext, "회원가입이 완료되었습니다",
                            Toast.LENGTH_SHORT).show()
//
                    } else {
                        // 신규가입실패
                        Log.w(TAG,"createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "정보를 다시 입력해주세요",
                            Toast.LENGTH_SHORT).show()
//
                    }
                }

        }
    }
}