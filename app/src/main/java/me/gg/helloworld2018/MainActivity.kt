package me.gg.helloworld2018

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = this.intent.extras
        if (bundle != null && bundle.containsKey("email") ) {
            var str = bundle["email"].toString() + ":" + bundle["password"]
            Log.i("调试信息GREAT: ", str)
            this.txtMainInfo.text = str
//        Toast.makeText(this, str, Toast.LENGTH_LONG)
        }

//        this.findViewById<Button>(R.id.btnMainFinish)
        btnMainFinish.setOnClickListener {
            //            Toast.makeText(this, txtMainInfo.text, Toast.LENGTH_LONG)
            var intent = Intent()
            intent.putExtra("result", "这是返回结果内容:" + txtFoo.text)
            this.setResult(Activity.RESULT_OK, intent)
            this.finish()
        }

        this.btnShowFoo.setOnClickListener {
            Toast.makeText(this, txtFoo.text, Toast.LENGTH_LONG).show()
        }

    }

}
