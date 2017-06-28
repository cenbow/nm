/**
 * @desc 服务器常量类配置(公用项目)
 * @remark 注意添加常量时, 请更新svn，添加后请立即提交
 * <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 * <%@ page import="com.hs.commons.constants.PubBusinessConstant"%>
 * <%@ page import="com.hs.commons.constants.CommonConstant" %>
 */
window.PubBusinessConstant = {
	SALETYPE_GROUP : '<%=PubBusinessConstant.SALETYPE_GROUP%>',
	SALETYPE_CROWD : '<%=PubBusinessConstant.SALETYPE_CROWD%>',


	/***附件类型——身份证正面       */
	ATTTYPE_SFZZM: '<%=PubBusinessConstant.ATTTYPE_SFZZM%>',
	/***附件类型——身份证反面       */
	ATTTYPE_SFZFM: '<%=PubBusinessConstant.ATTTYPE_SFZFM%>',
	/***附件类型——银行卡       */
	ATTTYPE_YHK: '<%=PubBusinessConstant.ATTTYPE_YHK%>',
	/***附件类型——现场照      */
	ATTTYPE_XCZ: '<%=PubBusinessConstant.ATTTYPE_XCZ%>',
	/***附件类型——学生证    */
	ATTTYPE_XSZ: '<%=PubBusinessConstant.ATTTYPE_XSZ%>',
	/***附件类型——通话详单       */
	ATTTYPE_THXD: '<%=PubBusinessConstant.ATTTYPE_THXD%>',
	/***附件类型——提货照     */
	ATTTYPE_THZ: '<%=PubBusinessConstant.ATTTYPE_THZ%>',
	/***附件类型——其他1       */
	ATTTYPE_QT1: '<%=PubBusinessConstant.ATTTYPE_QT1%>',
	/***附件类型——其他2	       */
	ATTTYPE_QT2: '<%=PubBusinessConstant.ATTTYPE_QT2%>',
	/***附件类型——其他3	       */
	ATTTYPE_QT3: '<%=PubBusinessConstant.ATTTYPE_QT3%>',
	
	/***客户类型—— 成人*/
	CUSTTYPE_CR: '<%=PubBusinessConstant.CUSTTYPE_CR%>',
	/***客户类型——学生	       */
	CUSTTYPE_XS: '<%=PubBusinessConstant.CUSTTYPE_XS%>',
	/***合同类型--申请个人等额本息*/
	CONTRACTTYPE_CR: '<%=PubBusinessConstant.CONTRACTTYPE_CR%>',
	/***合同类型--申请学生等额本息	       */
	CONTRACTTYPE_XS: '<%=PubBusinessConstant.CONTRACTTYPE_XS%>',
	
	
	/*** 分期状态 —— 未提交*/
	LOANSTAT_UNCOMMIT: '<%=PubBusinessConstant.LOANSTAT_UNCOMMIT%>',   
	/*** 分期状态 —— 提交待审批 */
	LOANSTAT_WATEAPPROV:'<%=PubBusinessConstant.LOANSTAT_WATEAPPROV%>',    
	/*** 分期状态 —— 审批驳回 */
	LOANSTAT_REJECTED:'<%=PubBusinessConstant.LOANSTAT_REJECTED%>',    
	/*** 分期状态 ——审批拒绝 */
	LOANSTAT_REFUSED:'<%=PubBusinessConstant.LOANSTAT_REFUSED%>',    
	/*** 分期状态 —— 审批通过*/
	LOANSTAT_PASS:'<%=PubBusinessConstant.LOANSTAT_PASS%>',   
	/*** 分期状态 —— 客户签约 */
	LOANSTAT_SIGNING:'<%=PubBusinessConstant.LOANSTAT_SIGNING%>',    
	/*** 分期状态 —— 已放款未结清 */
	LOANSTAT_UNSETTLEMENT :'<%=PubBusinessConstant.LOANSTAT_UNSETTLEMENT%>',
	/*** 分期状态 —— 正常结清 */
	LOANSTAT_SETTLEMENT:'<%=PubBusinessConstant.LOANSTAT_SETTLEMENT%>',   
	/*** 分期状态 —— 提前结清 */
	LOANSTAT_EARLYSETTLEMENT :'<%=PubBusinessConstant.LOANSTAT_EARLYSETTLEMENT%>',    
	/*** 分期状态 —— 逾期结清 */
	LOANSTAT_SETTLEOVERDUE :'<%=PubBusinessConstant.LOANSTAT_SETTLEOVERDUE%>',    
	/*** 分期状态 —— 取消*/
	LOANSTAT_CANCEL :'<%=PubBusinessConstant.LOANSTAT_CANCEL%>',       
	/*** 分期状态 —— 撤销       */
	LOANSTAT_UNDO :'<%=PubBusinessConstant.LOANSTAT_UNDO%>',  

	/*** 首付类型 —— 按比例*/
	FSTPAYTYPE_RATE: '<%=PubBusinessConstant.FSTPAYTYPE_RATE%>',
	/*** 首付类型 —— 按金额*/
	FSTPAYTYPE_AMT: '<%=PubBusinessConstant.FSTPAYTYPE_AMT%>',

    /*** 角色-超级管理员*/
	ROLE_R_SYS_SUPER: '<%=PubBusinessConstant.ROLE_R_SYS_SUPER%>',				   //超级管理员
	/*** 角色-系统运维*/
	ROLE_R_SYS_OPRATE: '<%=PubBusinessConstant.ROLE_R_SYS_OPRATE%>',                //系统运维
	/*** 角色-总经理*/
	ROLE_R_MGR: '<%=PubBusinessConstant.ROLE_R_MGR%>',                              //总经理
	/*** 角色-销售经理 */
	ROLE_R_SALE_MGR: '<%=PubBusinessConstant.ROLE_R_SALE_MGR%>',                    //销售经理
	/*** 角色-大区经理 */
	ROLE_R_SALE_MGR_REGION: '<%=PubBusinessConstant.ROLE_R_SALE_MGR_REGION%>',      //大区经理
	/*** 角色-区域经理    */
	ROLE_R_SALE_MGR_AREA: '<%=PubBusinessConstant.ROLE_R_SALE_MGR_AREA%>',          //区域经理
	/*** 角色-销售员*/
	ROLE_R_SALE_STAFF: '<%=PubBusinessConstant.ROLE_R_SALE_STAFF%>',                //销售员
	/*** 角色-商户*/
	ROLE_R_MERCHANT: '<%=PubBusinessConstant.ROLE_R_MERCHANT%>',                    //商户
	/*** 角色-回访*/
	ROLE_R_BACKCALL: '<%=PubBusinessConstant.ROLE_R_BACKCALL%>',                    //回访
	/*** 角色-运营经理*/
	ROLE_R_OPRATE_MGR: '<%=PubBusinessConstant.ROLE_R_OPRATE_MGR%>',                //运营经理
	/*** 角色-运营人员*/
	ROLE_R_OPRATE_SATAFF: '<%=PubBusinessConstant.ROLE_R_OPRATE_SATAFF%>',          //运营人员
	/*** 角色-客服人员*/
	ROLE_R_OPRATE_CUSTSER: '<%=PubBusinessConstant.ROLE_R_OPRATE_CUSTSER%>',        //客服人员
	/*** 角色-催收经理*/
	ROLE_R_COLLEC_MGR: '<%=PubBusinessConstant.ROLE_R_COLLEC_MGR%>',                //催收经理
	/*** 角色-催收人员*/
	ROLE_R_COLLEC_STAFF: '<%=PubBusinessConstant.ROLE_R_COLLEC_STAFF%>',            //催收人员
	/*** 角色-催收质检*/
	ROLE_R_COLLEC_CHECK: '<%=PubBusinessConstant.ROLE_R_COLLEC_CHECK%>',            //催收质检
	/*** 角色-委外公司  */
	ROLE_R_COLLEC_OUT_COMP: '<%=PubBusinessConstant.ROLE_R_COLLEC_OUT_COMP%>',      //委外公司
	/*** 角色-委外专员*/
	ROLE_R_COLLEC_OUT_STAFF: '<%=PubBusinessConstant.ROLE_R_COLLEC_OUT_STAFF%>',    //委外专员
	/*** 角色-审批经理   */
	ROLE_R_APPR_MGR: '<%=PubBusinessConstant.ROLE_R_APPR_MGR%>',                    //审批经理
	/*** 角色-审批人员*/
	ROLE_R_APPR_STAFF: '<%=PubBusinessConstant.ROLE_R_APPR_STAFF%>',                //审批人员
	/*** 角色-报表专员 */
	ROLE_R_OPRATE_RPT: '<%=PubBusinessConstant.ROLE_R_OPRATE_RPT%>',                //报表专员
	/*** 角色-资方业务查询*/
	ROLE_R_OPRATE_FUNDQRY: '<%=PubBusinessConstant.ROLE_R_OPRATE_FUNDQRY%>',        //资方业务查询
	/*** 角色-安全合规部*/
	ROLE_R_SAFE_MGR: '<%=PubBusinessConstant.ROLE_R_SAFE_MGR%>',                    //安全合规部
	/*** 角色-安全员*/
	ROLE_R_SAFE_STAFF: '<%=PubBusinessConstant.ROLE_R_SAFE_STAFF%>',                //安全员
	/*** 角色-财务经理     */
	ROLE_R_FINANCI_MGR: '<%=PubBusinessConstant.ROLE_R_FINANCI_MGR%>',              //财务经理
	/*** 角色-财务（业务）  */
	ROLE_R_FINANCI_STAFF: '<%=PubBusinessConstant.ROLE_R_FINANCI_STAFF%>',          //财务（业务）
	/*** 角色-市场部经理    */
	ROLE_R_MARKET_MGR: '<%=PubBusinessConstant.ROLE_R_MARKET_MGR%>',                //市场部经理
	/*** 角色-市场专员    */
	ROLE_R_MARKET_STAFF: '<%=PubBusinessConstant.ROLE_R_MARKET_STAFF%>',             //市场专员



	/*** 没有常量值 */
	DEFAULT_KEY: ''
}

window.CommonConstant = {
	/*** 是否标志 —— 是 */
	COMMON_YES: '<%=CommonConstant.COMMON_YES%>',
	/*** 是否标志 —— 否 */
	COMMON_NO: '<%=CommonConstant.COMMON_NO%>',

	/*** 没有常量值 */
	DEFAULT_KEY: ''
}