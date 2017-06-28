package com.hs.system.param.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.system.api.SysCodeTypeApi;
import com.hs.system.entity.SysCodTyp;
import com.hs.system.param.mapper.SysCodTypMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 编码类型 业务处理
 * @author autocreate
 * @create 2015-09-24
 */
@Service
@Transactional(readOnly=true)
public class  SysCodTypService implements SysCodeTypeApi{
	@Autowired
	private SysCodTypMapper sysCodTypMapper;
	
	/**
	 * 新增 编码类型
	 * @param vo
	 * @return
	 */
	@Transactional
	public void insert(SysCodTyp sysCodTyp){
		Date date = new Date();
		sysCodTyp.setId(RandomUtil.getUUID());
		sysCodTyp.setInstDate(date);
		sysCodTyp.setUpdtDate(date);
		sysCodTypMapper.insert(sysCodTyp);
	}

	/**
	 * 通过主键修改 编码类型
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysCodTypMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 编码类型
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysCodTypMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 编码类型 对象
	 * @param id
	 * @return
	 */
	public SysCodTyp getByPrimaryKey(String primaryKey){
		return sysCodTypMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 编码类型 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysCodTyp> queryForList(Map<String, Object> param){
		return sysCodTypMapper.queryForList(param);
	}
	
	/**
	 * 查询 编码类型 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysCodTyp> queryForPage(Page<SysCodTyp> page){
		List<SysCodTyp> list = sysCodTypMapper.queryForList(page.getPageParams());
		page.setList(list);
		return (Page<SysCodTyp>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 保存  编码类型
	 * @param vo
	 * @return
	 */
	@Transactional
	public SysCodTyp save(SysCodTyp sysCodTyp){
		int cn = 0;
		if(null != sysCodTyp){			
			Date date = new Date();
			//ID为空时，为新增，反之为修改
			if(StringUtils.isEmpty(sysCodTyp.getId())){
				sysCodTyp.setId(RandomUtil.getUUID());
				sysCodTyp.setInstDate(date);
				sysCodTyp.setUpdtDate(date);
				cn = sysCodTypMapper.insert(sysCodTyp);
			}else{
				Map<String, Object> bean2map = BeanUtils.bean2map(sysCodTyp);
				bean2map.put("updtDate", date);
				cn = sysCodTypMapper.updateByPrimaryKeySelective(bean2map);
			}
		}
		if(cn == 0){
			throw new ServiceException("保存码表类型信息失败");
		}
		return sysCodTyp;
	}
}