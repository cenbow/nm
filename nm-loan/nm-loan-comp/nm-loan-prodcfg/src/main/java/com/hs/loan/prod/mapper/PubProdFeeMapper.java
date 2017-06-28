package com.hs.loan.prod.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.bo.PubProdFeeBo;
import com.hs.loan.prod.entity.PubProdFee;

/**
 * PUB_产品与费用项关系 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubProdFeeMapper extends BaseMapper<PubProdFee>{

	public void deletePubPrdoFeeByprodNo(String prodNo);

	public List<String> queryProdFeeForIvnNum(String prodNo);
	
	public List<PubProdFee> queryCustSelFeeList(Map<String,Object> map);

	public void deleteByProdNoAndInum(String prodNo, String inum);

	public List<PubProdFeeBo> queryForListFee2(Map<String, Object> param);

	public List<PubProdFeeBo> queryForListFee(Map<String, Object> param);

	public Integer insertPubProdPrefee(Map map);

	public Integer delPubProdPrefee(Map map);

}