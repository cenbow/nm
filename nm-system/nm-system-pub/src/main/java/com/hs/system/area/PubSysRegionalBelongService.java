package com.hs.system.area;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.system.entity.SysRegionalBelong;
import com.hs.system.mapper.PubSysRegionalBelongMapper;
import com.hs.utils.StringUtils;

/**
 * SYS_地域归属表 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  PubSysRegionalBelongService{
	@Autowired
	private PubSysRegionalBelongMapper sysRegionalBelongMapper;
	
	/**
	 * 新增 SYS_地域归属表
	 * @param sysRegionalBelong 新增对象
	 */
	@Transactional
	public void insert(SysRegionalBelong sysRegionalBelong){
		sysRegionalBelongMapper.insert(sysRegionalBelong);
	}

	/**
	 * 通过主键修改 SYS_地域归属表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysRegionalBelongMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 SYS_地域归属表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysRegionalBelongMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 SYS_地域归属表 对象
	 * @param primaryKey 主键
	 * @return SYS_地域归属表对象
	 */
	public SysRegionalBelong getByPrimaryKey(String primaryKey){
		return sysRegionalBelongMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 SYS_地域归属表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<SysRegionalBelong> queryForList(Map<String, Object> param){
		return sysRegionalBelongMapper.queryForList(param);
	}
	
	/**
	 * 查询 SYS_地域归属表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<SysRegionalBelong> queryForPage(Page<SysRegionalBelong> page){
		sysRegionalBelongMapper.queryForList(page.getPageParams());
		return (Page<SysRegionalBelong>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询省名称
	 * @param page
	 * @return 省名
	 */
	public String getProvName(String provNo){
		if(StringUtils.isEmpty(provNo)){
			return "";
		}
		return sysRegionalBelongMapper.getProvName(provNo);
	}
	/**
	 * 查询省名称
	 * @param page
	 * @return 省名
	 */
	public String getCityName(String cityNo){
		if(StringUtils.isEmpty(cityNo)){
			return "";
		}
		return sysRegionalBelongMapper.getCityName(cityNo);
	}
	/**
	 * 查询省名称
	 * @param page
	 * @return 省名
	 */
	public String getCountName(String count){
		if(StringUtils.isEmpty(count)){
			return "";
		}
		return sysRegionalBelongMapper.getCountName(count);
	}

	public List<SysRegionalBelong> queryArea(String areano) {
		List<SysRegionalBelong> list = new ArrayList<>();
		if(StringUtils.isEmpty(areano)){
			 list = sysRegionalBelongMapper.queryProvs();
		}else{
			Map<String,Long> map =sysRegionalBelongMapper.checkType(areano);
			if(map == null){
				return list;
			}
			if(map.get("prov").longValue() > 0){
				list = sysRegionalBelongMapper.queryCity(areano);
			 }else if(map.get("city").intValue() > 0){
					list = sysRegionalBelongMapper.queryTowns(areano);
			 }
			}
		return list;
	}
	 
}