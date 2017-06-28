package com.hs.loan.prod.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.acct.entity.PubRepayKindConf;
import com.hs.loan.acct.entity.PubRepayTypConf;
import com.hs.loan.acct.service.PubRepayKindConfService;
import com.hs.loan.acct.service.PubRepayTypConfService;
import com.hs.loan.finance.entity.PubFundChanInfo;
import com.hs.loan.finance.service.PubFundChanInfoService;
import com.hs.loan.prod.bo.ProdBaseInfoBO;
import com.hs.loan.prod.bo.PubProdFeeBo;
import com.hs.loan.prod.constants.ProdConstant;
import com.hs.loan.prod.entity.PubProd;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.entity.PubProdFundChan;
import com.hs.loan.prod.entity.PubProdGoods;
import com.hs.loan.prod.entity.PubProdRepayTyp;
import com.hs.loan.prod.mapper.PubProdMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_产品信息 业务处理
 * 
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly = true)
public class PubProdService {

	@Autowired
	private PubRepayTypConfService pubRepayTypConfService;
	@Autowired
	private PubRepayKindConfService pubRepayKindConfService;

	@Autowired
	private PubProdRepayTypService pubProdRepayTypService;

	@Autowired
	private PubProdFundChanService pubProdFundChanService;

	@Autowired
	private PubFundChanInfoService pubFundChanInfoService;

	@Autowired
	private PubProdGoodsService pubProdGoodsService;

	@Autowired
	private PubProdFeeService pubProdFeeService;

	@Autowired
	private PubProdMapper pubProdMapper;

	/**
	 * 新增 PUB_产品信息
	 * 
	 * @param pubProd
	 * @return
	 */
	@Transactional
	public void insert(PubProd pubProd) {
		pubProdMapper.insert(pubProd);
	}

	/**
	 * 通过主键修改 PUB_产品信息
	 * 
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		pubProdMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_产品信息
	 * 
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		pubProdMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_产品信息 对象
	 * 
	 * @param primaryKey
	 * @return
	 */
	public PubProd getByPrimaryKey(String primaryKey) {
		return pubProdMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_产品信息 列表
	 * 
	 * @param param
	 * @return List<T>
	 */
	public List<PubProd> queryForList(Map<String, Object> param) {
		return pubProdMapper.queryForList(param);
	}

	/**
	 * 查询 PUB_产品信息 分页列表
	 * 
	 * @param page
	 * @return List<T>
	 */
	public Page<PubProd> queryForPage(Page<PubProd> page) {
		pubProdMapper.queryForList(page.getPageParams());
		return (Page<PubProd>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 将多选的字符串 转换成带引号的字符串
	 * 
	 * @param desc
	 * @return
	 */
	public static String stringToString(String desc) {
		StringBuffer sb = new StringBuffer();
		if (!StringUtils.isEmpty(desc)) {
			String[] arrs = desc.split(",");
			for (int i = 0; i < arrs.length; i++) {
				String ss = arrs[i];
				sb.append("'" + ss + "',");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		return sb.toString();
	}

	/**
	 * 查询产品基本信息
	 * 
	 * @param page
	 * @return
	 */
	public Page<ProdBaseInfoBO> queryProd(Page<ProdBaseInfoBO> page) {

		List<ProdBaseInfoBO> retList = new ArrayList<>();
		// 产品产品基本信息
		Map<String, Object> map = page.getPageParams();
		if (null != map.get("chanNo")) {
			map.put("chanNo", stringToString(map.get("chanNo").toString()));
		}
		if (null != map.get("branchNo")) {
			map.put("branchNo", stringToString(map.get("branchNo").toString()));
		}
		if (null != map.get("confNo")) {
			map.put("confNo", stringToString(map.get("confNo").toString()));
		}
		if (null != map.get("orgNo")) {
			map.put("orgNo", stringToString(map.get("orgNo").toString()));
		}

		List<PubProd> list = pubProdMapper.queryForBaseList(page.getPageParams());

		Map<String, Object> paramMap = new HashMap<>();
		for (PubProd pubProd : list) {
			ProdBaseInfoBO baseInfoBO = new ProdBaseInfoBO();

			String RepayKind = pubProd.getRepayKind(); // 设置还款方式
			String repayNo = pubProd.getRepayKind();
			/*
			 * BigDecimal rat = pubProd.getRat(); if(rat != null){
			 * pubProd.setRat(rat.multiply(new BigDecimal(100))); }
			 */
			if (!StringUtils.isEmpty(RepayKind)) {
				PubRepayKindConf conf = pubRepayKindConfService.getByPrimaryKey(RepayKind);
				if (conf != null) {
					pubProd.setRepayKind(conf.getRepayName());
					pubProd.setRepayNo(repayNo);
				}
			}

			baseInfoBO.setPubProd(pubProd);
			paramMap.put("prodNo", pubProd.getProdNo());
			// 查询产品和还款类型的关系信息
			List<PubProdRepayTyp> prodRepayTyps = pubProdRepayTypService.queryForList(paramMap);
			List<PubRepayTypConf> pubRepayFeeConfs = new ArrayList<>();
			for (PubProdRepayTyp pubProdRepayTyp : prodRepayTyps) {
				// 查询还款类型信息
				PubRepayTypConf prodRepayTyp = pubRepayTypConfService.getByPrimaryKey(pubProdRepayTyp.getConfNo());
				pubRepayFeeConfs.add(prodRepayTyp);
			}
			baseInfoBO.setPubRepayTypConfs(pubRepayFeeConfs);

			List<PubProdFundChan> pubProdFundChans = pubProdFundChanService.queryForList(paramMap);
			List<PubFundChanInfo> pubFundChanInfos = new ArrayList<>();
			for (PubProdFundChan pubProdFundChan : pubProdFundChans) {
				// 查询还款类型信息
				PubFundChanInfo pubFundChanInfo = pubFundChanInfoService.getByPrimaryKey(pubProdFundChan.getChanNo());
				pubFundChanInfos.add(pubFundChanInfo);
			}
			baseInfoBO.setPubFundChanInfos(pubFundChanInfos);

			/*** 产品和商品信息 **/
			List<PubProdGoods> pubProdGoods = pubProdGoodsService.queryForList(paramMap);
			baseInfoBO.setPubProdGoodss(pubProdGoods);
			// ProdBaseInfoBO baseInfoBO =
			// this.getProdBaseInfo(pubProd.getProdNo());
			baseInfoBO.setPubProd(pubProd);
			retList.add(baseInfoBO);
			page.setList(retList);
		}
		return (Page<ProdBaseInfoBO>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 保存产品信息
	 */
	@Transactional
	public void saveProdBaseInfo(ProdBaseInfoBO baseInfoBO) {
		PubProd prod = baseInfoBO.getPubProd();

		String prodNo = prod.getProdNo();
		if (!StringUtils.isEmpty(prodNo)) {
			this.deleteByPrimaryKey(prodNo);
			pubProdFundChanService.deleteByProdNo(prodNo);
			pubProdRepayTypService.deleteByProdNo(prodNo);
			pubProdGoodsService.deleteByProdNo(prodNo);
		} else {
			prod.setProdStat(ProdConstant.PRODSTAT_WAITING);
			prod.setProdNo(RandomUtil.getUUID());
		}
		prod.setInstDate(DateUtils.getCurrentDate());
		this.insert(prod); // 保存产品信息
		List<PubFundChanInfo> funChanlst = baseInfoBO.getPubFundChanInfos();
		PubProdFundChan prodFundChan = new PubProdFundChan();
		int i = 0;
		for (PubFundChanInfo pubFundChanInfo : funChanlst) {
			i++;
			prodFundChan.setId(RandomUtil.getUUID());
			prodFundChan.setProdNo(prod.getProdNo());
			prodFundChan.setChanNo(pubFundChanInfo.getChanNo());
			prodFundChan.setChanName(pubFundChanInfo.getChanName());
			prodFundChan.setSetlPrior(Integer.valueOf(i).toString());
			pubProdFundChanService.insert(prodFundChan); // 保存渠道产品关系
		}
		List<PubRepayTypConf> repayTypConfslst = baseInfoBO.getPubRepayTypConfs();
		PubProdRepayTyp prodRepayTyp = new PubProdRepayTyp();
		for (PubRepayTypConf pubRepayFeeConf : repayTypConfslst) {
			prodRepayTyp.setId(RandomUtil.getUUID());
			prodRepayTyp.setProdNo(prod.getProdNo());
			prodRepayTyp.setConfNo(pubRepayFeeConf.getConfNo());
			pubProdRepayTypService.insert(prodRepayTyp);// 保存还款类型产品关系
		}
		List<PubProdGoods> pubProdGoodss = baseInfoBO.getPubProdGoodss();

		for (PubProdGoods pubProdGoods : pubProdGoodss) {
			pubProdGoods.setId(RandomUtil.getUUID());
			pubProdGoods.setProdNo(prod.getProdNo());
			pubProdGoodsService.insert(pubProdGoods);// 保存商品产品关系
		}
	}

	/**
	 * 根据产品编号获取产品信息
	 */
	public ProdBaseInfoBO getProdBaseInfo(String prodNo) {
		// 产品产品基本信息
		ProdBaseInfoBO baseInfoBO = new ProdBaseInfoBO();
		PubProd pubProd = this.getByPrimaryKey(prodNo);

		if (pubProd == null) {
			throw new ServiceException("根据分期编号未获取到产品信息");
		}
		String RepayKind = pubProd.getRepayKind(); // 设置还款方式
		/*
		 * BigDecimal rat = pubProd.getRat(); if(rat != null){
		 * pubProd.setRat(rat.multiply(new BigDecimal(100))); }
		 * 
		 * String fstPayTyp = pubProd.getFstPayTyp(); BigDecimal fstPayVal =
		 * pubProd.getFstPayVal();
		 * if(ProdConstant.FSTPAYTYPE_RATE.equals(fstPayTyp)){ //
		 * pubProd.setFstPayVal(fstPayVal.multiply(new BigDecimal(100))); }
		 */

		if (!StringUtils.isEmpty(RepayKind)) {
			PubRepayKindConf conf = pubRepayKindConfService.getByPrimaryKey(RepayKind);
			if (conf != null) {
				pubProd.setRepayKind(conf.getRepayName());
			}
		}
		baseInfoBO.setPubProd(pubProd);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodNo", pubProd.getProdNo());
		// 查询产品和还款类型的管信息
		List<PubProdRepayTyp> prodRepayTyps = pubProdRepayTypService.queryForList(paramMap);
		List<PubRepayTypConf> pubRepayFeeConfs = new ArrayList<>();
		for (PubProdRepayTyp pubProdRepayTyp : prodRepayTyps) {
			// 查询还款类型信息
			PubRepayTypConf prodRepayTyp = pubRepayTypConfService.getByPrimaryKey(pubProdRepayTyp.getConfNo());
			pubRepayFeeConfs.add(prodRepayTyp);
		}
		baseInfoBO.setPubRepayTypConfs(pubRepayFeeConfs);

		List<PubProdFundChan> pubProdFundChans = pubProdFundChanService.queryForList(paramMap);
		List<PubFundChanInfo> pubFundChanInfos = new ArrayList<>();
		for (PubProdFundChan pubProdFundChan : pubProdFundChans) {
			// 查询还款类型信息
			PubFundChanInfo pubFundChanInfo = pubFundChanInfoService.getByPrimaryKey(pubProdFundChan.getChanNo());
			pubFundChanInfos.add(pubFundChanInfo);
		}
		baseInfoBO.setPubFundChanInfos(pubFundChanInfos);

		/*** 产品和商品信息 **/
		List<PubProdGoods> pubProdGoods = pubProdGoodsService.queryForList(paramMap);
		baseInfoBO.setPubProdGoodss(pubProdGoods);
		// 查询产品和费用项信息
		List<PubProdFeeBo> pubProdFee = pubProdFeeService.queryForListFee2(paramMap);
		baseInfoBO.setPubProdFeeDtos(BeanUtils.copyProperties(pubProdFee, PubProdFee.class));

		return baseInfoBO;
	}

	/**
	 * 查询产品基本信息-分期试算
	 * 
	 * @param page
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<PubProd> queryProdForLoanCal(Map<String, Object> map) {
		// 销售渠道，线上，线下，APP
		if (null == map.get("saleChan") || "".equals(map.get("saleChan"))) {
			map.put("I_SALE_CHAN", CommonConstant.SALECHANL_XYD);
		} else {
			map.put("I_SALE_CHAN", map.get("saleChan"));
		}
		// 产品类型 消费贷，现金贷
		if (null == map.get("prodTyp") || "".equals(map.get("prodTyp"))) {
			map.put("I_PROD_TYP", CommonConstant.PRODTYP_XFD);
		} else {
			map.put("I_PROD_TYP", map.get("prodTyp"));
		}

		if (null == map.get("branchNo")) {
			throw new ServiceException("网点不能为空");
		} else {
			map.put("I_STR_NO", map.get("branchNo"));
		}
		if (null == map.get("orgNo")) {
			throw new ServiceException("销售机构空,请重新登录或联系运营");
		} else {
			map.put("I_ORG_NO", map.get("orgNo"));
		}
		if (null == map.get("staffNo")) {
			throw new ServiceException("工号为空");
		} else {
			map.put("I_STAFF_NO", map.get("staffNo").toString());
		}
		if (null == map.get("goodsType")) {
			throw new ServiceException("商品类型为空");
		} else {
			map.put("I_GOODS_TYP", map.get("goodsType").toString());
		}
		if (null == map.get("custType")) {
			throw new ServiceException("客户类型为空");
		} else {
			map.put("I_CUST_TYP", map.get("custType").toString());
		}
		List<PubProd> list = new ArrayList<>();
		List<Map<String, Object>> listPreFee = null;
		try {
			list = pubProdMapper.queryProdLisForLoanCal(map);
			listPreFee = pubProdMapper.getPreFee(list);
			if (listPreFee!=null&&listPreFee.size()>0) {
				for (Map<String, Object> preFee:listPreFee) {
					for(PubProd pubProd:list){
						if (pubProd.getProdNo().equals(preFee.get("prodNo").toString())) {
							pubProd.setFstPayTyp(preFee.get("fstPayTyp").toString());
							pubProd.setFstPayVal(new BigDecimal(preFee.get("fstPayVal").toString()));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == map.get("O_RET")) {
			throw new ServiceException("获取产品信息失败");
		}
		return list;
	}

	public PubProdFeeBo getProdPFeeInfo(String prodNo, int perios) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodNo", prodNo);
		paramMap.put("instNum", perios);
		
		// 查询产品和费用项信息
		List<PubProdFeeBo> pubProdFee = pubProdFeeService.queryForListFee2(paramMap);
		if (pubProdFee.size()>1) {
			throw new ServiceException("请传入产品的分期期数");
		}else if (pubProdFee.size()==1) {
			return pubProdFee.get(0);
		}
		return null;
	}

}