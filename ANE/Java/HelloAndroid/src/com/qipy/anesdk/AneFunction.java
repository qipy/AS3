package com.qipy.anesdk;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AneFunction implements FREFunction {

	private FREContext freContext;
	private String funKey;
	private boolean isInit;

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		if(args.length < 1) return null;
		FREObject reObject = null;
		try {
			this.freContext = context;
			this.funKey = args[0].getAsString();
			reObject = execute(args);
		} catch (Exception e) {
			AneEventBroadcast.broadcast(AneEvents.EVENT_EXCPTION, e.getMessage());
			Log.e("AneFunctionError", e.getMessage());
		}
		
		return reObject;
	}
	
	protected FREObject execute(FREObject[] args)throws Exception{
		if(funKey.equals("init")){
			init();
		}
		return null;
	}
	
	protected void showAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(freContext.getActivity().getApplicationContext());
	    builder.setCancelable(false);//设置当点击返回按钮后，默认表示的行为。这里设置为false  
	    builder.setMessage("dialog弹窗标题");  
	    builder.setPositiveButton("Yes", null);
	    builder.show();  
	    AneEventBroadcast.broadcast(AneEvents.EVENT_EXCPTION, "AlertDialog");
	}
	
	
	private void init(){
		if(isInit) return;
		isInit = true;
		
		AneEventBroadcast.initBroadcast(freContext);//鍒濆鍖朼ne骞挎挱鍣�
		
		//鐩戝惉灞忓箷浜捣浠ュ強閿佸睆浜嬩欢
		final IntentFilter filter = new IntentFilter();  
		filter.addAction(Intent.ACTION_SCREEN_OFF);  
		filter.addAction(Intent.ACTION_SCREEN_ON); 
		filter.addAction(Intent.ACTION_USER_PRESENT);
		freContext.getActivity().registerReceiver(screen_ON_OFF_BroadcastReceiver, filter);
		showAlert();
	}
	
	private final BroadcastReceiver screen_ON_OFF_BroadcastReceiver = new BroadcastReceiver() {  
	       @Override  
	       public void onReceive(final Context context, final Intent intent) {  
	           final String action = intent.getAction();  
	          if(Intent.ACTION_SCREEN_ON.equals(action)){  
	               AneEventBroadcast.broadcast(AneEvents.EVENT_SCREEN_ON, "AneEvents.EVENT_SCREEN_ON");
	          }else if(Intent.ACTION_SCREEN_OFF.equals(action)){  
	        	  AneEventBroadcast.broadcast(AneEvents.EVENT_SCREEN_OFF, "AneEvents.EVENT_SCREEN_ON");
	          } else if(Intent.ACTION_USER_PRESENT.equals(action)){  
	        	  AneEventBroadcast.broadcast(AneEvents.EVENT_USER_PRESENT, "AneEvents.EVENT_USER_PRESENT");
	          } 
	       }  
	   };

}
