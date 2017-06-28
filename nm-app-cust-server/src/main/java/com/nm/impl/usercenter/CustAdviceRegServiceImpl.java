package com.nm.impl.usercenter;


import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.base.framework.core.validate.Validator;
import com.nm.mapper.usercenter.CustAdviceRegMapper;
import com.nm.service.usercenter.CustAdviceRegService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 上午11:07:18
 */
@Service
public class CustAdviceRegServiceImpl implements CustAdviceRegService {
	
	@Resource
	private CustAdviceRegMapper custAdviceRegMapper;

	@Override
	public String addAdviceReg(Map<String, Object> map) {
		String regFlag = "0";
		  Validator.init(map, "意见反馈信息").required().end()
          .get("phoneNo", "手机号").required().isPhone().end()
          .get("adviceContent", "反馈内容").required().rangeLength(1, 500).end()
          .get("emailAddress", "邮箱地址").required().rangeLength(4, 20).end();
		  map.put("id", RandomUtil.getUUID());
		  map.put("insDate", DateUtils.getCurDate());
		  int type =   custAdviceRegMapper.addAdviceReg(map);
		  if( type < 1){
			  throw new ServiceException("添加意见反馈失败");
		  }
		   regFlag = "1";
		return regFlag;
	}



}
