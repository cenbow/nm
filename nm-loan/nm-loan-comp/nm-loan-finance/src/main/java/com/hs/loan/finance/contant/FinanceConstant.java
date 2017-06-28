package com.hs.loan.finance.contant;

public class FinanceConstant {
	
	//交易类型----对公
	public static final String TRANSTYPE_PUBLIC = "100230";
	//交易类型----单笔
	public static final String TRANSTYPE_SINGLE = "100210";
	//交易类型----批量
	public static final String TRANSTYPE_BATCH = "100220";
	
	
	//冲账渠道----支付宝
	public static final String STRIKECHAN_ALIPAY = "100232";
	//冲账渠道----银联
	public static final String STRIKECHAN_UNIONPAY = "100210";
	//冲账渠道----现金
	public static final String STRIKECHAN_CASH = "100233";
	
	//渠道一
	public static final String CHL_XT = "002";
	
	//单笔扣款页来源-代扣
	public static final String SINGLE_REPAY_DK = "dk";
	//单笔扣款页来源-还款计划登记（提前结清）
	public static final String SINGLE_REPAY_JH = "jh";
	//单笔扣款页来源-催收单笔还款
	public static final String SINGLE_REPAY_CS_HK = "cshk";
	//单笔扣款页来源-催收结清
	public static final String SINGLE_REPAY_CS_JQ = "csjq";
	//单笔扣款页来源-委外
	public static final String SINGLE_REPAY_WW = "ww";
	
	
	
	
	
	/*********************************************************/
	
	//成功
	public static final String SUCC = "SUCC";
	//失败
	public static final String FILED = "FILED";
	
	//预处理表状态-锁表
	public static final String PRETREAT_STAT_LOCK = "20101002";
	//预处理表状态-未锁表
	public static final String PRETREAT_STAT_UNLOCK = "20101001";
	//预处理表状态-已结清
	public static final String PRETREAT_STAT_CLEARD = "20101003";
	
	//交易方----平台
	public static final String ACCTFLAG_PT = "20101101";
	//交易方----资方
	public static final String ACCTFLAG_XT = "20101102";
	
	//预处理表扣款状态  未扣款
	public static final String WITHSTAT_UNDK = "20109001";
	//预处理表扣款状态 扣款中	
	public static final String WITHSTAT_DKING = "20109002";
	//预处理表扣款状态 扣款成功	
	public static final String WITHSTAT_DKSUCC = "20109003";
	//预处理表扣款状态 扣款失败	
	public static final String WITHSTAT_DKFIELD = "20109004";
	//预处理表扣款状态 取消	
	public static final String WITHSTAT_DKREV = "20109005";
	//预处理表扣款状态 信托扣款成功	
	public static final String WITHSTAT_DKXTSUCC = "20109006";
	//预处理表扣款状态 平台扣款成功	
	public static final String WITHSTAT_DKPTSUCC = "20109007";
	
	
	//交易大类----正常
	public static final String TRANTYPE_NORMAL = "20101201";
	//交易大类----冲账
	public static final String TRANYPE_STRIKE = "20101203";
	//交易大类----补账
	public static final String TRANYPE_PREM = "20101202";
	
	//交易状态----正常
	public static final String LOAN_STAT_ZC = "20101410";
	//交易状态----逾期
	public static final String LOAN_STAT_YQ = "20101420";
	//交易状态----委外
	public static final String LOAN_STAT_WW = "20101430";
	
		
	//广银联配置
	public static final String GZ_CHINAPAY_URL = "GZ_CHINAPAY_URL";//交易接口
	public static final String GZ_CHINAPAY_MERID = "GZ_CHINAPAY_MERID";//商户id
	public static final String GZ_CHINAPAY_MERKEY_FILEPATH = "GZ_CHINAPAY_MERKEY_FILEPATH";//私钥
	public static final String GZ_CHINAPAY_PUBKEY_FILEPATH = "GZ_CHINAPAY_PUBKEY_FILEPATH";//公钥
	public static final String GZ_CHINAPAY_USER_USERNAME = "GZ_CHINAPAY_USER_USERNAME";//用户名
	public static final String GZ_CHINAPAY_USER_PASSWORD = "GZ_CHINAPAY_USER_PASSWORD";//密码
	public static final boolean GZ_CHINAPAY_BATCH_TYPE = false;//是否压缩
	public static final int TRANSAMT_PROPORTION = 100;//银联代扣 单位/元
	
	public static final String  gz_template_singleDk_classpath = "gz_template_singleDk_classpath";
	public static final String gz_template_batchDk_classpath = "gz_template_batchDk_classpath";
	public static final String gz_template_transQuery_classpath = "gz_template_transQuery_classpath";
	
	//by hwen 2013 9 24
		//银联状态
	public static final String TRAN_ST_FIELD = "3008";//余额不足
	public static final String TRAN_ST_SUCC = "0000";//交易成功
	public static final String TRAN_ST_DEALING = "2000";//单扣处理中
	
	public static final String NEW_TRAN_ST_FIELD = "51";//余额不足
	public static final String NEW_TRAN_ST_SUCC = "1001";//交易成功
	public static final String NEW_TRAN_ST_CODE_SUCC = "00";//交易成功
	
	/*银联服务调用地址*/
	public static final String single_repay_url="single_repay_url";
	public static final String	batch_repay_url="batch_repay_url";
	public static final String	query_repay_url="query_repay_url";
//	public static final String single_repay_url="http://127.0.0.1:8080/tk-unionpay-server/dk/single";
//	public static final String	batch_repay_url="http://127.0.0.1:8080/tk-unionpay-server/dk/batch";
//	public static final String	query_repay_url="http://127.0.0.1:8080/tk-unionpay-server/query";
	
	/*还款类型*/
	//提前还款	50201001
	public static final String REPAY_TYPE_NOM_CLEAR = "50201002"; //提前结清	50201002
	//正常还款	50201003
	//失败重扣	50201004
	public static final String REPAY_TYPE_OVER_DK = "50201005";//催收扣款	50201005
	//催收提前还款	50201006
	public static final String REPAY_TYPE_OVER_CLEAR = "50201007";//催收提前结清	50201007
	public static final String REPAY_TYPE_OVER_OUT_DK = "50201007";//委外扣款	50201008
 
	
	//通联扣款 配置
	public static final String TL_CHINAPAY_URL = "TL_CHINAPAY_URL";//交易接口
	public static final String TL_CHINAPAY_MERID = "TL_CHINAPAY_MERID";//商户id
	public static final String TL_CHINAPAY_MERKEY_FILEPATH = "TL_CHINAPAY_MERKEY_FILEPATH";//私钥
	public static final String TL_CHINAPAY_PUBKEY_FILEPATH = "TL_CHINAPAY_PUBKEY_FILEPATH";//公钥
	public static final String TL_CHINAPAY_USER_USERNAME = "TL_CHINAPAY_USER_USERNAME";//用户名
	public static final String TL_CHINAPAY_USER_PASSWORD = "TL_CHINAPAY_USER_PASSWORD";//密码
	public static final boolean TL_CHINAPAY_BATCH_TYPE = false;//是否压缩
	public static final String tl_template_singleDk_classpath = "tl_template_singleDk_classpath";
	public static final String tl_template_batchDk_classpath = "tl_template_batchDk_classpath";
	public static final String tl_template_transQuery_classpath = "tl_template_transQuery_classpath";
	public static final String TL_TRAN_ST_SUCC = "4000";//交易成功
	
	/*通联服务调用地址*/
	public static final String tl_single_repay_url="tl_single_repay_url";
	public static final String tl_batch_repay_url="tl_batch_repay_url";
	public static final String tl_query_repay_url="tl_query_repay_url";
//	public static final String tl_single_repay_url="http://127.0.0.1:8080/tk-allinpay-server/dk/single";
//	public static final String	tl_batch_repay_url="http://127.0.0.1:8080/tk-allinpay-server/dk/batch";
//	public static final String	tl_query_repay_url="http://127.0.0.1:8080/tk-allinpay-server/query";
	
	
	/*中金服务调用地址*/
	/**
	 * 中金单笔扣款URL
	 */
	public static final String zj_single_repay_url="zj_single_repay_url";
	/**
	 * 中金批量扣款URL
	 */
	public static final String zj_batch_repay_url="zj_batch_repay_url";
	/**
	 * 中金单笔扣款结果查询URL	
	 */
	public static final String zj_single_query_repay_url="zj_single_query_repay_url";
	/**
	 * 中金批量扣款结果查询URL
	 */
	public static final String zj_batch_query_repay_url="zj_batch_query_repay_url";
	/**
	 * 中金资方扣款批量URL
	 */
	public static final String zj_zf_batch_repay_url="zj_zf_batch_repay_url";
	/**
	 * 中金资方单笔扣款URL
	 */
	public static final String zj_zf_single_repay_url = "zj_zf_single_repay_url";
	//连连支付服务调用地址
	/**连连支付单扣地址*/
	public static final String lianlian_single_repay_url="lianLemonSinglePay";
	/**连连支付批扣地址*/
	public static final String lianlian_batch_repay_url="lianLemonBatchPay";
	
	
	//快付通返回状态
	public static final String KFT_TRAN_ST_SUCC = "FSBR0000";//交易成功
	
	public static final String ALL_ZT_TRUE = "10001001";
	public static final String ALL_ZT_FALSE = "10001002";
	/**结清类型正常*/
	public static final String CLEARN_TYPE_ZC = "50105001";  
	/**结清类型逾期 */
	public static final String CLEARN_TYPE_YQ = "50105002";
	/**结清类型委外*/
	public static final String CLEARN_TYPE_WW = "50105003";
}
