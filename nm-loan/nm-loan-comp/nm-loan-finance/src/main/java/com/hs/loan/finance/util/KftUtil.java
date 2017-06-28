package com.hs.loan.finance.util;

import java.util.HashMap;
import java.util.Map;

public class KftUtil {
	private final static Map<String, String> bankMap = new HashMap<>();
	private final static Map<String, String> resultMap = new HashMap<>();

	static {
		// TODO Auto-generated constructor stub
		bankMap.put("100", "0025840");// 邮政银行
		bankMap.put("102", "1021000");// 工商
		bankMap.put("103", "1031000");// 中国农业银行
		bankMap.put("104", "1041000");// 中国银行
		bankMap.put("105", "1051000");// 中国建设银行
		bankMap.put("301", "3011000");// 交通银行
		bankMap.put("302", "3021000");// 中信银行
		bankMap.put("303", "3031000");// 中国光大银行
		bankMap.put("304", "3041000");// 华夏银行
		bankMap.put("305", "3051000");// 中国民生银行
		bankMap.put("306", "3051000");// 广发银行
		bankMap.put("307", "3135840");// 平安银行
		bankMap.put("308", "3085840");// 招商银行
		bankMap.put("309", "3091000");// 兴业银行
		bankMap.put("310", "3102900");// 上海浦东发展银行
		resultMap.put("FSBR0000", "处理成功");
		resultMap.put("FSBR1000", "确认失败");
		resultMap.put("FSBR1010", "发起行要求退票");
		resultMap.put("FSBR1012", "客户未打印存折");
		resultMap.put("FSBR1105", "直接贷记事先无协议");
		resultMap.put("FSBR1199", "其它原因导致入帐不成功");
		resultMap.put("FSBR1203", "付款人账号与户名不符");
		resultMap.put("FSBR1207", "直接借记支付中金额超过事先规定限额");
		resultMap.put("FSBR1210", "睡眠户不能扣款");
		resultMap.put("FSBR1212", "非结算类账户不能扣款");
		resultMap.put("FSBR1213", "账户密码挂失或卡密码挂失");
		resultMap.put("FSBR6017", "帐户质押额度不足或可用额度不足");
		resultMap.put("FSBR6018", "帐户授信额度不足或可用额度不足");
		resultMap.put("FSBR6019", "帐户圈存额度不足或可用额度不足");
		resultMap.put("FSBR6037", "账号或卡号不存在");
		resultMap.put("FSBR6038", "户名不符");
		resultMap.put("FSBR6039", "账户被冻结，无法入账");
		resultMap.put("FSBR6040", "金额不足");
		resultMap.put("FSBR6041", "账户状态错误");
		resultMap.put("FSBR6042", "账户密码错误");
		resultMap.put("FSBR6043", "当日通兑业务累计金额超过规定金额");
		resultMap.put("FSBR6044", "币种不支持");
		resultMap.put("FSBR6045", "金额大于单笔业务限额");
		resultMap.put("FSBR6046", "证件号码不符");
		resultMap.put("FSBR6051", "已收妥未入账");
		resultMap.put("FSBR6999", "其他原因(应写明相应原因)");
		resultMap.put("FSBR8001", "非本行票据");
		resultMap.put("FSBR8002", "出票人账户余额不足以支付支票款项");
		resultMap.put("FSBR8003", "支票必须记载的事项不全");
		resultMap.put("FSBR8004", "大、小写金额不符");
		resultMap.put("FSBR8005", "不得更改事项已更改或可更改事项未按规定更改");
		resultMap.put("FSBR8006", "出票人签章与预留银行签章不符");
		resultMap.put("FSBR8007", "约定使用支付密码的，支付密码未填写或错误");
		resultMap.put("FSBR8008", "超过提示付款期");
		resultMap.put("FSBR8009", "远期票据");
		resultMap.put("FSBR8010", "出票人账户已依法冻结");
		resultMap.put("FSBR8011", "出票人账号、户名不符");
		resultMap.put("FSBR8012", "背书不符合规定或背书不连续");
		resultMap.put("FSBR8013", "持票人开户行申请止付");
		resultMap.put("FSBR8014", "银行汇票签章、压数机压印金额模糊不清或密押有误");
		resultMap.put("FSBR8015", "支票出票签章模糊不清或变形");
		resultMap.put("FSBR8016", "持票人已办理挂失止付或已收到法院止付通知书");
		resultMap.put("FSBR8017", "电子清算信息与支票影像不相符");
		resultMap.put("FSBR8018", "出票人已销户");
		resultMap.put("FSBR8019", "数字签名或证书错误");
		resultMap.put("FSBR8020", "未收到实物票据");
		resultMap.put("FSBR8021", "重复提示付款");
		resultMap.put("FSBR8022", "持票人未作委托收款背书");
		resultMap.put("FSBR8101", "非本行付款(Notdrawnonthisbank)");
		resultMap.put("FSBR8102", "请与出票人接洽(Refertodrawer)");
		resultMap.put("FSBR8103", "收款人名称欠缺／涂改(Payee＇snameomitted／altered)");
		resultMap.put("FSBR8104", "金额欠缺／不清／涂改(Amountinwordsand／orfiguresrequired／illegible／altered)");
		resultMap.put("FSBR8105", "大小写金额不符(Wordandfiguresdiffer)");
		resultMap.put("FSBR8106", "出票日期欠缺／不明／涂改(Daterequired／illegible／altered)");
		resultMap.put("FSBR8107", "可更改事项更改，原记载人未签章证明(Alterationrequiresfullchop／signatureoftheoriginator)");
		resultMap.put("FSBR8108", "无出票人签章(Drawer＇schop／signaturerequired)");
		resultMap.put("FSBR8109", "出票人签章与预留银行签章不符(Drawer＇schop／signaturediffersfromspecimeninourpossession)");
		resultMap.put("FSBR8110", "超过提示付款期(Outofdateofpresentation)");
		resultMap.put("FSBR8111", "远期支票(Post－datedcheque)");
		resultMap.put("FSBR8112", "账户已冻结或无此账户(Drawer＇saccountfrozenordoesnotexist)");
		resultMap.put("FSBR8113", "账号户名不符(Drawer＇snameandaccountnumberdonotmatch)");
		resultMap.put("FSBR8114", "背书不符合规定(Endorsementirregular)");
		resultMap.put("FSBR8115", "未加盖票据交换章(Banker＇schearingchoprequired)");
		resultMap.put("FSBR8116", "其他原因(OtherReason)");
		resultMap.put("FSBR8201", "请与发票人接洽(Refertodrawer)");
		resultMap.put("FSBR8202", "托收款项尚未收到，请再入账(Drawnagainstuncollectedfunds,pleasepresentagain)");
		resultMap.put("FSBR8203", "此户已结清(Accountclosed)");
		resultMap.put("FSBR8204", "经已过期(Outofdate)");
		resultMap.put("FSBR8205", "票非即期(Postdated)");
		resultMap.put("FSBR8206", "此票已停止支付(Paymentcountermandedbythedrawer)");
		resultMap.put("FSBR8207", "金额文字与数码不符(Wordsandfiguresdiffer)");
		resultMap.put("FSBR8208", "金额文字未写/不全/不清(Amountinwordsrequired／incomplete／illegible)");
		resultMap.put("FSBR8209", "涂改处须发票人盖章/签字(Alterationrequiresdrawer＇sfullchop／signature)");
		resultMap.put("FSBR8210", "发票人图章/签字与本行存底不符(Drawer＇schop／signaturediffersfromspecimeninourpossession)");
		resultMap.put("FSBR8211", "发票人未盖章/签字(Drawer＇schop／signaturerequired)");
		resultMap.put("FSBR8212", "发票人图章/签字不完全(Drawer＇schop／signatureincomplete)");
		resultMap.put("FSBR8213", "发票日期不全/不明(Dateincomplete／illegible)");
		resultMap.put("FSBR8214", "此票破裂处须经发票人签章证明(Mutilatedchequesrequiredrawer＇sconfirmation)");
		resultMap.put("FSBR8215", "未经受款人/贵银行背书(Payee＇s／Banker＇sendorsementrequired)");
		resultMap.put("FSBR8216", "背书不妥/不清楚/漏失/不详(Endorsementirregular／illegible／missing／unknown)");
		resultMap.put("FSBR8217", "须发票人背书(＂CashorOrder＂chequesrequiredrawer＇sendorsement)");
		resultMap.put("FSBR8218", "划线支票不能以现金支付(Chequecrossed－notpayablebycash)");
		resultMap.put("FSBR8219", "支票填写未完整，漏填台头人姓名(Irregularlydrawn－payee＇snameomitted)");
		resultMap.put("FSBR8220", "贵银行未盖交换图章/横线图章(Banker＇sclearing／crossingchoprequired)");
		resultMap.put("FSBR8221", "汇价错误(Incorrectexchangerate)");
		resultMap.put("FSBR8222", "非我行付款(Notdrawnonthisbank)");
		resultMap.put("FSBR8223", "其他原因(OtherReason)");
		resultMap.put("FSBR9998", "通用拒绝");
		resultMap.put("FSBR9999", "通用失败");
	}

	public static String getKftBank(String bank) {
		return bankMap.get(bank);
	}

	public static String getResultStr(String resultCode) {
		return resultMap.get(resultCode);
	}
}
