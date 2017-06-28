package com.hs.loan.acct.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.acct.entity.PubRepayFeeConf;
import com.hs.loan.acct.mapper.PubRepayFeeConfMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_费用项配置 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubRepayFeeConfService{
	@Autowired
	private PubRepayFeeConfMapper pubRepayFeeConfMapper;
	
	/**
	 * 新增 PUB_费用项配置
	 * @param pubRepayFeeConf 新增对象
	 */
	@Transactional
	public void insert(PubRepayFeeConf pubRepayFeeConf){
		pubRepayFeeConfMapper.insert(pubRepayFeeConf);
	}

	/**
	 * 通过主键修改 PUB_费用项配置
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubRepayFeeConfMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_费用项配置
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubRepayFeeConfMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_费用项配置 对象
	 * @param primaryKey 主键
	 * @return PUB_费用项配置对象
	 */
	public PubRepayFeeConf getByPrimaryKey(String primaryKey){
		return pubRepayFeeConfMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_费用项配置 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubRepayFeeConf> queryForList(Map<String, Object> param){
		return pubRepayFeeConfMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_费用项配置 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubRepayFeeConf> queryForPage(Page<PubRepayFeeConf> page){
		pubRepayFeeConfMapper.queryForList(page.getPageParams());
		return (Page<PubRepayFeeConf>)page.getPageParams().get(Page.KEY);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 保存或更新 费用配置项，
	 * 有id为更新，
	 * 无id为保存
	 * 
	 */
	@Transactional
	public void save(PubRepayFeeConf pubRepayFeeConf){
		String id = pubRepayFeeConf.getFeeNo();
		if(StringUtils.isEmpty(id)){//保存
			List<PubRepayFeeConf> lst = queryForList(BeanUtils.bean2mapExclude(pubRepayFeeConf,"instDate,updtDate"));
			if(lst!=null && lst.size()>0){
				return;
			}
			pubRepayFeeConf.setFeeNo(RandomUtil.getUUID());
			pubRepayFeeConf.setInstDate(new Date());
			insert(pubRepayFeeConf);
		}else{//更新
			Map<String,Object> param = BeanUtils.bean2map(pubRepayFeeConf);
			param.put("updtDate", new Date());
			updateByPrimaryKeySelective(param);
		}
	}
	
	/**
	 * 通过费用项编号 删除 费用项配置
	 */
	@Transactional
	public void deleteByNo(String feeNo){
		if(StringUtils.isEmpty(feeNo)){
			throw new AppException("feeNo 不可为空");
		}
		deleteByPrimaryKey(feeNo);
	}
	
	/**
	 * 通过费用项编号 获取 费用项配置
	 * @param feeNo
	 */
	public PubRepayFeeConf getByNo(String feeNo){
		return getByPrimaryKey(feeNo);
	}
	
	/**
	 * 获取 费用项配置 list
	 * @param param
	 * @return
	 */
	public List<PubRepayFeeConf> getFeeConfList(Map<String, Object> param){
		return queryForList(param);
	}
	
	/**
	 * 分页查询 费用项配置
	 * @param page
	 * @return
	 */
	public Page<PubRepayFeeConf> queryFeeConf(Page<PubRepayFeeConf> page){
		return queryForPage(page);
	}
	
	/**
	 * 分页查询没在产品中的费用项 且启用的
	 * 必须参数：prodNo
	 * @param page
	 * @return
	 */
	public List<PubRepayFeeConf> queryProdUnUsedFeeConf(Map<String,Object> param) {
		param.put("validFlag", CommonConstant.STAT_ENABLE);
		return pubRepayFeeConfMapper.queryProdUnUsedFeeConf(param);
	}

	/**
	 * 分页查询在产品中的费用项 且启用的
	 * 必须参数：prodNo
	 * @param page
	 * @return
	 */
	public List<PubRepayFeeConf> queryProdUsedFeeConf(Map<String,Object> param) {
		param.put("validFlag", CommonConstant.STAT_ENABLE);
		return pubRepayFeeConfMapper.queryProdUsedFeeConf(param);
	}

	
	/**
	 * 获取 启用的 费用项配置
	 * @param param
	 * @return
	 */
	public List<PubRepayFeeConf> getEnabledFeeConfList(Map<String, Object> param) {
		param.put("validFlag", CommonConstant.STAT_ENABLE);
		queryForList(param);
		return null;
	}
	
	
	
	
}