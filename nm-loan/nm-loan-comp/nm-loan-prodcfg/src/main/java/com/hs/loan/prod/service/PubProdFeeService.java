package com.hs.loan.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.bo.PubProdFeeBo;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.mapper.PubProdFeeMapper;
import com.hs.utils.StringUtils;

/**
 * PUB_产品与费用项关系 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubProdFeeService{
	@Autowired
	private PubProdFeeMapper pubProdFeeMapper;
	public Integer delPubProdPrefee(Map map){
		return  pubProdFeeMapper.delPubProdPrefee(map);
	}
	public Integer insertPubProdPrefee(Map map){
		return pubProdFeeMapper.insertPubProdPrefee(map);
	}
	
	/**
	 * 新增 PUB_产品与费用项关系
	 * @param pubProdFee
	 * @return
	 */
	@Transactional
	public void insert(PubProdFee pubProdFee){
		pubProdFeeMapper.insert(pubProdFee);
	}

	/**
	 * 通过主键修改 PUB_产品与费用项关系
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdFeeMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_产品与费用项关系
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdFeeMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过产品编号删除 PUB_产品与费用项关系
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByProdNo(String prodNo){
		pubProdFeeMapper.deletePubPrdoFeeByprodNo(prodNo);
	}
	/**
	 * 通过费用号查询 PUB_产品与费用项关系
	 * 
	 * @param primaryKey
	 * @return 返回true可以删除 费用项 false则不可以删除 说明费用项和已有产品有关系
	 */
	public boolean queryExistRelaProdAndFee(String feeNo){
		if(StringUtils.isEmpty(feeNo)){
			throw new ServiceException("费用项编号为空");
		}
		Map<String, Object> param = new HashMap<>();
		param.put("feeNo", feeNo);
		List<PubProdFee> list= this.queryForList(param);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
	/**
	 * 通过主键取得 PUB_产品与费用项关系 对象
	 * @param primaryKey
	 * @return
	 */
	public PubProdFee getByPrimaryKey(String primaryKey){
		return pubProdFeeMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_产品与费用项关系 列表
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdFee> queryForList(Map<String, Object> param){
		return pubProdFeeMapper.queryForList(param);
	}
	/**
	 * 查询 PUB_产品与费用项关系 列表
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdFeeBo> queryForListFee(Map<String, Object> param){
		return pubProdFeeMapper.queryForListFee(param);
	}
	public List<PubProdFeeBo> queryForListFee2(Map<String, Object> param){
		return pubProdFeeMapper.queryForListFee2(param);
	}
	/**
	 * 查询 PUB_产品与费用项关系 -费用项distinct
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdFee> queryCustSelFeeList(Map<String, Object> param){
		return pubProdFeeMapper.queryCustSelFeeList(param);
	}
	
	/**
	 * 查询 PUB_产品与费用项关系 分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<PubProdFee> queryForPage(Page<PubProdFee> page){
		pubProdFeeMapper.queryForList(page.getPageParams());
		return (Page<PubProdFee>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询产品可选期数
	 * @param prodNo
	 * @return
	 */
	public List<String> queryProdFeeForIvnNum(String prodNo) {
		return pubProdFeeMapper.queryProdFeeForIvnNum(prodNo);
	}

	//产品编号和期数删除费用项关系信息
	public void deleteByProdNoAndInum(String prodNo, String inum) {
		pubProdFeeMapper.deleteByProdNoAndInum(prodNo,inum);
		
	}
	 
}