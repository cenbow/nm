package com.hs.loan.operation.service;

import com.hs.base.entity.Page;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.loan.operation.entity.PubLoanContractFile;
import com.hs.loan.operation.mapper.PubLoanContractFileMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;

import com.nm.cache.busi.service.OperateCacheServiceApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PUB_合同模版 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubLoanContractFileService{
	@Autowired
	private PubLoanContractFileMapper pubLoanContractFileMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	@Autowired
	private OperateCacheServiceApi operateCacheService;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public List<String> getSendLoanApply(java.util.Map map){
		return pubLoanContractFileMapper.getSendLoanApply(map);
	}
	public List<String> getSendLoanResult(java.util.Map map){
		return pubLoanContractFileMapper.getSendLoanResult(map);
	}
	public List<String> getSendSuccessRepayment(java.util.Map map){
		return pubLoanContractFileMapper.getSendSuccessRepayment(map);
	}
	@Transactional
	public int insertAppEntr(java.util.Map map){
		return pubLoanContractFileMapper.insertAppEntr(map);
	};
	public Page<HashMap<String,Object>> getAppEntr(Page<HashMap<String,Object>> page){
		pubLoanContractFileMapper.getAppEntr(page.getPageParams());
		return (Page<HashMap<String,Object>>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 新增 PUB_合同模版
	 * @param pubLoanContractFile 新增对象
	 */
	@Transactional
	public void insert(PubLoanContractFile pubLoanContractFile){
//		pubLoanContractFileMapper.insert(pubLoanContractFile);
		int num = pubLoanContractFileMapper.insert(pubLoanContractFile);
		if(num>0){
			Map<String, Object> map = BeanUtils.bean2map(pubLoanContractFile);
			operateCacheService.addCache(DB.PUB_LOAN_CONTRACT_FILE, map);
		}
	}

	/**
	 * 通过主键修改 PUB_合同模版
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
//		pubLoanContractFileMapper.updateByPrimaryKeySelective(map);
		String id = map.get("fileId").toString();
		int num = pubLoanContractFileMapper.updateByPrimaryKeySelective(map);
		if(num>0){
			 Map<String,Object> del = new HashMap<String,Object>();
			 del.put("fileId", id);
			 PubLoanContractFile m = pubLoanContractFileMapper.getByPrimaryKey(id);
			 Map<String,Object> news = BeanUtils.bean2map(m);
			 operateCacheService.modifyCacheByCondition(DB.PUB_LOAN_CONTRACT_FILE, del,news);
		}
	}

	/**
	 * 通过主键删除 PUB_合同模版
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
//		pubLoanContractFileMapper.deleteByPrimaryKey(primaryKey);
		int num = pubLoanContractFileMapper.deleteByPrimaryKey(primaryKey);
		if(num>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fileId", primaryKey);
			operateCacheService.delCache(DB.PUB_LOAN_CONTRACT_FILE, map);
		}
	}

	/**
	 * 通过主键取得 PUB_合同模版 对象
	 * @param primaryKey 主键
	 * @return PUB_合同模版对象
	 */
	public PubLoanContractFile getByPrimaryKey(String primaryKey){
//		return pubLoanContractFileMapper.getByPrimaryKey(primaryKey);
		PubLoanContractFile file = null;
		boolean f = false;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fileId", primaryKey);
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.PUB_LOAN_CONTRACT_FILE, map);
			List<PubLoanContractFile> list = BeanUtil.ListMap2JavaBean(lst, PubLoanContractFile.class);
			if(!list.isEmpty()) {
				file = list.get(0);
			}
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(file == null || f){
			file = pubLoanContractFileMapper.getByPrimaryKey(primaryKey);
		}
		return file;
	}

	/**
	 * 查询 PUB_合同模版 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubLoanContractFile> queryForList(Map<String, Object> param){
//		return pubLoanContractFileMapper.queryForList(param);
		List<PubLoanContractFile> list = new ArrayList<PubLoanContractFile>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.PUB_LOAN_CONTRACT_FILE, param);
			list = BeanUtil.ListMap2JavaBean(lst, PubLoanContractFile.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.isEmpty() || f){
			list = pubLoanContractFileMapper.queryForList(param);
		}
		return list;
	}
	
	/**
	 * 查询 PUB_合同模版 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubLoanContractFile> queryForPage(Page<PubLoanContractFile> page){
		pubLoanContractFileMapper.queryForList(page.getPageParams());
		return (Page<PubLoanContractFile>)page.getPageParams().get(Page.KEY);
	}
}