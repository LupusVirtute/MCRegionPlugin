package com.lupus.messages;

import java.util.HashMap;
import java.util.Map;

public class MessageReplaceQuery {
	private Map<String,String> queryMap = new HashMap<>();
	public MessageReplaceQuery(){

	}
	public MessageReplaceQuery addString(String key,String value){
		queryMap.put(key,value);
		return this;
	}
	public MessageReplaceQuery removeString(String key){
		queryMap.remove(key);
		return this;
	}
	public Map<String,String> getQueryMap(){
		return queryMap;
	}
}
