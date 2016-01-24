package com.example.tuionf.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

public class ForceOfflineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d("ForceOfflineReceiver","jie shou dao");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        Log.d("ForceOfflineReceiver","new");
        dialogBuilder.setTitle("Warning");
        Log.d("ForceOfflineReceiver","setTitle");
        dialogBuilder.setMessage("You are forced to be offline.Please try to login again.");
        Log.d("ForceOfflineReceiver","setMessage");
        dialogBuilder.setCancelable(false);
        Log.d("ForceOfflineReceiver","setCancelable");


        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("ForceOfflineReceiver","dian ji shi jian");

                //销毁所有的活动
                ActivityCollector.finishAll();
                Intent intent = new Intent(context,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();

        Log.d("ForceOfflineReceiver","dialogBuilder.create");
        //需要设置 AlertDialog 的类型，保证在广播接收器中可以正常弹出
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
        Log.d("ForceOfflineReceiver","show");
    }
}
