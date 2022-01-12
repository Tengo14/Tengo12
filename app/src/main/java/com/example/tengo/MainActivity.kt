package com.example.tengo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        function()
        clickListener()
        check()


    }

    private fun function(){
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        button = findViewById(R.id.button)
    }

    private fun clickListener(){
        button.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()|| repeatPassword.isEmpty()){
                Toast.makeText(this,   "ცარიელია!",Toast.LENGTH_LONG).show()

            }
        }

    }
    private fun check(){
        button.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()

            if(repeatPassword != password){
                Toast.makeText(this,"რაღაც შეცდომაა!,შეიყვანეთ პაროლები ახლიდან",Toast.LENGTH_LONG).show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"რეგისტრაცია წარმატებით განხორციელდა",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"რაღაც შეცდომაა!",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}