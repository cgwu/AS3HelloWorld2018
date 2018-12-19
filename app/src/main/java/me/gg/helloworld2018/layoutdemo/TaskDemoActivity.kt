package me.gg.helloworld2018.layoutdemo

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_task_demo.*
import me.gg.helloworld2018.R
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class TaskDemoActivity : AppCompatActivity() {

    lateinit var mhandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_demo)

        mhandler = object : Handler() {
            override fun handleMessage(msg: Message?) {
                tvCnt.text = "cnt:" +msg?.data?.getString("counter")
            }
        }

        btnCnt.setOnClickListener {
            Thread(Runnable {
                killSomeTime()
            }).start()
        }

        btnCnt2.setOnClickListener {
            Worker().execute()
        }

        btnCnt3.setOnClickListener {
            doAsync {
                for(i in 1..10){
                    Thread.sleep(1000)
                    activityUiThread {
                        tvCnt3.text = i.toString()
                    }
                }
            }
        }

    }

//    private fun killSomeTime() {
//        for(i in 1..20){
//            runOnUiThread(Runnable {
//                tvCnt.text = i.toString()
//            })
//            println("i: $i")
//            Thread.sleep(1000)
//        }
//    }

    private fun killSomeTime() {
        for(i in 1..20){
            var msg = Message.obtain()
            msg.data.putString("counter", i.toString())
            mhandler.sendMessage(msg)
            println("i: $i")
            Thread.sleep(1000)
        }
    }


    inner class Worker: AsyncTask<Void, String, Boolean> () {
        override fun onProgressUpdate(vararg values: String?) {
            tvCnt2.text = values[0]
        }

        override fun onPostExecute(result: Boolean?) {
            println(result)
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            for(i in 1..20){
                publishProgress(i.toString())
                Thread.sleep(1000)
            }
            return  true
        }
    }


}
