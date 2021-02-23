package com.example.xposedapp2;

import static de.robv.android.xppsed.XposedHelpers.findAndHookMethod;
import android.graphics.Color;
import android.widget.TextView;
import de.robv.android.xppsed.IXposedHookLoadPackage;
import de.robv.android.xppsed.XC_MethodHook;
import de.robv.android.xppsed.callbacks.XC_LoadPackage.LoadPackageParam;

public class hook implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.android.systemui"))

            return;

        findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                TextView tv = (TextView) param.thisObject;
                String text = tv.getText().toString();
                tv.setText(text + "r0ysue :)");
                tv.setTextColor(Color.RED);
            }
        });
    }
}