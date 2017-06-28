package com.hs.loan.approve.contant;

public class ApproveContant {

public static final String GROUP_DEFAULT = "__defaultGroup";
	
	/*** 资金匹配状态 -- 待匹配 */
	public static final String FUNDMATCHSTAT_DPP = "40003001";
	/*** 资金匹配状态 -- 匹配中 */
	public static final String FUNDMATCHSTAT_PPZ="40003002";
	/*** 资金匹配状态 -- 匹配完成  */
	public static final String FUNDMATCHSTAT_PPWC="40003003";
	
	/*** 匹配类型 -- 自动匹配 */
	public static final String FUNDMATCHTYPE_ZDPP = "40104001";
	/*** 匹配类型 -- 人工匹配 */
	public static final String FUNDMATCHTYPE_RGPP = "40104002";
	/*** 资金渠道匹配结果 -- 匹配成功  */
	public static final String FUNDMATCHRST_PPCG = "40103001";
	/*** 资金渠道匹配结果 -- 匹配失败 */
	public static final String FUNDMATCHRST_PPSB = "40103002";
	/*** 资金渠道匹配结果 --  匹配成功变更 */
	public static final String FUNDMATCHRST_PPCGBG = "40103003";
	
	/*** 系统状态 -- 正常  */
	public static final String AVAILABLESTAT_VALID = "10002001";
	/*** 系统状态 -- 失效 */
	public static final String AVAILABLESTAT_UNVALID = "10002002";
}
