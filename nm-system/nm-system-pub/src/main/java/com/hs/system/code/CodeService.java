package com.hs.system.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.commons.bo.SimpleCode;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.service.ISimpleCodeService;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.entity.SysGroup;
import com.hs.system.mapper.CodMapper;
import com.hs.system.util.BeanUtil;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;

/**
 * 编码信息 公用查询类
 * @author jqiu
 * @create 2015-09-24
 */
@Service
@Transactional(readOnly=true)
public class  CodeService implements ISimpleCodeService{
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private CodMapper codMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	/**
	 * 查询列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysCodInfo> queryForList(Map<String, Object> param){
		return codMapper.queryForList(param);
	}
	
	/** 
	 * <li>@Description:查询code
	 * <li>@param param
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2017年2月6日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public List<SysCodInfo> querySysCodInfo(Map<String, Object> param){
		List<SysCodInfo> list = new ArrayList<SysCodInfo>();
		boolean f = false;
		try {
			List<Map<String, Object>> lzt = redisCacheApi.queryCacheByCondition(DB.SYS_COD_INFO, param);
			list = BeanUtil.ListMap2JavaBean(lzt, SysCodInfo.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.size()==0 || f){
			list = this.queryForList(param);
		}
		return list;
	}
	
	public List<SysGroup> querySysGroup(Map<String, Object> param){
		String typeCode = String.valueOf(param.get("typeCode"));
		return codMapper.queryGroupListByType(typeCode); 
	}

	@Override
	public List<SimpleCode> queryAllCodeItem() {
		Map<String,Object> param = new HashMap<String,Object>();
		List<SysCodInfo> list = this.querySysCodInfo(param);
		List<SimpleCode> codeList = new ArrayList<SimpleCode>();
		SimpleCode bean;
		for (SysCodInfo sysCodInfo : list) {
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

	@Override
	public List<SimpleCode> queryForListByType(String typeCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("codTyp", typeCode);
		
		List<SysCodInfo> list = this.querySysCodInfo(param);
		List<SimpleCode> codeList = info2SimpleCode(list);
		return codeList;
	}
	
	/**
	 * 通过编号获取编码对象
	 * @param code	编号
	 * @return SimpleCode
	 */
	@Override
	public SimpleCode getCodeInfoByCode(String code){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("codVal", code);
		
		List<SysCodInfo> list = this.querySysCodInfo(param);
		List<SimpleCode> codeList = info2SimpleCode(list);
		return codeList.size() > 0 ? codeList.get(0) : null;
	}
	
	/**
	 * 通过名称获取编码对象
	 * @param type 编码类型
	 * @param name 名称
	 * @return SimpleCode
	 */
	@Override
	public SimpleCode getCodeInfoByName(String type,String name){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("codTyp", type); 
		param.put("codName", name);
		List<SysCodInfo> list = this.querySysCodInfo(param);
		List<SimpleCode> codeList = info2SimpleCode(list);
		return codeList.size() > 0 ? codeList.get(0) : null;
	}
	
	
	/**
	 * bean转换成simpleCode
	 * @param sysCodInfo
	 * @return
	 */
	private List<SimpleCode> info2SimpleCode(List<SysCodInfo> infoList){
		List<SimpleCode> codeList = new ArrayList<SimpleCode>();
		if(infoList == null || infoList.size()==0){
			return codeList;
		}
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
	
	@Override
//	@ReadThroughSingleCache(namespace = "system.code", expiration = 3600)    @ParameterValueKeyProvider 
	public Map<String,List<SimpleCode>> queryGroupListByType(String typeCode) {
		Map<String,List<SimpleCode>> groupMap = new HashMap<String,List<SimpleCode>>();
		
		List<SimpleCode> list = this.queryForListByType(typeCode);
		groupMap.put(CommonConstant.GROUP_DEFAULT, list);
		
		if(list != null && !list.isEmpty()){
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("typeCode", typeCode);
			List<SysGroup> groupList = this.querySysGroup(param);
			for (SysGroup sysGroup : groupList) {
				groupMap.put(sysGroup.getGroupCod(), info2SimpleCode(sysGroup.getInfoList()));
			}
		}
		
		return groupMap;
	}
	
}