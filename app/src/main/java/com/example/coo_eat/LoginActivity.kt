package com.example.coo_eat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth //firebase 인스턴스 선언
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    val db = Firebase.firestore


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        val TAG:String = "MainActivity : " //log출력을 위한 TAG

        //이메일,비밀번호 id값으로 가져오기
        val email : EditText = findViewById(R.id.email)
        val password : EditText = findViewById(R.id.password)


        // 회원가입 버튼 클릭
        btn_signup.setOnClickListener{
            val intent= Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
        // 로그인 버튼 클릭
        btn_login.setOnClickListener {

            //이메일,비밀번호 text 가져오기
            val emailText = email.getText().toString()
            val passwordText = password.getText().toString()


            //사용자 확인후 로그인
            auth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
                    val edit = pref.edit()  //수정모드

                    edit.putString("email",emailText)
                    edit.apply()

                    //todo: 밑에 코드 세개 재료화면에 적용하기
                    db.collection(emailText).document("ingredient").update("양파",true)
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

                        //화면전환
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // 로그인 실패
                        Log.w(TAG, "signInWithEmail:failure", task.exception)

                        //알림메시지
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
//
                    }
                }
        }
    }
}