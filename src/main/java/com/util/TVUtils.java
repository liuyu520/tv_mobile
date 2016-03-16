package com.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;

import com.dict.Constant2;
import com.entity.User;
import com.string.widget.util.ValueWidget;

public class TVUtils {
	public static ListOrderedMap getListOrderedMap(){
		ListOrderedMap orderColumnModeMap=new ListOrderedMap();
		orderColumnModeMap.put("sticktop", "desc");
		orderColumnModeMap.put("stickTime", "desc");
		orderColumnModeMap.put("releaseTime", "desc");
		return orderColumnModeMap;
	}
	
	
}
