package com.hs.loan.finance.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.CapTranLogBatApi;
import com.hs.loan.finance.dto.AccCapTranLogBatDto;
import com.hs.loan.finance.dto.AccCapTranLogDto;
import com.hs.loan.finance.entity.AccCapTranLogBat;
import com.hs.loan.finance.service.AccCapTranLogBatService;
import com.hs.utils.BeanUtils;

/**
 * ACC_银联交易日志（批量） 接口
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly = true)
public class  CapTranLogBatServer implements CapTranLogBatApi{
	
	@Autowired
	private AccCapTranLogBatService accCapTranLogBatService;

	@Override
	public void insert(AccCapTranLogBatDto accCapTranLogBatDto) throws ServiceException{
		AccCapTranLogBat actb = new AccCapTranLogBat();
		BeanUtils.copyProperties(accCapTranLogBatDto, actb);
		accCapTranLogBatService.insert(actb);
	}

	@Override
	public void updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException{
		accCapTranLogBatService.updateByPrimaryKeySelective(map);
		
	}

	@Override
	public void deleteByPrimaryKey(String primaryKey) throws ServiceException{
		accCapTranLogBatService.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public AccCapTranLogDto getByPrimaryKey(String primaryKey) throws ServiceException{
		// TODO Auto-generated method stub
		AccCapTranLogBat actb = accCapTranLogBatService.getByPrimaryKey(primaryKey);
		AccCapTranLogDto dto = new AccCapTranLogDto();
		BeanUtils.copyProperties(dto, actb);
		return dto;
	}

	@Override
	public List<AccCapTranLogBatDto> queryForList(Map<String, Object> param) throws ServiceException{
		// TODO Auto-generated method stub
		return BeanUtils.copyProperties(accCapTranLogBatService.queryForList(param), AccCapTranLogBatDto.class);
	}

	@Override
	public Page<AccCapTranLogBatDto> queryForPage(Page<AccCapTranLogBatDto> page) throws ServiceException{
		// TODO Auto-generated method stub
		return accCapTranLogBatService.queryForPage(page.toPage(AccCapTranLogBat.class)).toPage(AccCapTranLogBatDto.class);
	}
	
}