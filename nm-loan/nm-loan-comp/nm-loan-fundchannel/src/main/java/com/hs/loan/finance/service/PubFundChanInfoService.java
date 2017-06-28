package com.hs.loan.finance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.loan.finance.bo.SaleContractBO;
import com.hs.loan.finance.entity.PubFundChanInfo;
import com.hs.loan.finance.mapper.PubFundChanInfoMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * PUB_资金渠道信息 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubFundChanInfoService{
	@Autowired
	private PubFundChanInfoMapper pubFundChanInfoMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	@Autowired
	private OperateCacheServiceApi operateCacheService;
	
	Logger log = Logger.getLogger(this.getClass());
	
	private static String CHANNO = "chanNo";
	
	/**
	 * 新增 PUB_资金渠道信息
	 * @param pubFundChanInfo 新增对象
	 */
	@Transactional
	public void insert(PubFundChanInfo pubFundChanInfo){
//		pubFundChanInfoMapper.insert(pubFundChanInfo);
		int num = pubFundChanInfoMapper.insert(pubFundChanInfo);
		if(num>0){
			Map<String, Object> map = BeanUtils.bean2map(pubFundChanInfo);
			operateCacheService.addCache(DB.PUB_FUND_CHAN_INFO, map);
		}
	}

	/**
	 * 通过主键修改 PUB_资金渠道信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
//		pubFundChanInfoMapper.updateByPrimaryKeySelective(map);
		String chanNo = map.get(CHANNO).toString();
		int num = pubFundChanInfoMapper.updateByPrimaryKeySelective(map);
		if(num>0){
			 Map<String,Object> del = new HashMap<String,Object>();
			 del.put(CHANNO, chanNo);
			 PubFundChanInfo m = pubFundChanInfoMapper.getByPrimaryKey(chanNo);
			 Map<String,Object> news = BeanUtils.bean2map(m);
			 operateCacheService.modifyCacheByCondition(DB.PUB_FUND_CHAN_INFO, del,news);
		}
	}

	/**
	 * 通过主键删除 PUB_资金渠道信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
//		pubFundChanInfoMapper.deleteByPrimaryKey(primaryKey);
		int num = pubFundChanInfoMapper.deleteByPrimaryKey(primaryKey);
		if(num>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(CHANNO, primaryKey);
			operateCacheService.delCache(DB.PUB_FUND_CHAN_INFO, map);
		}
	}

	/**
	 * 通过主键取得 PUB_资金渠道信息 对象
	 * @param primaryKey 主键
	 * @return PUB_资金渠道信息对象
	 */
	public PubFundChanInfo getByPrimaryKey(String primaryKey){
//		return pubFundChanInfoMapper.getByPrimaryKey(primaryKey);
		PubFundChanInfo pub = null;
		boolean f = false;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(CHANNO, primaryKey);
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.PUB_FUND_CHAN_INFO, map);
			List<PubFundChanInfo> list = BeanUtil.ListMap2JavaBean(lst, PubFundChanInfo.class);
			if(!list.isEmpty()) {
				pub = list.get(0);
			}
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(pub == null || f){
			pub = pubFundChanInfoMapper.getByPrimaryKey(primaryKey);
		}
		return pub;
	}

	/**
	 * 查询 PUB_资金渠道信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubFundChanInfo> queryForList(Map<String, Object> param){
//		return pubFundChanInfoMapper.queryForList(param);
		List<PubFundChanInfo> list = new ArrayList<PubFundChanInfo>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.PUB_FUND_CHAN_INFO, param);
			list = BeanUtil.ListMap2JavaBean(lst, PubFundChanInfo.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.isEmpty() || f){
			list = pubFundChanInfoMapper.queryForList(param);
		}
		return list;
	}
	
	/**
	 * 查询 PUB_资金渠道信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubFundChanInfo> queryForPage(Page<PubFundChanInfo> page){
		pubFundChanInfoMapper.queryForList(page.getPageParams());
		return (Page<PubFundChanInfo>)page.getPageParams().get(Page.KEY);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 保存或者更新 资金渠道信息
	 * @param pubFundChanInfo
	 */
	@Transactional
	public void save(PubFundChanInfo pubFundChanInfo){
		PubFundChanInfo pfci = getByNo(pubFundChanInfo.getChanNo());
		if(pfci == null){//保存
			pubFundChanInfo.setInstDate(new Date());
			insert(pubFundChanInfo);
		}else{//更新
			Map<String,Object> param = BeanUtils.bean2map(pubFundChanInfo);
			param.put("updtDate", new Date());
			updateByPrimaryKeySelective(param);
		}
	}
	
	/**
	 * 通过渠道编号 删除 资金渠道信息
	 * @param chanNo
	 */
	@Transactional
	public void deleteByNo(String chanNo){
		deleteByPrimaryKey(chanNo);
	}
	
	/**
	 * 通过渠道编号 获取 资金渠道信息
	 * @param chanNo
	 */
	public PubFundChanInfo getByNo(String chanNo){
		return getByPrimaryKey(chanNo);
	}
	
	/**
	 * 获取 资金渠道信息 列表
	 * @param param
	 * @return
	 */
	public List<PubFundChanInfo> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 分页查询 资金渠道信息 
	 * @param page
	 * @return
	 */
	public Page<PubFundChanInfo> queryPubFundChanInfo(Page<PubFundChanInfo> page){
		return queryForPage(page);
	}
	
	/**
	 * 返回没有在chanNos里的渠道信息
	 * 
	 * @param chanNos
	 * @return
	 */
	public List<PubFundChanInfo> queryNotUseFundChanInfo(String chanNos){
		return pubFundChanInfoMapper.queryNotUseFundChanInfo(chanNos);
	}

	/**
	 * 返回没有在chanNos里的渠道信息
	 * 
	 * @param chanNos
	 * @return
	 */
	public Page<SaleContractBO> querySaleContractList(Page<SaleContractBO> page){
		pubFundChanInfoMapper.querySaleContractList(page.getPageParams());
		return (Page<SaleContractBO>)page.getPageParams().get(Page.KEY);
	}
	
}