package com.hs.loan.cust.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustLevelApi;
import com.hs.loan.cust.bo.CustStarLevelBo;
import com.hs.loan.cust.dto.CustStarLevelBoDto;
import com.hs.loan.cust.service.AppCustLevelService;
import com.hs.utils.BeanUtils;

/**
 * 客户评级 服务
 * @author zwr
 *
 */

@Service
@Transactional(readOnly = true)
public class CustLevelServer implements CustLevelApi {

	@Autowired
	private AppCustLevelService appCustLevelService;
	
	/**
	 * 获取级别定义（满足多少分为一级，以此类推）
	 * 返回以从小到大的级别顺序对应的评分。
	 * 
	 * @return
	 */
	public String[] getLevelDefine(){
		return new String[]{"20","40","60","80","100"};
	}

	/**
	 * 通过客户号 获取 星级评估信息。
	 * 
	 * @param custNo
	 * @return
	 */
	public CustStarLevelBoDto getStarEvaluate(String custNo) {
		CustStarLevelBo custStarLevelBo = appCustLevelService.getStarEvaluate(custNo);
		CustStarLevelBoDto custStarLevelBoDto = (CustStarLevelBoDto) BeanUtils.copyPropertiesNotNull(new CustStarLevelBoDto(), custStarLevelBo);
		return custStarLevelBoDto;
	}
	
	
	
}
