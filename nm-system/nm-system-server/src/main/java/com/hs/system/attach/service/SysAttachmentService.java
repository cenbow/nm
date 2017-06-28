package com.hs.system.attach.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hs.commons.attach.Attachment;
import com.hs.commons.attach.AttachmentApi;
import com.hs.commons.attach.OssUtil;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.attach.mapper.SysAttachmentMapper;
import com.hs.system.entity.SysAttachment;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * SYS_附件表 业务处理
 * @author autocreate
 * @create 2015-11-10
 */
@Service
@Transactional(readOnly=true)
public class  SysAttachmentService implements AttachmentApi{
	@Autowired
	private SysAttachmentMapper sysAttachmentMapper;
	
	/**
	 * 新增 SYS_附件表
	 * @param attachment 新增对象
	 */
	@Override
	@Transactional
	public Attachment save(Attachment attachment){
		if(StringUtils.isEmpty(attachment.getId())){
			attachment.setId(RandomUtil.getUUID());
			attachment.setInstDate(new Date());
		}else{
			attachment.setInstDate(new Date());
			attachment.setUpdtDate(new Date());
		}
		
		attachment.setStat(CommonConstant.STAT_ENABLE);
		sysAttachmentMapper.insert(BeanUtils.copyPropertiesNotForceByClz(SysAttachment.class, attachment));
		attachment.setPresNetworkAddress(OssUtil.generatePresignedUrl(attachment));
		
		return attachment;
	}
	
	

	@Override
	public Attachment getById(String id) {
		 SysAttachment att = sysAttachmentMapper.getByPrimaryKey(id);
		 if(att==null)return null;
		 
		 Attachment retBean = BeanUtils.copyPropertiesNotForceByClz(Attachment.class, att);
		 retBean.setNetworkAddress(OssUtil.generatePresignedUrl(retBean));
		 return retBean;
	}

	@Override
	@Transactional
	public void removeById(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("stat", CommonConstant.STAT_DISABLE);
		sysAttachmentMapper.updateByPrimaryKeySelective(map);
	}
	
	@Transactional
	public void deleteById(String id) {
		sysAttachmentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 通过主键修改 SYS_附件表
	 * @param map 修改参数Map
	 */
	@Transactional
	private void updateByPrimaryKeySelective(Map<String, Object> map){
		sysAttachmentMapper.updateByPrimaryKeySelective(map);
	}

	

	/**
	 * 查询 SYS_附件表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<SysAttachment> queryForListParam(Map<String, Object> param){
		return sysAttachmentMapper.queryForList(param);
	}
	/**
	 * 查询 SYS_附件表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<Attachment> queryForList(Map<String, Object> param){
		List<SysAttachment> list = this.queryForListParam(param);
		return BeanUtils.copyProperties(list, Attachment.class);
	}
	
	
}