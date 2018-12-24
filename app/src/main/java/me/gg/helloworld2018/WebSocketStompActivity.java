package me.gg.helloworld2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompHeader;
import ua.naiksoftware.stomp.client.StompClient;
import ua.naiksoftware.stomp.client.StompMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebSocketStompActivity extends AppCompatActivity {

    private static final String TAG = "WebSocket";
    private StompClient mStompClient;

    private void toast(String text) {
        Log.i(TAG, text);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket_stomp);

        ((Button) findViewById(R.id.btnWsConnect)).setOnClickListener(v -> {
            mStompClient = Stomp.over(Stomp.ConnectionProvider.JWS, "ws://192.168.50.5:8080/stomp/websocket");

            mStompClient.lifecycle()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(lifecycleEvent -> {
                        switch (lifecycleEvent.getType()) {
                            case OPENED:
                                toast("Stomp connection opened");
                                break;
                            case ERROR:
                                Log.e(TAG, "Stomp connection error", lifecycleEvent.getException());
                                toast("Stomp connection error");
                                break;
                            case CLOSED:
                                toast("Stomp connection closed");
                        }
                    });



            // Receive greetings
            mStompClient.topic("/topic")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(topicMessage -> {
                        Log.i(TAG, "/topic接收到消息 " + topicMessage.getPayload());
                    });

            mStompClient.topic("/user/009/msg")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(topicMessage -> {
                        Log.i(TAG, "/user接收到消息 " + topicMessage.getPayload());
                    });

//            mStompClient.topic("/topic").subscribe(msg -> {
//                System.out.println(msg.getPayload());
//                Log.i(TAG, msg.getPayload());
//            });
//
//            mStompClient.topic("/user/009/msg").subscribe(msg -> {
//                System.out.println(msg.getPayload());
//                Log.i(TAG, msg.getPayload());
//            });

            StompHeader head = new StompHeader("username","009");
            List<StompHeader> listHead = new ArrayList<>();
            listHead.add(head);
            mStompClient.connect(listHead);

        });

//        mStompClient.send("/topic/hello-msg-mapping", "My first STOMP message!").subscribe();

        ((Button) findViewById(R.id.btnWsDisonnect)).setOnClickListener(v -> {
            mStompClient.disconnect();
        });

    }

}
