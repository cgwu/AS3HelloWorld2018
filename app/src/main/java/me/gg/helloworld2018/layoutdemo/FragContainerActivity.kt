package me.gg.helloworld2018.layoutdemo

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import me.gg.helloworld2018.R
import me.gg.helloworld2018.fragments.MyBlankFragment

class FragContainerActivity : AppCompatActivity(), MyBlankFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag_container)

        // 动态添加fragment
        var f1 = MyBlankFragment.newInstance("参数11","参数12")
        var f2 = MyBlankFragment.newInstance("参数21","参数22")
        var ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.conFag1, f1)
        ft.add(R.id.conFag2, f2)
        ft.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.i("callback", uri.toString())
    }

}
