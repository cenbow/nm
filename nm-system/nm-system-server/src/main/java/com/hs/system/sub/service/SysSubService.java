package com.hs.system.sub.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.system.api.SysSubApi;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysSub;
import com.hs.system.sub.mapper.SysSubMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 子系统信息 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysSubService implements SysSubApi{
	@Autowired
	private SysSubMapper sysSubMapper;
	
	/**
	 * 新增 子系统信息
	 * @param vo
	 * @return
	 */
	
	@Transactional 
	@Override
	public void insert(SysSub sysSub) {
		sysSubMapper.insert(sysSub);
	}

	/**
	 * 通过主键修改 子系统信息
	 * @param map
	 * @return
	 */
	@Transactional 
	@Override
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		sysSubMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 子系统信息
	 * @param primaryKey
	 * @return
	 */
	@Transactional 
	@Override
	public void deleteByPrimaryKey(String primaryKey) {
		sysSubMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 子系统信息 对象
	 * @param id
	 * @return
	 */
	@Override
	public SysSub getByPrimaryKey(String primaryKey) {
		return sysSubMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 子系统信息 列表
	 * @param param
	 * @return List<T>
	 */
	@Override
	public List<SysSub> queryForList(Map<String, Object> param) {
		return sysSubMapper.queryForList(param);
	}
	
	/**
	 * 查询 子系统信息 分页列表
	 * @param param
	 * @return List<T>
	 */
	@Override
	public Page<SysSub> queryForPage(Page<SysSub> page) {
		sysSubMapper.queryForList(page.getPageParams());
		return (Page<SysSub>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 新增、修改 机构信息
	 * @param vo
	 * @return
	 */
	@Transactional 
	@Override
	public SysSub save(SysSub sysSub) {
		int cn = 0;
		 if(sysSub != null){
			if(StringUtils.isEmpty(sysSub.getId())){
				sysSub.setId(RandomUtil.getUUID());
				sysSub.setInstDate(new Date());
				cn = sysSubMapper.insert(sysSub);
			 }else{
				Map<String,Object> map = BeanUtils.bean2map(sysSub);
				map.put("updtDate", new Date());
				cn = sysSubMapper.updateByPrimaryKeySelective(map);
			 }
		 }
		 if(cn == 0){
			 throw new ServiceException("保存机构信息失败");
		 }
		return getByPrimaryKey(sysSub.getId());
	}
}