package com.hs.loan.acct.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.api.RepayTypConfApi;
import com.hs.loan.acct.dto.RepayTypConfDto;
import com.hs.loan.acct.entity.PubRepayTypConf;
import com.hs.loan.acct.service.PubRepayTypConfService;
import com.hs.loan.prod.api.ProdApi;
import com.hs.utils.BeanUtils;

/**
 * PUB_还款类型配置信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class RepayTypConfServer implements RepayTypConfApi {

	@Autowired
	private PubRepayTypConfService pubRepayTypConfService;
	
	@Autowired
	private ProdApi acctProdService;
	
	/**
	 * 保存或者更新 还款类型配置信息
	 */
	@Transactional
	public void save(RepayTypConfDto repayTypConfDto){
		PubRepayTypConf pubRepayTypConf = new PubRepayTypConf();
		BeanUtils.copyProperties(repayTypConfDto, pubRepayTypConf);
		pubRepayTypConfService.save(pubRepayTypConf);
	}
	
	/**
	 * 通过还款类型配置编号 删除 还款类型配置信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByNo(String confNo){
		boolean izRel = acctProdService.queryProdRepayType(confNo);
		if(!izRel){
			throw new ServiceException("该还款类型已经与产品关联不可删除");
		}
		pubRepayTypConfService.deleteByNo(confNo);
	}
	
	/**
	 * 通过还款类型配置编号 获取 还款类型配置信息
	 * @param primaryKey 主键
	 * @return PUB_还款类型配置信息对象
	 */
	public RepayTypConfDto getByNo(String confNo){
		RepayTypConfDto repayTypConfDto = new RepayTypConfDto();
		PubRepayTypConf pubRepayTypConf = pubRepayTypConfService.getByNo(confNo);
		BeanUtils.copyProperties(pubRepayTypConf, repayTypConfDto);
		return repayTypConfDto;
	}
	
	/**
	 * 查询还款类型配置信息List
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<RepayTypConfDto> getList(Map<String, Object> param){
		return BeanUtils.copyProperties(pubRepayTypConfService.getList(param), RepayTypConfDto.class);
	}
	
	/**
	 * 分页查询还款类型配置信息
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<RepayTypConfDto> queryRepayTypConf(Page<RepayTypConfDto> page){
		return pubRepayTypConfService.queryRepayTypConf(page.toPage(PubRepayTypConf.class)).toPage(RepayTypConfDto.class);
	}
	
	
}
