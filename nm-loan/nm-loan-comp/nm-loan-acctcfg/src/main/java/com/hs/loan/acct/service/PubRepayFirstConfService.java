package com.hs.loan.acct.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.entity.PubRepayFirstConf;
import com.hs.loan.acct.entity.PubRepayKindConf;
import com.hs.loan.acct.mapper.PubRepayFirstConfMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_首次还款日规则 业务处理
 * @author autocreate
 * @create 2015-10-29
 */
@Service
@Transactional(readOnly=true)
public class  PubRepayFirstConfService{
	@Autowired
	private PubRepayFirstConfMapper pubRepayFirstConfMapper;
	
	/**
	 * 新增 PUB_首次还款日规则
	 * @param pubRepayFirstConf 新增对象
	 */
	@Transactional
	public void insert(PubRepayFirstConf pubRepayFirstConf){
		pubRepayFirstConfMapper.insert(pubRepayFirstConf);
	}

	/**
	 * 通过主键修改 PUB_首次还款日规则
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubRepayFirstConfMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_首次还款日规则
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubRepayFirstConfMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PUB_首次还款日规则 对象
	 * @param primaryKey 主键
	 * @return PUB_首次还款日规则对象
	 */
	public PubRepayFirstConf getByPrimaryKey(String primaryKey){
		return pubRepayFirstConfMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_首次还款日规则 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubRepayFirstConf> queryForList(Map<String, Object> param){
		return pubRepayFirstConfMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_首次还款日规则 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubRepayFirstConf> queryForPage(Page<PubRepayFirstConf> page){
		pubRepayFirstConfMapper.queryForList(page.getPageParams());
		return (Page<PubRepayFirstConf>)page.getPageParams().get(Page.KEY);
	}

	 
	
	/**
	 * 保存或更新 还款日规则
	 * @param pubRepayFirstConf
	 */
	@Transactional
	public void save(PubRepayFirstConf pubRepayFirstConf){
		String id = pubRepayFirstConf.getFirstNo();
		if(StringUtils.isEmpty(id)){//保存
			pubRepayFirstConf.setFirstNo(RandomUtil.getUUID());
			pubRepayFirstConf.setInstDate(new Date());
			insert(pubRepayFirstConf);
		}else{//更新
			Map<String,Object> param = BeanUtils.bean2map(pubRepayFirstConf);
			param.put("updtDate", new Date());
			updateByPrimaryKeySelective(param);
		}
	}
	/**
	 * 删除还款日规则
	 * @param pubRepayFirstConf
	 */
	@Transactional
	public void deleteNo(String repayNo) {
		this.deleteByPrimaryKey(repayNo);
	}
	/**
	 * 计算还款日规则
	 * @param pubRepayFirstConf
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String calFirstDate(String prodNo,String applyDate) {
		Map<String,String> map = new HashMap<>();
		map.put("I_PROD_NO", prodNo);
		map.put("I_DAY", applyDate);
		pubRepayFirstConfMapper.getFirstRepayDate(map);
		if(StringUtils.isEmpty(map.get("O_RET"))){
			throw new ServiceException("获取还款日配置信息失败,计算返回为空");
		}
		if(!"0".equals(map.get("O_RET").split("\\|")[0])){
			throw new ServiceException("计算还款日失败："+map.get("O_STR"));
		}
		return map.get("O_RET").split("\\|")[1];
	}
}