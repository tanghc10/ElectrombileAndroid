package com.xiaoantech.electrombile.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

/**
 * Created by yangxu on 2016/12/14.
 */

public class CommonDialog {
    private String TAG = "CommonDialog";
    private Context mContext;
    private int customLayoutId;
    private String dialogTitle;
    private String positiveText;
    private String negativeText;

    private View dialogView;
    private OnDialogListener listener;

    public CommonDialog(Context context,int customLayoutId,String dialogTitle,String positiveText,String negativeText){
        this.mContext = context;
        this.customLayoutId = customLayoutId;
        this.dialogTitle = dialogTitle;
        this.positiveText = positiveText;
        this.negativeText = negativeText;
        this.dialogView = View.inflate(context,customLayoutId,null);
    }

    public void showDialog(){
        if(mContext == null){
            Log.e(TAG,"context is null");
            return;
        }
        CustomDialog.Builder dialog = new CustomDialog.Builder(mContext);
        dialog.setContentView(dialogView);
        if (positiveText != null){
            dialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (listener != null){
                        listener.dialogPositiveListener(dialogView,dialog,which);
                    }
                }
            });
        }
        if (negativeText != null){
            dialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null){
                        listener.dialogNegativeListener(dialogView,dialog,which);
                    }
                }
            });
        }
        dialog.create().show();
    }

    public CommonDialog setOnDialogListener(OnDialogListener listener){
        this.listener = listener;
        return this;
    }

    public interface OnDialogListener{
        public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which);
        public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which);
    }
}
