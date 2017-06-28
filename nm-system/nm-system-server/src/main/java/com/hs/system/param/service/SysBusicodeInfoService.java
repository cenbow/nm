package com.hs.system.param.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.system.param.mapper.SysBusicodeInfoMapper;
import com.hs.system.api.SysBusicodeInfoApi;
import com.hs.system.entity.SysBusicodeInfo;
import com.hs.system.entity.SysMenu;
import com.hs.base.entity.Page;

/**
 * 业务参数 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysBusicodeInfoService implements SysBusicodeInfoApi{
	@Autowired
	private SysBusicodeInfoMapper sysBusicodeInfoMapper;
	
	/**
	 * 新增 业务参数
	 * @param vo
	 * @return
	 */
	@Transactional
	public void insert(SysBusicodeInfo sysBusicodeInfo){
		sysBusicodeInfoMapper.insert(sysBusicodeInfo);
	}

	/**
	 * 通过主键修改 业务参数
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysBusicodeInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 业务参数
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysBusicodeInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 业务参数 对象
	 * @param id
	 * @return
	 */
	public SysBusicodeInfo getByPrimaryKey(String primaryKey){
		return sysBusicodeInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 业务参数 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysBusicodeInfo> queryForList(Map<String, Object> param){
		return sysBusicodeInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 业务参数 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysBusicodeInfo> queryForPage(Page<SysBusicodeInfo> page){
		sysBusicodeInfoMapper.queryForList(page.getPageParams());
		return (Page<SysBusicodeInfo>)page.getPageParams().get(Page.KEY);
	}
}