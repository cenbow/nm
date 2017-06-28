package com.hs.loan.approv;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hs.base.entity.CometData;
import com.hs.utils.HttpsInvokerUtil;
import com.hs.utils.ParamUtils;

import net.sf.json.JSONArray;


public class Test {
	public static void main(String[] args) {
		/*CometData cometData = new CometData();
		cometData.setChannel("SALE");
		cometData.setClient("luoxr");
		cometData.setContent("333");
		cometData.setList(new ArrayList<Object>());
		cometData.getList().add("第1条消息");
		cometData.getList().add("第2条消息");
		cometData.getList().add("第3条消息");
		cometData.getList().add("第4条消息");
		String json = JSON.toJSONString(cometData);
		System.out.println(json);*/
		CometData cometData = new CometData();
		cometData.setChannel("sale");
		cometData.setClient("cd-wuqing");
		cometData.setContent("您有一笔分期被审批拒绝,客户名称:[]");
		String json = JSON.toJSONString(cometData); 
		//HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json.toString());
		HttpsInvokerUtil.postJson("http://push.lemonjinfu.com/push/api/server/push", json.toString());
	}
}
