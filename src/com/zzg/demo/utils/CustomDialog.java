package com.zzg.demo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zzg.demo.R;

public class CustomDialog {
	private AlertDialog.Builder builder;
	private Context context;

	public CustomDialog(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void createDialog(String buttontext, String title, String message,
			final CallBack callBack) {
		builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setCancelable(true);
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.setMessage(message);
		builder.setPositiveButton(buttontext, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				callBack.isConfirm(arg0, true);

			}
		});
		builder.create().show();
	}

	public void createWarnDialog(String title, String message,
			final CallBack callBack) {
		builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				callBack.isConfirm(arg0, true);
			}
		});
		builder.create().show();
	}

	public interface CallBack {
		public void isConfirm(DialogInterface arg0, boolean flag);
	}

	public void createToasts(String message, LayoutInflater layoutInflater) {
		// Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		View view = layoutInflater.inflate(R.layout.toast, null);
		TextView textView = (TextView) view.findViewById(R.id.text);
		textView.setText(message);

		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(view);
		toast.show();
	}
}
