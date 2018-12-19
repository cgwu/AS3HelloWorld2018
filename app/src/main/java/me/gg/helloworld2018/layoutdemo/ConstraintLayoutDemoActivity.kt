package me.gg.helloworld2018.layoutdemo

import android.app.Activity
import android.content.Intent
import me.gg.helloworld2018.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_constraint_layout_demo.*
import me.gg.helloworld2018.MainActivity

class ConstraintLayoutDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_demo)

        this.btnClickMe.setOnClickListener {
            Toast.makeText(this, this.textView4.text, Toast.LENGTH_LONG).show()
        }

        this.btnSnackBarTest.setOnLongClickListener {
        //this.btnSnackBarTest.setOnClickListener {
            Snackbar.make(rootLayout, "长按显示Snackbar - Long click", Snackbar.LENGTH_LONG).show()
            true
        }

        this.btnStartMainActivity.setOnClickListener {
            val intent = Intent(this@ConstraintLayoutDemoActivity, MainActivity::class.java)
            startActivityForResult(intent,0x007)
        }



//        this.btnStartMainActivity.setOnClickListener(View.OnClickListener(){
//            val intent = Intent(this@ConstraintLayoutDemoActivity, MainActivity::class.java)
//            startActivity(intent)
//            Log.i("跳转测试1","Start Main Activity from context: "
//                    + this@ConstraintLayoutDemoActivity.javaClass)
//            Log.i("跳转测试2","Start Main Activity from context: "
//                    + this.javaClass)
//        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("INFO","onActivityResult调用$requestCode, $resultCode")
        if (requestCode == 0x007 && resultCode == Activity.RESULT_OK) {
            val result = data!!.getStringExtra("result")
            Log.i("返回结果", result)
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        }
    }

}
