package com.hs.loan.finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.bo.SaleContractBO;
import com.hs.loan.finance.entity.PubFundChanInfo;

/**
 * PUB_资金渠道信息 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubFundChanInfoMapper extends BaseMapper<PubFundChanInfo>{

	//返回没在chanNos里的资金渠道信息
	List<PubFundChanInfo> queryNotUseFundChanInfo(@Param("chanNos") String chanNos);
	//查询销售合同列表
	List<SaleContractBO> querySaleContractList(Map param);
}