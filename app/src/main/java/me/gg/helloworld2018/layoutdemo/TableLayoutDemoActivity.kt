package me.gg.helloworld2018.layoutdemo

import me.gg.helloworld2018.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class TableLayoutDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_layout_demo)

        var edit1 = findViewById<EditText>(R.id.editText1)

        this.findViewById<Button>(R.id.btn1).setOnClickListener {
            Toast.makeText(this, "单击了按钮1:" + (it as Button).text + ":"+ edit1.text,
                Toast.LENGTH_LONG).show()
        }
        Log.i("Activity生命周期","onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity生命周期","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity生命周期","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity生命周期","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity生命周期","onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("Activity生命周期","onDestroy")
    }

}
