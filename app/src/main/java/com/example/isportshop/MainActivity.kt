package com.example.isportshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Attributes
    lateinit var phone : EditText
    lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phone = findViewById(R.id.txtPhoneNumber)
        password = findViewById(R.id.txtPassword)


    }

    private fun ValidateInputsLogin():Boolean{
        var flag = true

        if(TextUtils.isEmpty(phone.text.toString())){
            flag = false
        }
        if(TextUtils.isEmpty(password.text.toString())){
            flag = false
        }

        return flag
    }

    public fun LogIn(v: View?){
        if(ValidateInputsLogin()){
            val intent=Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }else{
            //Show message error!
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }

    }

    public fun MoveRegister(v:View?){
        val intent=Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}