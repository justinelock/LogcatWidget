package com.widget.logcatviewer.ui;

import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.widget.logcatviewer.R;
import com.widget.logviewer.FloatingLogcatService;

import java.lang.reflect.Method;

public class FloatingLogActivity extends AppCompatActivity {

    private static String TAG = "UseFloatingLogcatServiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_logcat);

        FloatingLogcatViewer();
    }

    //请求用户给予悬浮窗的权限
    public void FloatingLogcatViewer() {
        if (isFloatWindowOpAllowed(this)) {
            FloatingLogcatService.launch(FloatingLogActivity.this);
        } else {
            openSetting();
        }
    }

    public static boolean isFloatWindowOpAllowed(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            return checkOp(context, 24);
            // AppOpsManager.OP_SYSTEM_ALERT_WINDOW
        } else {
            if ((context.getApplicationInfo().flags & 1 << 27) == 1 << 27) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;

        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            try {
                //Class<?> spClazz = Class.forName(manager.getClass().getName());
                Method method = manager.getClass().getDeclaredMethod("checkOp", int.class, int.class, String.class);
                int property = (Integer) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
                Log.e(TAG, " property: " + property);

                if (AppOpsManager.MODE_ALLOWED == property) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "Below API 19 cannot invoke!");
        }
        return false;
    }

    public void openSetting() {
        try {
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", getPackageName());
            localIntent.putExtra("app_uid", getApplicationInfo().uid);

            startActivityForResult(localIntent, 11);
            Log.e(TAG, "启动悬浮窗设置界面 \n(Start the millet floating window setting interface)");
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            Intent intent1 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);//ACTION_APPLICATION_DETAILS_SETTINGS
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent1.setData(uri);
            startActivityForResult(intent1, 11);
            Log.e(TAG, "启动悬浮窗界面 \n(Start the floating window interface)");
        }
    }

}
