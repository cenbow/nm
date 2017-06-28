package com.hs.system.param.service;

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
import com.hs.base.exception.ServiceException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.commons.bo.SimpleCode;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.SysCodeInfoApi;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.entity.SysMenu;
import com.hs.system.param.mapper.SysCodInfoMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 编码信息 业务处理
 * @author autocreate
 * @create 2015-09-24
 */
@Service
@Transactional(readOnly=true)
public class  SysCodeInfoService implements SysCodeInfoApi{
	@Autowired
	private SysCodInfoMapper sysCodInfoMapper;
	
	@Autowired
	private OperateCacheServiceApi operateCacheServiceApi;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 新增 编码信息
	 * @param vo
	 * @return
	 */
	@Transactional
	public int insert(SysCodInfo sysCodInfo){
		Date date = new Date();
		sysCodInfo.setId(RandomUtil.getUUID());
		sysCodInfo.setInstDate(date);
		sysCodInfo.setUpdtDate(date);
		int num = sysCodInfoMapper.insert(sysCodInfo);
		if(num>0){
			Map<String,Object> map = BeanUtils.bean2map(sysCodInfo);
			operateCacheServiceApi.addCache(DB.SYS_COD_INFO, map);
		}
		return num;
	}

	/**
	 * 通过主键修改 编码信息
	 * @param map
	 * @return
	 */
	@Transactional
	public int updateByPrimaryKeySelective(Map<String, Object> map){
		
		Map<String,Object> del = new HashMap<String, Object>();
		String id = map.get(DB.ID).toString();
		del.put(DB.ID, id);
		int n = sysCodInfoMapper.updateByPrimaryKeySelective(map);
		if(n>0){
			SysCodInfo news = sysCodInfoMapper.getByPrimaryKey(id);
			Map<String,Object> newsm = BeanUtils.bean2map(news);
			operateCacheServiceApi.modifyCacheByCondition(DB.SYS_COD_INFO,del, newsm);
		}
		return n;
	}

	/**
	 * 通过主键删除 编码信息
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		
		int num = sysCodInfoMapper.deleteByPrimaryKey(primaryKey);
		if(num>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", primaryKey);
			operateCacheServiceApi.delCache(DB.SYS_COD_INFO, map);
		}
		
		
	}

	/**
	 * 通过主键取得 编码信息 对象
	 * @param id
	 * @return
	 */
	public SysCodInfo getByPrimaryKey(String primaryKey){
		SysCodInfo info = null;
		boolean f = false;
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("id", primaryKey);
			if(primaryKey==null || "".equals(primaryKey)){
				throw new ServiceException("primaryKey is null!");
			}
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_COD_INFO, param);
			List<SysCodInfo> list = BeanUtil.ListMap2JavaBean(lst, SysCodInfo.class);
			if(!list.isEmpty()){
				info = list.get(0);
			}
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(info==null || f){
			info = sysCodInfoMapper.getByPrimaryKey(primaryKey);
		}
		return info;
		
		
	}

	/**
	 * 查询 编码信息 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysCodInfo> queryForList(Map<String, Object> param){
		List<SysCodInfo> list = new ArrayList<SysCodInfo>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_COD_INFO, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysCodInfo.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.isEmpty() || f){
			list = sysCodInfoMapper.queryForList(param);	
		}
		return list;
	}
	
	/**
	 * 查询 编码类型 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysCodInfo> queryForPage(Page<SysCodInfo> page){
		List<SysCodInfo> list = sysCodInfoMapper.queryForList(page.getPageParams());
		page.setList(list);
		return (Page<SysCodInfo>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 保存 编码信息
	 * @param vo
	 * @return
	 */
	@Transactional 
	public SysCodInfo save(SysCodInfo sysCodInfo){
		int cn = 0;
		if(null != sysCodInfo){	
			Date date = new Date();
			//如果ID为空，则新增，反之则根据主键修改
			if(StringUtils.isEmpty(sysCodInfo.getId())){
				sysCodInfo.setId(RandomUtil.getUUID());
				sysCodInfo.setUpdtDate(date);
				sysCodInfo.setInstDate(date);
				cn = insert(sysCodInfo);
			}else{
				Map<String, Object> bean2map = BeanUtils.bean2map(sysCodInfo);
				bean2map.put("updtDate", date);
				cn = updateByPrimaryKeySelective(bean2map);
			}
		}
		if(cn == 0){
			throw new ServiceException("保存码表信息失败");
		}
		return sysCodInfo;
	}
	

	
	/**
	 * bean转换成simpleCode
	 * @param sysCodInfo
	 * @return
	 */
	private List<SimpleCode> info2SimpleCode(List<SysCodInfo> infoList){
		List<SimpleCode> codeList = new ArrayList<SimpleCode>();
		SimpleCode bean;
		for (SysCodInfo sysCodInfo : infoList) {
			bean = new SimpleCode();
			bean.setCode(sysCodInfo.getCodVal());
			bean.setCodeName(sysCodInfo.getCodName());
			bean.setType(sysCodInfo.getCodTyp());
			bean.setTypeName(sysCodInfo.getCodTypName());
			bean.setIsEnable(!CommonConstant.STAT_DISABLE.equals(sysCodInfo.getStat()));
			codeList.add(bean);
		}
		return codeList;
	}
}