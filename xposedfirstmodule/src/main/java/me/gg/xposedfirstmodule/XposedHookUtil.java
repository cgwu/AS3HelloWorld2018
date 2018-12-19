package me.gg.xposedfirstmodule;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedHookUtil implements IXposedHookLoadPackage {

    String class_name = "me.gg.xposedfirstmodule.MainActivity";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        XposedBridge.log("Xposed日志-> Loaded app: " + loadPackageParam.packageName);

        Class clazz = null;
        try {
            clazz = loadPackageParam.classLoader.loadClass(class_name);
        } catch (ClassNotFoundException e) {
            return;
        }
        XposedBridge.log("Xposed日志-> 找到待注入类: " + loadPackageParam.packageName);

        XposedHelpers.findAndHookMethod(clazz, "getTTAd", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                return "广告被拦截了v7";
            }
        });
    }

}