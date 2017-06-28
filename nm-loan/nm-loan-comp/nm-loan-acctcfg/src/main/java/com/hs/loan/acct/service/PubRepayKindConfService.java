package com.hs.loan.acct.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.loan.acct.entity.PubRepayKindConf;
import com.hs.loan.acct.mapper.PubRepayKindConfMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_还款方式配置表 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubRepayKindConfService{
	@Autowired
	private PubRepayKindConfMapper pubRepayKindConfMapper;
	
	/**
	 * 新增 PUB_还款方式配置表
	 * @param pubRepayKindConf 新增对象
	 */
	@Transactional
	public void insert(PubRepayKindConf pubRepayKindConf){
		pubRepayKindConfMapper.insert(pubRepayKindConf);
	}

	/**
	 * 通过主键修改 PUB_还款方式配置表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubRepayKindConfMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_还款方式配置表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubRepayKindConfMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_还款方式配置表 对象
	 * @param primaryKey 主键
	 * @return PUB_还款方式配置表对象
	 */
	public PubRepayKindConf getByPrimaryKey(String primaryKey){
		return pubRepayKindConfMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_还款方式配置表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubRepayKindConf> queryForList(Map<String, Object> param){
		return pubRepayKindConfMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_还款方式配置表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubRepayKindConf> queryForPage(Page<PubRepayKindConf> page){
		pubRepayKindConfMapper.queryForList(page.getPageParams());
		return (Page<PubRepayKindConf>)page.getPageParams().get(Page.KEY);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 保存或更新 还款方式
	 * @param pubRepayKindConf
	 */
	@Transactional
	public void save(PubRepayKindConf pubRepayKindConf){
		String id = pubRepayKindConf.getRepayNo();
		if(StringUtils.isEmpty(id)){
			throw new AppException("还款方式编号不可为空");
		}
		PubRepayKindConf pk = getByPrimaryKey(id);
		if(pk==null){
			pubRepayKindConf.setInstDate(new Date());
			insert(pubRepayKindConf);
			return;
		}
		Map<String,Object> param = BeanUtils.bean2map(pubRepayKindConf);
		param.remove("instDate");
		param.put("updtDate", new Date());
		updateByPrimaryKeySelective(param);
	}
	
	/**
	 * 通过还款方式编号 删除 还款方式
	 * @param repayNo
	 */
	@Transactional
	public void deleteByNo(String repayNo){
		if(StringUtils.isEmpty(repayNo)){
			throw new AppException("repayNo 不可为空");
		}
		deleteByPrimaryKey(repayNo);
	}
	
	/**
	 * 通过还款方式编号 获取 还款方式
	 * @param repayNo
	 */
	public PubRepayKindConf getByNo(String repayNo){
		return getByPrimaryKey(repayNo);
	}
	
	/**
	 * 获取 还款方式list
	 * @param param
	 * @return
	 */
	public List<PubRepayKindConf> getList(Map<String, Object> param){
		return queryForList(param);
	}
	
	
	/**
	 * 分页查询 还款方式
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubRepayKindConf> queryRepayConf(Page<PubRepayKindConf> page){
		return queryForPage(page);
	}
	
	
}