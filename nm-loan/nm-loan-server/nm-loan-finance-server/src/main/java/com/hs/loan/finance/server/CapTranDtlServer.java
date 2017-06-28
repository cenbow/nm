package com.hs.loan.finance.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.CapTranDtlApi;
import com.hs.loan.finance.dto.AccCapTranDtlDto;
import com.hs.loan.finance.entity.AccCapTranDtl;
import com.hs.loan.finance.service.AccCapTranDtlService;
import com.hs.utils.BeanUtils;

/**
 *  银联交易明细
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly = true)
public class  CapTranDtlServer implements CapTranDtlApi{

	@Autowired
	private AccCapTranDtlService accCapTranDtlService;
	
	@Override
	public void insert(AccCapTranDtlDto accCapTranDtlDto) throws ServiceException{
		AccCapTranDtl o = new AccCapTranDtl();
		BeanUtils.copyProperties(accCapTranDtlDto, o);
		accCapTranDtlService.insert(o);
		
	}

	@Override
	public void updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException{
		accCapTranDtlService.updateByPrimaryKeySelective(map);
		
	}

	@Override
	public void deleteByPrimaryKey(String primaryKey) throws ServiceException{
		accCapTranDtlService.deleteByPrimaryKey(primaryKey);
		
	}

	@Override
	public AccCapTranDtlDto getByPrimaryKey(String primaryKey) throws ServiceException{
		AccCapTranDtl dt = accCapTranDtlService.getByPrimaryKey(primaryKey);
		AccCapTranDtlDto o = new AccCapTranDtlDto();
		BeanUtils.copyProperties(dt, o);
		return o;
	}

	@Override
	public List<AccCapTranDtlDto> queryForList(Map<String, Object> param) throws ServiceException{
		// TODO Auto-generated method stub
		return BeanUtils.copyProperties(accCapTranDtlService.queryForList(param),AccCapTranDtlDto.class);
	}

	@Override
	public Page<AccCapTranDtlDto> queryForPage(Page<AccCapTranDtlDto> page) throws ServiceException{
		// TODO Auto-generated method stub
		return accCapTranDtlService.queryForPage(page.toPage(AccCapTranDtl.class)).toPage(AccCapTranDtlDto.class);
	}
	
}