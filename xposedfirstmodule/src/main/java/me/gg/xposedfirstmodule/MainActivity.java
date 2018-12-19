package me.gg.xposedfirstmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_ad;
    private Button btn_load_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_ad = findViewById(R.id.tv_ad);
        btn_load_ad = findViewById(R.id.btn_load_ad);

        btn_load_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_ad.setText(getTTAd());
            }
        });
    }


    public String getTTAd(){
        return "广告加载成功";
    }
}
