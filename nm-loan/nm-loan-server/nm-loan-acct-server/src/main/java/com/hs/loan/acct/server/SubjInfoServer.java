package com.hs.loan.acct.server;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hs.base.entity.Page;
import com.hs.loan.acct.api.SubjInfoApi;
import com.hs.loan.acct.dto.SubjInfoDto;
import com.hs.loan.acct.entity.PubSubjInfo;
import com.hs.loan.acct.service.PubSubjInfoService;
import com.hs.utils.BeanUtils;

/**
 * 科目信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class SubjInfoServer implements SubjInfoApi {

	@Autowired
	private PubSubjInfoService pubSubjInfoService;
	
	/**
	 * 通过id获取一个有效的科目信息
	 * @param subjId
	 * @return
	 */
	public SubjInfoDto getById(String subjId){
		PubSubjInfo pubSubjInfo = pubSubjInfoService.getById(subjId);
		SubjInfoDto subjInfoDto = new SubjInfoDto();
		BeanUtils.copyProperties(pubSubjInfo, subjInfoDto);
		return subjInfoDto;
	}
	
	/**
	 * 获取有效的 科目信息列表
	 * @param param
	 * @return
	 */
	public List<SubjInfoDto> getList(Map<String,Object> param){
		return BeanUtils.copyProperties(pubSubjInfoService.getList(param), SubjInfoDto.class);
	}
	
	/**
	 * 分页查询有效的 科目信息
	 * @param page
	 * @return
	 */
	public Page<SubjInfoDto> querySubjInfo(Page<SubjInfoDto> page){
		return pubSubjInfoService.querySubjInfo(page.toPage(PubSubjInfo.class)).toPage(SubjInfoDto.class);
	}
	
	
}
