package com.qipy.anesdk;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class StartMeFunc implements FREFunction {

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		String name = "";
		try{
			name = arg1[0].getAsString();
		} catch (Exception e) {
			Log.e("AneFunctionError", e.getMessage());
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(arg0.getActivity().getBaseContext());
	    builder.setCancelable(false);//设置当点击返回按钮后，默认表示的行为。这里设置为false  
	    builder.setMessage("dialog弹窗标题");  
	    builder.setPositiveButton("Yes", null);
	    builder.show();  
	    AneEventBroadcast.broadcast(AneEvents.EVENT_EXCPTION, "AlertDialog");
		/*Intent __activityIntent = arg0.getActivity().getPackageManager().getLaunchIntentForPackage(name);
		arg0.getActivity().startActivity(__activityIntent);*/
		/*
		int taskId = arg0.getActivity().getTaskId();
		ActivityManager am =  (ActivityManager)arg0.getActivity().getSystemService("activity") ;
		am.moveTaskToFront(taskId, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);*/
		
		return null;
	}
	  
	      
	   
	

}
