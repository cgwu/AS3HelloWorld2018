package me.gg.democlient;

import android.util.Log;
import me.gg.demointerface.Comm;

public class PluginClass implements Comm {

    public PluginClass() {
        Log.i("Plugin", "PluginClass client initialized");
    }

    public int function1(int a, int b) {
        return a + b;
    }
}
