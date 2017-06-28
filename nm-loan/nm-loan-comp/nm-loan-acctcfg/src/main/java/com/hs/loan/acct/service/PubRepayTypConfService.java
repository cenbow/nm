package com.hs.loan.acct.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.loan.acct.entity.PubRepayTypConf;
import com.hs.loan.acct.mapper.PubRepayTypConfMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_还款类型配置信息 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubRepayTypConfService{
	@Autowired
	private PubRepayTypConfMapper pubRepayTypConfMapper;
	
	/**
	 * 新增 PUB_还款类型配置信息
	 * @param pubRepayTypConf 新增对象
	 */
	@Transactional
	public void insert(PubRepayTypConf pubRepayTypConf){
		pubRepayTypConfMapper.insert(pubRepayTypConf);
		//int a = 1/0;
	}

	/**
	 * 通过主键修改 PUB_还款类型配置信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubRepayTypConfMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_还款类型配置信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubRepayTypConfMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_还款类型配置信息 对象
	 * @param primaryKey 主键
	 * @return PUB_还款类型配置信息对象
	 */
	public PubRepayTypConf getByPrimaryKey(String primaryKey){
		return pubRepayTypConfMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_还款类型配置信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubRepayTypConf> queryForList(Map<String, Object> param){
		return pubRepayTypConfMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_还款类型配置信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubRepayTypConf> queryForPage(Page<PubRepayTypConf> page){
		pubRepayTypConfMapper.queryForList(page.getPageParams());
		return (Page<PubRepayTypConf>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 保存或者更新 还款类型配置信息
	 */
	@Transactional
	public void save(PubRepayTypConf pubRepayTypConf){
		String id = pubRepayTypConf.getConfNo();
		if(StringUtils.isEmpty(id)){ //保存
			List<PubRepayTypConf> lst = queryForList(BeanUtils.bean2mapExclude(pubRepayTypConf, "instDate,updtDate"));
			if(lst!=null && lst.size()>0){ return;}
			pubRepayTypConf.setConfNo(RandomUtil.getUUID());
			pubRepayTypConf.setInstDate(new Date());
			insert(pubRepayTypConf);
		}else{//更新 
			Map<String,Object> param = BeanUtils.bean2map(pubRepayTypConf);
			param.put("updtDate", new Date());
			updateByPrimaryKeySelective(param);
		}
	}
	
	/**
	 * 通过还款类型配置编号 删除 还款类型配置信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByNo(String confNo){
		if(StringUtils.isEmpty(confNo)){
			throw new AppException("confNo不可为空");
		}
		deleteByPrimaryKey(confNo);
	}
	
	/**
	 * 通过还款类型配置编号 获取 还款类型配置信息
	 * @param primaryKey 主键
	 * @return PUB_还款类型配置信息对象
	 */
	public PubRepayTypConf getByNo(String confNo){
		return getByPrimaryKey(confNo);
	}
	
	/**
	 * 查询还款类型配置信息List
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubRepayTypConf> getList(Map<String, Object> param){
		return queryForList(param);
	}
	
	/**
	 * 分页查询还款类型配置信息
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubRepayTypConf> queryRepayTypConf(Page<PubRepayTypConf> page){
		return queryForPage(page);
	}
	
	
}