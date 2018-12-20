package me.gg.jnicmakedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = (stringFromJNI() + ":" + JniUtils.getName()
                + ":" + JniUtils.add(1, 2))

        this.btnAdd.setOnClickListener {
            val a = this.txtA.text.toString().toInt()
            val b = this.txtB.text.toString().toInt()
            val c = JniUtils.add(a, b)
            Toast.makeText(this, "Result:$c", Toast.LENGTH_LONG).show()
        }



        this.btnSayHi.setOnClickListener {
            val name = txtName.text.toString()
            val res = JniUtils.saiHi(name)
            Toast.makeText(this, "Result:$res", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
