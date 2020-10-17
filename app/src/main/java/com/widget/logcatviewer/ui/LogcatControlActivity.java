package com.widget.logcatviewer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.widget.logcatviewer.R;
import com.widget.logcatviewer.been.LogBeen;
import com.widget.logcatviewer.common.Constant;
import com.widget.logviewer.LogcatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogcatControlActivity extends AppCompatActivity {

    private static String TAG = "LogcatControlActivity";
    private String loginAccountId;
    private String isOpenLogcatViewerCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logcat_control);

        //用户登录后得到的账号ID（loginAccountId） 示例中账号ID为 2020101010  是否开启浮动日志窗口码（isOpenLogcatViewerCode）可以自定定义参数
        loginAccountId = "2020101010";
        isOpenLogcatViewerCode = "0";

        //远程开启指定用户或玩家的logviewer日志输出窗口
        remoteControlLogViewer();
    }


    private void remoteControlLogViewer() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(Constant.CONTROL_LOGCATVIEWER_API).build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //获取远程控制接口返回的json
                assert response.body() != null;
                String string = response.body().string();

                Gson gson = new Gson();
                LogBeen logBeen = gson.fromJson(string, LogBeen.class);
                String isOpenLogcatViewer = logBeen.getData().getIsOpenLogcatViewer();
                String accountId = logBeen.getData().getAccountId();

                /*
                 * 是否打开LogcatViewer 可以自定义对应参数（isOpenLogcatViewerCode）
                 * 用户登录得到的账号ID(loginAccountId) 与 后台动态控制接口得到账号ID(accountId)
                 */
                if (isOpenLogcatViewer.equals(isOpenLogcatViewerCode) && accountId.equals(loginAccountId)) {
                    Log.e(TAG, "指定账号ID：2020101010 用户或玩家 Logviewer窗口开启");
                    LogcatActivity.launch(LogcatControlActivity.this);
                } else {
                    Log.e(TAG, "指定账号ID：2020101010 用户或玩家 Logviewer窗口没有开启");
                }
            }
        });
    }
}
