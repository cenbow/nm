package com.hs.system.param.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.system.param.mapper.SysGroupMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.system.api.SysGroupApi;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.entity.SysGroup;
import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;

/**
 * 编码组 业务处理
 * @author autocreate
 * @create 2015-09-26
 */
@Service
@Transactional(readOnly=true)
public class  SysGroupService implements SysGroupApi{
	@Autowired
	private SysGroupMapper sysGroupMapper;
	
	/**
	 * 新增 编码组
	 * @param vo
	 * @return
	 */
	@Transactional
	public void insert(SysGroup sysGroup){
		sysGroupMapper.insert(sysGroup);
	}

	/**
	 * 通过主键修改 编码组
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysGroupMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 编码组
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysGroupMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 编码组 对象
	 * @param id
	 * @return
	 */
	public SysGroup getByPrimaryKey(String primaryKey){
		return sysGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 编码组 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysGroup> queryForList(Map<String, Object> param){
		return sysGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 编码组 列表 
	 * @param param {typId}
	 * 
	 * @return List<T>
	 */
	public List<SysGroup> queryByType(Map<String,Object> param){
		List<SysGroup> list = sysGroupMapper.queryForList(param);
		
		SysGroup defGroup = new SysGroup();
		defGroup.setRemark("默认分组");
		defGroup.setGroupCod(CommonConstant.GROUP_DEFAULT);
		
		List<SysGroup> rsLst = new ArrayList<SysGroup>();
		rsLst.add(defGroup);
		rsLst.addAll(list);
		return rsLst;
	}
	
	
	/**
	 * 保存或更新码表组信息，包含的syscodeinfo将一同保存
	 */
	@Transactional
	public void saveOrUpdateCodGrp(SysGroup sysGroup){
		String grpId = sysGroup.getId();
		if(grpId==null || grpId.trim().length()==0){//保存
			sysGroup.setId(RandomUtil.getUUID());
			sysGroup.setInstDate(new Date());
			sysGroup.setUpdtDate(null);
			//保存码表组的基本信息
			insert(sysGroup);
		}else{//更新
			Map<String,Object> params = BeanUtils.bean2mapInclude(sysGroup,"id,groupCod,remark");
			params.put("updtDate", new Date());
			params.put("instDate", sysGroup.getInstDate());
			//更新组基本信息
			updateByPrimaryKeySelective(params);
		}
		//删除组原有关联的codeinfo
		sysGroupMapper.deleteSysCodGrpByGrpId(sysGroup.getId());
		if(sysGroup.getInfoList()!=null){
			//保存组与codeinfo新的关联关系
			for(SysCodInfo sysCodInfo : sysGroup.getInfoList()){
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("id", RandomUtil.getUUID());
				param.put("codId",sysCodInfo.getId());
				param.put("grpId", sysGroup.getId());
				sysGroupMapper.saveCodeGrpRelation(param);
			}
		}
		
	}
	
	

	/**
	 * 查询码表组的基本信息，不包含syscodeinfo
	 */
	@Override
	public SysGroup querySysGrpBaseInfo(String groupId) {
		SysGroup sysGrp = sysGroupMapper.querySysGrpBaseInfo(groupId);
		return sysGrp;
	}

	/**
	 * 查询码表组中的codeinfo列表,
	 * 结果列表中包含checked的和未checked的codeinfo，
	 * checked表示这条codeinfo是属于这个码表组中的。
	 * 
	 * map的key有id,codeVal,codeName,codTyp,checked
	 * 
	 * @param groupId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryGrpCodeInfoLst(String groupId) {
		List<Map<String,Object>> lst = sysGroupMapper.queryGrpCodeInfoLst(groupId);
		return lst;
	}

	/**
	 * 删除码表组
	 */
	@Transactional
	@Override
	public void deleteCodGrp(String groupId) {
		// TODO Auto-generated method stub
		//先删除grp
		deleteByPrimaryKey(groupId);
		//再删除关联关系
		sysGroupMapper.deleteSysCodGrpByGrpId(groupId);
		
	}
	
	
	
}