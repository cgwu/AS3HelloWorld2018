package me.gg.helloworld2018.layoutdemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shared_pref_demo.*
import me.gg.helloworld2018.R

class SharedPrefDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref_demo)

        val pref = getPreferences(Context.MODE_PRIVATE)

        btnSave.setOnClickListener {
            val editor = pref.edit()

            editor.putString("name", txtName.text.toString())
            editor.putString("pwd", txtPwd.text.toString())
            editor.apply() // async
//            editor.commit() // sync

            Toast.makeText(this, "Saved data", Toast.LENGTH_LONG).show()
        }

        btnLoad.setOnClickListener {
            txtName.setText(pref.getString("name",""))
            txtPwd.setText( pref.getString("pwd",""))
        }

    }


}
