package com.hs.system.param.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.ComplexParaApi;
import com.hs.system.entity.SysComplexPara;
import com.hs.system.param.mapper.SysComplexParaMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * SYS_复杂参数表 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  SysComplexParaService implements ComplexParaApi{
	@Autowired
	private SysComplexParaMapper sysComplexParaMapper;
	
	/**
	 * 新增 SYS_复杂参数表
	 * @param sysComplexPara 新增对象
	 */
	@Transactional
	public void insert(SysComplexPara sysComplexPara){
		sysComplexParaMapper.insert(sysComplexPara);
	}

	/**
	 * 通过主键修改 SYS_复杂参数表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysComplexParaMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 SYS_复杂参数表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysComplexParaMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 SYS_复杂参数表 对象
	 * @param primaryKey 主键
	 * @return SYS_复杂参数表对象
	 */
	public SysComplexPara getByPrimaryKey(String primaryKey){
		return sysComplexParaMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 SYS_复杂参数表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<SysComplexPara> queryForList(Map<String, Object> param){
		return sysComplexParaMapper.queryForList(param);
	}
	
	/**
	 * 查询 SYS_复杂参数表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<SysComplexPara> queryForPage(Page<SysComplexPara> page){
		sysComplexParaMapper.queryForList(page.getPageParams());
		return (Page<SysComplexPara>)page.getPageParams().get(Page.KEY);
	}

	@Override
	public Page<SysComplexPara> querySysComplexParas(Page<SysComplexPara> page) {
		return  this.queryForPage(page);
	}

	@Override
	public SysComplexPara getSysComplexParaById(String id) {
		return  this.getByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void save(SysComplexPara complexPara) {
		if(StringUtils.isEmpty(complexPara.getId())){
			complexPara.setId(RandomUtil.getUUID());
			this.insert(complexPara);
		}else{
			this.updateByPrimaryKeySelective(BeanUtils.bean2map(complexPara));
		}
		
	}
	@Transactional
	@Override
	public void disabledSysComplexPara(String id) {
		 Map<String,Object> map = new HashMap<>();
		 map.put("id", id);
		 map.put("stat", CommonConstant.STAT_DISABLE);
		this.updateByPrimaryKeySelective(map);
	}
	@Transactional
	@Override
	public void enableSysComplexPara(String id) {
		 Map<String,Object> map = new HashMap<>();
		 map.put("id", id);
		 map.put("stat", CommonConstant.STAT_ENABLE);
		this.updateByPrimaryKeySelective(map);
		
	}

	@Override
	public SysComplexPara getSysComplexParaByTypeCode(String typeNo, String paraNo) {
	    Map map = new HashMap<>();
	    map.put("typeNo", typeNo);
	    map.put("paraNo", paraNo);
	    List<SysComplexPara> list=  this.queryForList(map);
	    if(list == null || list.size() == 0){
	    	throw new ServiceException("未获取到参数信息");
	    }
		return list.get(0);
	}
}