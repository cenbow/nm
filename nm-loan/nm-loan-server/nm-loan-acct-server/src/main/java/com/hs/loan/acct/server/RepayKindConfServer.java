package com.hs.loan.acct.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.api.RepayKindConfApi;
import com.hs.loan.acct.dto.RepayKindConfDto;
import com.hs.loan.acct.entity.PubRepayKindConf;
import com.hs.loan.acct.service.PubRepayKindConfService;
import com.hs.loan.prod.api.ProdApi;
import com.hs.utils.BeanUtils;


/**
 * 还款方式配置服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class RepayKindConfServer implements RepayKindConfApi{

	@Autowired
	private PubRepayKindConfService pubRepayKindConfService;
	
	@Autowired
	private ProdApi acctProdService;
	
	/**
	 * 保存或更新 还款方式
	 * @param pubRepayKindConf
	 */
	@Transactional
	public void save(RepayKindConfDto repayKindConfDto){
		PubRepayKindConf pubRepayKindConf = new PubRepayKindConf();
		BeanUtils.copyProperties(repayKindConfDto, pubRepayKindConf);
		pubRepayKindConfService.save(pubRepayKindConf);
	}
	
	/**
	 * 通过还款方式编号 删除 还款方式
	 * @param repayNo
	 */
	@Transactional
	public void deleteByNo(String repayNo){
		boolean izRel = acctProdService.queryProdRepayKind(repayNo);
		if(!izRel){
			throw new ServiceException("该还款方式已经与产品关联不能删除");
		}
		pubRepayKindConfService.deleteByNo(repayNo);
	}
	
	/**
	 * 通过还款方式编号 获取 还款方式
	 * @param repayNo
	 */
	public RepayKindConfDto getByNo(String repayNo){
		PubRepayKindConf pubRepayKindConf = pubRepayKindConfService.getByNo(repayNo);
		RepayKindConfDto repayKindConfDto = new RepayKindConfDto();
		BeanUtils.copyProperties(pubRepayKindConf, repayKindConfDto);
		return repayKindConfDto;
	}
	
	/**
	 * 获取 还款方式list
	 * @param param
	 * @return
	 */
	public List<RepayKindConfDto> getList(Map<String, Object> param){
		List<PubRepayKindConf> sLst = pubRepayKindConfService.getList(param);
		return BeanUtils.copyProperties(sLst, RepayKindConfDto.class);
	}
	
	
	/**
	 * 分页查询 还款方式
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<RepayKindConfDto> queryRepayConf(Page<RepayKindConfDto> page){
		return pubRepayKindConfService.queryRepayConf(page.toPage(PubRepayKindConf.class)).toPage(RepayKindConfDto.class);
	}
	
	
}
