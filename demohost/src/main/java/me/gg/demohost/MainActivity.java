package me.gg.demohost;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import dalvik.system.DexClassLoader;
import me.gg.demointerface.Comm;

import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btnInvoke)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useDexClassLoader();
            }
        });
    }

    public void useDexClassLoader() {
        Intent intent = new Intent("me.gg.decmoclient.plugin", null);
        PackageManager pm = getPackageManager();
        final List<ResolveInfo> plugins = pm.queryIntentActivities(intent, 0);
        ResolveInfo rinfo = plugins.get(0);
        ActivityInfo ainfo = rinfo.activityInfo;

        String div = System.getProperty("path.separator");
        String packageName = ainfo.packageName;
        String dexPath = ainfo.applicationInfo.sourceDir;
        String dexOuputDir = getApplicationInfo().dataDir;
        String libPath = ainfo.applicationInfo.nativeLibraryDir;

        String pluginVersion = "";
        try {
            Resources res = pm.getResourcesForApplication(packageName); // plugin resource
            int id = res.getIdentifier("version", "string", packageName);
            pluginVersion = res.getString(id);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        DexClassLoader cl = new DexClassLoader(dexPath, dexOuputDir,
                libPath, this.getClass().getClassLoader());
        try {
            Class<?> clazz = cl.loadClass(packageName + ".PluginClass");
            Object obj = clazz.newInstance();
            //法1：
//            Class[] params = new Class[2];
//            params[0] = Integer.TYPE;
//            params[1] = Integer.TYPE;
//            Method action = clazz.getMethod("function1", params);
//            Integer ret = (Integer) action.invoke(obj, 12, 34);

            //法2：
            Comm comm = (Comm) obj;
            Integer ret = comm.function1(11, 22);
            Log.i("Host", "Plugin " + pluginVersion + " Return Value is " + ret);
            ((TextView) findViewById(R.id.tvResult)).setText("Plugin " + pluginVersion + " Return Value is " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
