package com.hs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMsgTest {
//	@Test
//	public void send() {
//		String url = "http://uat.lemoncome.com:3070/nm-msg-server/msg/sms/send.json";
//		Map<String, Object> param = new HashMap<>();
//		param.put("mob", "13880768541");
//		param.put("msg", "{name}您好，我们公司的支付宝账号是：cw@lemonjinfu.com，请在还款备注上填写您的姓名。详询4009950906【柠檬分期】");
////		param.put("flag", "sensitive");
//
//		List<NameValuePair> parameters = new ArrayList<>();
//		Set<String> keys = param.keySet();
//		for (String key : keys) {
//			parameters.add(new BasicNameValuePair(key, param.get(key).toString()));
//		}
//		try {
//			System.out.println(HttpsInvokerUtil.executeHttpPost(url, parameters));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


    public static void main(String[] args) throws Exception {
        //com.alibaba.dubbo.container.Main.main(args);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/spring/applicationContext-dubbo-service.xml");
        context.start();

        System.in.read(); // 按任意键退出

    }
}
