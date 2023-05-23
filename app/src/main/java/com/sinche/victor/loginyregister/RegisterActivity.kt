package com.sinche.victor.loginyregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private  lateinit var  txtName:EditText
    private  lateinit var  txtApellido:EditText
    private  lateinit var  txtCorreo:EditText
    private  lateinit var  txtCelular:EditText
    private  lateinit var  txtContraseña:EditText
    private  lateinit var  txtContraseñaCom:EditText
    private  lateinit var  progressBar: ProgressBar
    private  lateinit var  dbReference: DatabaseReference
    private  lateinit var  database: FirebaseDatabase
    private  lateinit var  auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtName=findViewById(R.id.txt_name)
        txtApellido=findViewById(R.id.txt_lastname)
        txtCorreo=findViewById(R.id.txt_email)
        txtCelular=findViewById(R.id.txt_phone)
        txtContraseña=findViewById(R.id.txt_password)
        txtContraseñaCom=findViewById(R.id.txt_confirm_password)

        progressBar= findViewById(R.id.progressBar)
        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        dbReference=database.reference.child("User")
    }
    fun btn_register(view: View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val name:String=txtName.text.toString()
        val apellido:String=txtApellido.text.toString()
        val correo:String=txtCorreo.text.toString()
        val celular:String=txtCelular.text.toString()
        val contraseña:String=txtContraseña.text.toString()
        val comcontraseña:String=txtContraseñaCom.toString()

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(apellido)&&!TextUtils.isEmpty(correo)&&!TextUtils.isEmpty(celular)&&!TextUtils.isEmpty(contraseña)&&!TextUtils.isEmpty(comcontraseña)){
                progressBar.visibility=View.VISIBLE
            auth.createUserWithEmailAndPassword(correo,contraseña)
                .addOnCompleteListener(this){
                    task->

                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail(user)

                        val userBD= user?.let { dbReference.child(it.uid) }

                        userBD?.child("Name")?.setValue(name)
                        userBD?.child("Apellido")?.setValue(apellido)
                        action()
                    }
                }
        }
    }
    private fun action(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
    private  fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener (this){
                task->

                if(task.isComplete){
                    Toast.makeText(this,"Email enviado",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error al enviar email",Toast.LENGTH_LONG).show()
                }
            }
    }
    fun ViewLogin(view: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}