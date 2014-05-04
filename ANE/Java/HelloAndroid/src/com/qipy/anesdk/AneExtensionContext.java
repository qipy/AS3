package com.qipy.anesdk;


import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class AneExtensionContext extends FREContext {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> funMap = new HashMap<String, FREFunction>();
		
		funMap.put("aneInits", new AneFunction());//
		funMap.put("startMe", new StartMeFunc());//
		return funMap;
	}
	
}
