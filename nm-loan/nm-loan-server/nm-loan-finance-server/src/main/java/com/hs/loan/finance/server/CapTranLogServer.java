package com.hs.loan.finance.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.CapTranLogApi;
import com.hs.loan.finance.dto.AccCapTranLogDto;
import com.hs.loan.finance.entity.AccCapTranLog;
import com.hs.loan.finance.service.AccCapTranLogService;
import com.hs.utils.BeanUtils;

/**
 * ACC_银联交易日志（单笔） 接口
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly = true)
public class  CapTranLogServer implements CapTranLogApi{

	@Autowired
	private AccCapTranLogService accCapTranLogService;
	
	@Override
	public void insert(AccCapTranLogDto accCapTranLogDto) throws ServiceException{
		AccCapTranLog log = new AccCapTranLog();
		BeanUtils.copyProperties(accCapTranLogDto, log);
		accCapTranLogService.insert(log);
		
	}

	@Override
	public void updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException{
		accCapTranLogService.updateByPrimaryKeySelective(map);
	}

	@Override
	public void deleteByPrimaryKey(String primaryKey) throws ServiceException{
		accCapTranLogService.deleteByPrimaryKey(primaryKey);
		
	}

	
	@Override
	public AccCapTranLogDto getByPrimaryKey(String primaryKey) throws ServiceException{
		AccCapTranLog dt = accCapTranLogService.getByPrimaryKey(primaryKey);
		AccCapTranLogDto o = new AccCapTranLogDto();
		BeanUtils.copyProperties(dt, o);
		return o;
	}

	/**
	 * 获取交易日志列表
	 */
	@Override
	public List<AccCapTranLogDto> queryForList(Map<String, Object> param) throws ServiceException{
		// TODO Auto-generated method stub
		return BeanUtils.copyProperties(accCapTranLogService.queryForList(param), AccCapTranLogDto.class);
	}

	/**
	 * 分页查询单扣交易日志
	 */
	@Override
	public Page<AccCapTranLogDto> queryForPage(Page<AccCapTranLogDto> page) throws ServiceException{
		// TODO Auto-generated method stub
		return accCapTranLogService.queryForPage(page.toPage(AccCapTranLog.class)).toPage(AccCapTranLogDto.class);
	}
	
	
}