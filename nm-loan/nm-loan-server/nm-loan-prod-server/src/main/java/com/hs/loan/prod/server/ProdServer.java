package com.hs.loan.prod.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.acct.entity.PubProdFeeCalc;
import com.hs.loan.acct.entity.PubRepayFeeConf;
import com.hs.loan.acct.entity.PubRepayTypConf;
import com.hs.loan.acct.service.AcctFeeCalService;
import com.hs.loan.acct.service.PubRepayFeeConfService;
import com.hs.loan.finance.entity.PubFundChanInfo;
import com.hs.loan.finance.service.PubFundChanInfoService;
import com.hs.loan.prod.api.ProdApi;
import com.hs.loan.prod.api.ProdAreaApi;
import com.hs.loan.prod.api.ProdFeeApi;
import com.hs.loan.prod.api.ProdFundChanApi;
import com.hs.loan.prod.api.ProdOrgApi;
import com.hs.loan.prod.api.ProdStrApi;
import com.hs.loan.prod.api.PubProdGroupApi;
import com.hs.loan.prod.bo.ProdBaseInfoBO;
import com.hs.loan.prod.bo.ProdGroupBO;
import com.hs.loan.prod.bo.PubBranchBO;
import com.hs.loan.prod.bo.PubProdFeeBo;
import com.hs.loan.prod.constants.ProdConstant;
import com.hs.loan.prod.dto.ProFeeCalResultDto;
import com.hs.loan.prod.dto.ProdBaseInfoDto;
import com.hs.loan.prod.dto.ProdFeeCalDto;
import com.hs.loan.prod.dto.ProdFeeDto;
import com.hs.loan.prod.dto.ProdFeeResultBassDto;
import com.hs.loan.prod.dto.PubBranchDto;
import com.hs.loan.prod.dto.PubFundChanInfoDto;
import com.hs.loan.prod.dto.PubProdAreaDto;
import com.hs.loan.prod.dto.PubProdDto;
import com.hs.loan.prod.dto.PubProdFeeDto;
import com.hs.loan.prod.dto.PubProdFundChanDto;
import com.hs.loan.prod.dto.PubProdGoodsDto;
import com.hs.loan.prod.dto.PubProdGroupDto;
import com.hs.loan.prod.dto.PubProdStrDto;
import com.hs.loan.prod.dto.PubProdSysOrgDto;
import com.hs.loan.prod.dto.PubRepayFeeConfDto;
import com.hs.loan.prod.dto.PubRepayTypConfDto;
import com.hs.loan.prod.entity.PubProd;
import com.hs.loan.prod.entity.PubProdArea;
import com.hs.loan.prod.entity.PubProdCrowd;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.entity.PubProdFundChan;
import com.hs.loan.prod.entity.PubProdGoods;
import com.hs.loan.prod.entity.PubProdGroup;
import com.hs.loan.prod.entity.PubProdOrg;
import com.hs.loan.prod.entity.PubProdRepayTyp;
import com.hs.loan.prod.entity.PubProdStr;
import com.hs.loan.prod.service.PubProdAreaService;
import com.hs.loan.prod.service.PubProdCrowdService;
import com.hs.loan.prod.service.PubProdFeeService;
import com.hs.loan.prod.service.PubProdFundChanService;
import com.hs.loan.prod.service.PubProdGroupService;
import com.hs.loan.prod.service.PubProdOrgService;
import com.hs.loan.prod.service.PubProdRepayTypService;
import com.hs.loan.prod.service.PubProdService;
import com.hs.loan.prod.service.PubProdStrService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.system.entity.SysOrg;
import com.hs.system.group.PubGroupCrowdService;
import com.hs.system.group.bo.PubCrowdGroupBO;
import com.hs.system.org.PubSysOrgService;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * <p>
 * decribing : 分期产品信息
 * </p>
 * <p>
 * copyright : Copyright @ 2013 nm
 * </p>
 * <p>
 * company : 柠檬
 * </p>
 * <p>
 * time : 2015-11-19下午12:05:53
 * </p>
 * 
 * @author ymzhang
 * @version v1.0
 */

@Service
@Transactional(readOnly = true)
public class ProdServer
		implements ProdApi, ProdAreaApi, ProdFeeApi, ProdFundChanApi, ProdOrgApi, ProdStrApi, PubProdGroupApi {

	@Autowired
	private PubProdService pubProdService;
	@Autowired
	private PubProdAreaService pubProdAreaService;
	@Autowired
	private PubProdFundChanService pubProdFundChanService;
	@Autowired
	private PubProdFeeService pubProdFeeService;
	@Autowired
	private PubRepayFeeConfService pubRepayFeeConfService;
	@Autowired
	private PubFundChanInfoService pubFundChanInfoService;
	@Autowired
	private PubProdOrgService pubProdOrgService;
	@Autowired
	private PubSysOrgService orgService;
	@Autowired
	private AcctFeeCalService acctFeeCalService;
	@Autowired
	private PubGroupCrowdService pubGroupCrowdService;
	@Autowired
	private PubProdRepayTypService pubProdRepayTypService;
	@Autowired
	private PubProdStrService prodStrService;
	@Autowired
	private PubSysRegionalBelongService pubSysRegionalBelongService;
	@Autowired
	private PubProdGroupService pubProdGroupService;
	@Autowired
	private PubProdCrowdService pubProdCrowdService;

	/**
	 * 产品查询
	 */
	@Override
	public Page<ProdBaseInfoDto> queryProd(Page<ProdBaseInfoDto> page) {
		Page<ProdBaseInfoBO> page1 = pubProdService.queryProd(page.toPage(ProdBaseInfoBO.class));
		Page<ProdBaseInfoDto> target = page1.toPage(ProdBaseInfoDto.class);
		List<ProdBaseInfoDto> retList = new ArrayList<>();
		List<ProdBaseInfoBO> list = page1.getList();
		for (ProdBaseInfoBO prodBaseInfoBO : list) {
			ProdBaseInfoDto retDto = new ProdBaseInfoDto();
			PubProdDto prodDto = new PubProdDto();
			BeanUtils.copyProperties(prodBaseInfoBO.getPubProd(), prodDto);
			List<PubFundChanInfoDto> pubFundChanInfoDtos = BeanUtils
					.copyProperties(prodBaseInfoBO.getPubFundChanInfos(), PubFundChanInfoDto.class);
			List<PubProdGoodsDto> pubProdGoodsDtos = BeanUtils.copyProperties(prodBaseInfoBO.getPubProdGoodss(),
					PubProdGoodsDto.class);
			List<PubRepayTypConfDto> pubRepayTypConfDtos = BeanUtils
					.copyProperties(prodBaseInfoBO.getPubRepayTypConfs(), PubRepayTypConfDto.class);
			retDto.setPubProd(prodDto);
			retDto.setPubFundChanInfos(pubFundChanInfoDtos);
			retDto.setPubProdGoodss(pubProdGoodsDtos);
			retDto.setPubRepayTypConfs(pubRepayTypConfDtos);
			retList.add(retDto);
		}
		target.setList(retList);
		return target;
	}

	/**
	 * 产品查询列表-分期试算
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<PubProdDto> queryProdForLoan(Map<String, Object> map) {
		List<PubProd> list = pubProdService.queryProdForLoanCal(map);
		return BeanUtils.copyProperties(list, PubProdDto.class);
	}

	/**
	 * 产品上架
	 */
	@Override
	@Transactional
	public void onProd(String prodNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodNo", prodNo);
		map.put("prodStat", ProdConstant.PRODSTAT_SALEING);
		pubProdService.updateByPrimaryKeySelective(map);
	}

	/**
	 * 产品下架
	 */
	@Override
	@Transactional
	public void offProd(String prodNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodNo", prodNo);
		map.put("prodStat", ProdConstant.PRODSTAT_DISABLE);
		pubProdService.updateByPrimaryKeySelective(map);
	}

	/**
	 * 保存产品基本信息
	 * 
	 * @param baseInfoDto
	 */
	@Override
	@Transactional
	public void saveProdBaseInfo(ProdBaseInfoDto baseInfoDto) {
		ProdBaseInfoBO baseInfoBO = new ProdBaseInfoBO();
		PubProd pubProd = new PubProd();
		BeanUtils.copyPropertiesNotForce(pubProd, baseInfoDto.getPubProd());
		baseInfoBO.setPubProd(pubProd);

		List<PubRepayTypConfDto> list = baseInfoDto.getPubRepayTypConfs();
		List<PubRepayTypConf> pubRepayTypConfs = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			PubRepayTypConfDto pubRepayTypConfDto = list.get(i);
			PubRepayTypConf pubRepayTypConf = new PubRepayTypConf();
			pubRepayTypConf.setConfName(pubRepayTypConfDto.getConfName());
			pubRepayTypConf.setConfNo(pubRepayTypConfDto.getConfNo());
			pubRepayTypConfs.add(pubRepayTypConf);
		}
		baseInfoBO.setPubRepayTypConfs(pubRepayTypConfs);

		List<PubFundChanInfoDto> list2 = baseInfoDto.getPubFundChanInfos();
		List<PubFundChanInfo> pubFundChanInfos = new ArrayList<>();
		for (int i = 0; i < list2.size(); i++) {
			PubFundChanInfoDto pubFundChanInfoDto = list2.get(i);
			PubFundChanInfo pubFundChanInfo = new PubFundChanInfo();
			pubFundChanInfo.setChanNo(pubFundChanInfoDto.getChanNo());
			pubFundChanInfo.setChanName(pubFundChanInfoDto.getChanName());
			pubFundChanInfos.add(pubFundChanInfo);
		}
		baseInfoBO.setPubFundChanInfos(pubFundChanInfos);

		List<PubProdGoodsDto> list3 = baseInfoDto.getPubProdGoodss();
		List<PubProdGoods> pubProdGoodss = new ArrayList<>();
		for (int i = 0; i < list3.size(); i++) {
			PubProdGoodsDto pubProdGoodsDto = list3.get(i);
			PubProdGoods pubProdGoods = new PubProdGoods();
			pubProdGoods.setGoodsId(pubProdGoodsDto.getGoodsId());
			pubProdGoods.setGoodsName(pubProdGoodsDto.getGoodsName());
			pubProdGoodss.add(pubProdGoods);
		}
		baseInfoBO.setPubProdGoodss(pubProdGoodss);
		pubProdService.saveProdBaseInfo(baseInfoBO);
	}

	/**
	 * 获取单个产品基本信息
	 */
	@Override
	public ProdBaseInfoDto getProdBaseInfo(String prodNo) {
		ProdBaseInfoDto retDto = new ProdBaseInfoDto();
		ProdBaseInfoBO baseInfoBO = pubProdService.getProdBaseInfo(prodNo);
		PubProdDto prodDto = new PubProdDto();
		BeanUtils.copyProperties(baseInfoBO.getPubProd(), prodDto);
		List<PubFundChanInfoDto> pubFundChanInfoDtos = BeanUtils.copyProperties(baseInfoBO.getPubFundChanInfos(),
				PubFundChanInfoDto.class);
		List<PubProdGoodsDto> pubProdGoodsDtos = BeanUtils.copyProperties(baseInfoBO.getPubProdGoodss(),
				PubProdGoodsDto.class);
		List<PubRepayTypConfDto> pubRepayTypConfDtos = BeanUtils.copyProperties(baseInfoBO.getPubRepayTypConfs(),
				PubRepayTypConfDto.class);
		List<PubProdFeeDto> prodFeeDtos = BeanUtils.copyProperties(baseInfoBO.getPubProdFeeDtos(), PubProdFeeDto.class);
		retDto.setPubProd(prodDto);
		retDto.setPubFundChanInfos(pubFundChanInfoDtos);
		retDto.setPubProdGoodss(pubProdGoodsDtos);
		retDto.setPubRepayTypConfs(pubRepayTypConfDtos);
		retDto.setPubProdFeeDtos(prodFeeDtos);
		return retDto;
	}

	/**
	 * 获取产品区域信息
	 */
	@Override
	public Page<PubProdAreaDto> queryProdArea(Page<PubProdAreaDto> page) {
		Page<PubProdArea> page2 = page.toPage(PubProdArea.class);
		return pubProdAreaService.queryForPage(page2).toPage(PubProdAreaDto.class);
	}

	/**
	 * 保存产品区域信息 joon
	 */
	@Override
	@Transactional
	public void saveProdArea(PubProdAreaDto areaDto) {
		PubProdArea pubProdArea = new PubProdArea();
		BeanUtils.copyProperties(areaDto, pubProdArea);
		pubProdArea.setId(RandomUtil.getUUID());
		pubProdArea.setProvName(pubSysRegionalBelongService.getProvName(areaDto.getProvNo()));
		pubProdArea.setCityName(pubSysRegionalBelongService.getCityName(areaDto.getCityNo()));
		pubProdArea.setCntyName(pubSysRegionalBelongService.getCountName(areaDto.getCntyNo()));
		pubProdAreaService.savePubProdArea(pubProdArea);
	}

	/**
	 * 移除产品和区域关系
	 */
	@Override
	@Transactional
	public void removeProdArea(Set<String> ids) {
		for (String id : ids) {
			pubProdAreaService.deleteByPrimaryKey(id);
		}
	}

	/**
	 * 保存产品费用项
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void saveProdFee(List<PubProdFeeDto> prodFeeDtos, String prodNo) {
		int i = 0;
		for (PubProdFeeDto pubProdFeeDto : prodFeeDtos) {
			if ("1601,2001,2201,2401,2601,1401,1801".indexOf(pubProdFeeDto.getFeeNo()) != -1) {
				i++;
			}
		}
		if (i == 0) {
			throw new ServiceException("产品费用项不能为空");
		}
		pubProdFeeService.deleteByProdNo(prodNo);
		Map feemap = new HashMap<>();
		feemap.put("prodNo", prodNo);
		pubProdFeeService.delPubProdPrefee(feemap);
		for (PubProdFeeDto pubProdFeeDto : prodFeeDtos) {
			PubProdFee pubProdFee = new PubProdFee();
			BeanUtils.copyProperties(pubProdFeeDto, pubProdFee);
			pubProdFee.setId(RandomUtil.getUUID());
			pubProdFee.setProdNo(prodNo);
			Map map = new HashMap();
			map.put("id", RandomUtil.getUUID());
			map.put("prodNo", prodNo);
			map.put("feeNum", pubProdFeeDto.getInstNum());
			if (null == threeNull(pubProdFeeDto.getFeeRat())) {
				map.put("feeRat", new BigDecimal("0"));
			} else {
				map.put("feeRat", new BigDecimal(pubProdFeeDto.getFeeRat()));
			}
			if (null == threeNull(pubProdFeeDto.getRat())) {
				map.put("rat", new BigDecimal("0"));
			} else {
				map.put("rat", new BigDecimal(pubProdFeeDto.getRat()));
			}
			if (null == threeNull(pubProdFeeDto.getRat())) {
				map.put("rat", new BigDecimal("0"));
			} else {
				map.put("rat", new BigDecimal(pubProdFeeDto.getRat()));
			}
			if (null == threeNull(pubProdFeeDto.getFstPayTyp())) {
				map.put("fstPayTyp", "0");
			} else {
				map.put("fstPayTyp", pubProdFeeDto.getFstPayTyp());
			}
			if (null == threeNull(pubProdFeeDto.getFstPayVal())) {
				map.put("fstPayVal", new BigDecimal("0"));
			} else {
				map.put("fstPayVal", pubProdFeeDto.getFstPayVal());
			}
			if ("1111".equals(pubProdFeeDto.getFeeNo()) || "2222".equals(pubProdFeeDto.getFeeNo())) {
				pubProdFeeService.delPubProdPrefee(map);
				pubProdFeeService.insertPubProdPrefee(map);
			} else {
				pubProdFeeService.insert(pubProdFee);
			}
		}

	}

	private String threeNull(Object obj) {
		return (null == obj || "".equalsIgnoreCase(obj.toString().trim())) ? null : obj.toString().trim();
	}

	/**
	 * 查询产品费用项
	 */
	@Override
	public ProdFeeDto queryProdFeeInfo(String prodNo) {
		ProdFeeDto prodFeeDto = new ProdFeeDto();
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品编号不能为空");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);// 单独设置查询条件
		prodFeeDto.setProdFeeDtos(this.queryProdFee(map));
		prodFeeDto.setFeeDtos(this.queryCustFeeList(prodNo));
		return prodFeeDto;
	}

	/**
	 * 查询产品费用项
	 */
	public List<PubRepayFeeConfDto> queryCustFeeList(String prodNo) {
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品编号不能为空");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);// 单独设置查询条件
		List<PubProdFee> beanlist = pubProdFeeService.queryCustSelFeeList(map);
		StringBuffer sb = new StringBuffer();
		if (beanlist != null && beanlist.size() > 0) {
			for (int i = 0; i < beanlist.size(); i++) {
				PubProdFee pubProdFee = beanlist.get(i);
				sb.append("'" + pubProdFee.getFeeNo() + "'");
				sb.append(",");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			map.put("feeNo", sb.toString());
		}
		if (map.get("feeNo") == null || "".equals(map.get("feeNo"))) {
			map.put("feeNo", "''");
		}
		List<PubRepayFeeConf> pubRepayTypConf = pubRepayFeeConfService.queryProdUsedFeeConf(map);

		return BeanUtils.copyProperties(pubRepayTypConf, PubRepayFeeConfDto.class);
	}

	/**
	 * 查询产品费用项-所有的关系
	 */
	public List<PubProdFeeDto> queryProdFee2(Map<String, Object> map) {
		if (null == map.get("prodNo")) {
			throw new ServiceException("产品编号不能为空");
		}
		List<PubProdFeeBo> beanlist = pubProdFeeService.queryForListFee(map);
		return BeanUtils.copyProperties(beanlist, PubProdFeeDto.class);
	}

	/**
	 * 查询产品费用项-所有的关系
	 */
	@Override
	public List<PubProdFeeDto> queryProdFee(Map<String, Object> map) {
		if (null == map.get("prodNo")) {
			throw new ServiceException("产品编号不能为空");
		}
		List<PubProdFeeBo> beanlist = pubProdFeeService.queryForListFee2(map);
		return BeanUtils.copyProperties(beanlist, PubProdFeeDto.class);
	}

	/**
	 * 查询产品费用项-所有的关系
	 */
	@Override
	public void removeProdFee(String prodNo, String inum) {
		pubProdFeeService.deleteByProdNoAndInum(prodNo, inum);

	}

	/**
	 * 查询产品与费用项关系信息--客户可选则的费用项、和期数
	 * 
	 * @param prodFees
	 */
	public ProdFeeCalDto queryProdFeeForCal(String prodNo) {
		ProdFeeCalDto dto = new ProdFeeCalDto();
		dto.setSeleFees(this.queryProdFeeForCustSel(prodNo));
		dto.setInstNum(this.queryProdFeeForIvnNum(prodNo));
		return dto;
	}

	/**
	 * 查询产品费用项--客户可选则的费用项
	 */
	public List<PubProdFeeDto> queryProdFeeForCustSel(String prodNo) {
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品信息为空,请选择产品信息");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);// 单独设置查询条件
		map.put("isSel", CommonConstant.COMMON_YES);// 客户选择
		List<PubProdFee> beanlist = pubProdFeeService.queryCustSelFeeList(map);
		return BeanUtils.copyProperties(beanlist, PubProdFeeDto.class);
	}

	/**
	 * 查询产品费用项--客户可选则的费用项
	 */
	private List<String> queryProdFeeForIvnNum(String prodNo) {
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品信息为空,请选择产品信息");
		}
		List<String> invNums = pubProdFeeService.queryProdFeeForIvnNum(prodNo);
		return invNums;
	}

	/**
	 * 查询费用项-不在产品配置中的
	 */
	@Override
	public List<PubRepayFeeConfDto> queryPubRepayFeeConf(String prodNo) {
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品信息为空,请选择产品信息");
		}
		Map<String, Object> param = new HashMap<>();
		param.put("prodNo", prodNo);
		StringBuffer sb = new StringBuffer();
		List<PubProdFee> pubProdFees = pubProdFeeService.queryForList(param);
		if (pubProdFees != null && pubProdFees.size() > 0) {
			for (int i = 0; i < pubProdFees.size(); i++) {
				PubProdFee pubProdFee = pubProdFees.get(i);
				sb.append("'" + pubProdFee.getFeeNo() + "'");
				sb.append(",");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			param.put("feeNo", sb.toString());
		}
		List<PubRepayFeeConf> pubRepayTypConf = pubRepayFeeConfService.queryProdUnUsedFeeConf(param);
		return BeanUtils.copyProperties(pubRepayTypConf, PubRepayFeeConfDto.class);
	}

	/**
	 * 保存产品-资金渠道关系
	 */
	@Override
	@Transactional
	public void saveProdFundChan(List<PubProdFundChanDto> fundChanInfoDtos, String prodNo) {
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品信息为空,请选择产品信息");
		}
		PubProdFundChan pubProdFee = new PubProdFundChan();
		pubProdFundChanService.deleteByProdNo(prodNo);
		int i = 0;
		for (PubProdFundChanDto pubProdFeeDto : fundChanInfoDtos) {
			i++;
			BeanUtils.copyProperties(pubProdFeeDto, pubProdFee);
			pubProdFee.setProdNo(prodNo);
			pubProdFee.setSetlPrior(Integer.valueOf(i).toString());
			pubProdFee.setId(RandomUtil.getUUID());
			pubProdFundChanService.insert(pubProdFee);
		}

	}

	/**
	 * 查询不在产品中的资金渠道
	 */
	@Override
	public List<PubFundChanInfoDto> queryPubFundChanInfo(String prodNo) {
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品信息为空");
		}
		Map<String, Object> param = new HashMap<>();
		param.put("prodNo", prodNo);
		StringBuffer sb = new StringBuffer();
		List<PubProdFundChan> pubProdFundChan = pubProdFundChanService.queryForList(param);
		if (pubProdFundChan != null && pubProdFundChan.size() > 0) {
			for (int i = 0; i < pubProdFundChan.size(); i++) {
				PubProdFundChan pubProdFee = pubProdFundChan.get(i);
				sb.append("'" + pubProdFee.getChanNo() + "'");
				sb.append(",");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			param.put("chanNo", sb.toString());
		}
		List<PubFundChanInfo> pubProdFundChans = pubFundChanInfoService.queryNotUseFundChanInfo(sb.toString());
		return BeanUtils.copyProperties(pubProdFundChans, PubFundChanInfoDto.class);
	}

	/**
	 * 查询产品和资金渠道关系信息
	 */
	@Override
	public List<PubProdFundChanDto> queryProdFundChan(String prodNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);// 单独设置查询条件
		List<PubProdFundChan> beanlist = pubProdFundChanService.queryForList(map);
		return BeanUtils.copyProperties(beanlist, PubProdFundChanDto.class);
	}

	/**
	 * 查询产品和资金渠道关系信息
	 */
	@Override
	@Transactional
	public void removeFunchanl(String id) {
		pubProdFundChanService.deleteByPrimaryKey(id);
	}

	/**
	 * 查询产品和机构关系
	 */
	@Override
	public Set<String> queryProdOrg(String prodNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);// 单独设置查询条件
		List<PubProdOrg> pubProdOrgs = pubProdOrgService.queryForList(map);
		Set<String> orgs = new HashSet<>();
		for (PubProdOrg pubProdOrg : pubProdOrgs) {
			orgs.add(pubProdOrg.getOrgNo());
		}
		return orgs;
	}

	/**
	 * 查询产品和机构关系
	 */
	@Override
	public Page<PubProdSysOrgDto> queryProdOrgForPage(Page<PubProdSysOrgDto> page) {
		Page<PubProdOrg> pubProdOrg = pubProdOrgService.queryForPage(page.toPage(PubProdOrg.class));
		return pubProdOrg.toPage(PubProdSysOrgDto.class);
	}

	/**
	 * 保存产品和机构关系
	 */
	@Override
	@Transactional
	public void saveProdOrg(Set<String> orgNos, String prodNo) {
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品编号不能为空");
		}
		pubProdOrgService.deleteByProdNo(prodNo);
		PubProdOrg pubProdFee = new PubProdOrg();
		for (String orgNo : orgNos) {
			pubProdFee.setId(RandomUtil.getUUID());
			pubProdFee.setOrgNo(orgNo);
			pubProdFee.setProdNo(prodNo);
			;
			SysOrg sysOrg = orgService.getByOrgNo(orgNo);
			pubProdFee.setOrgName(sysOrg.getOrgName());
			pubProdFee.setProvNo(sysOrg.getProvNo());
			pubProdFee.setCityNo(sysOrg.getCityNo());
			pubProdFee.setCntyNo(sysOrg.getCntyNo());
			pubProdFee.setAreaNo(sysOrg.getAreaNo());
			pubProdOrgService.insert(pubProdFee);
		}
	}

	/**
	 * 查询产品业务渠道
	 */
	@Override
	public Page<PubBranchDto> queryProdStr(Page<PubBranchDto> page) {
		return prodStrService.queryProdStr(page.toPage(PubBranchBO.class)).toPage(PubBranchDto.class);
	}

	/**
	 * 保存网点和产品关系
	 */
	@Override
	@Transactional
	public void saveProdStr(List<PubProdStrDto> pubProdStrDto, String prodNo) {
		Map<String, Object> param = new HashMap<>();
		PubProdStr pubProdStr = new PubProdStr();
		for (PubProdStrDto prodStrDto : pubProdStrDto) {
			param.put("prodNo", prodStrDto.getProdNo());
			param.put("branchNo", prodStrDto.getBranchNo());
			List<PubProdStr> list = prodStrService.queryForList(param);
			if (list == null || list.size() == 0) {
				BeanUtils.copyProperties(prodStrDto, pubProdStr);
				pubProdStr.setId(RandomUtil.getUUID());
				prodStrService.insert(pubProdStr);
			}
		}
	}

	/**
	 * 删除产品和网点的关系
	 */
	@Override
	@Transactional
	public void removeProdStr(Set<String> ids) {
		for (String id : ids) {
			prodStrService.deleteByPrimaryKey(id);
		}
	}

	/**
	 * 产品试算
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ProFeeCalResultDto tryCalProdFee(String prodNo, BigDecimal goodsPrice, int perios) {
		BigDecimal loanAmt = goodsPrice;// 分期金额 默认商品金额
		BigDecimal fstPayMentAmt = new BigDecimal(0);// 首付金额
		String fstRepayType = "";
		BigDecimal fstPayVal = BigDecimal.ZERO;
		PubProd prod = pubProdService.getByPrimaryKey(prodNo);
		if (prod == null) {
			return null;
		}
		PubProdFeeBo fee = pubProdService.getProdPFeeInfo(prodNo, perios);
		if (fee != null) {
			fstRepayType = fee.getFstPayTyp();
			fstPayVal = fee.getFstPayVal();
		}
		if (fstPayVal == null) {
			fstPayVal = new BigDecimal("0");
		}
		if (ProdConstant.FSTPAYTYPE_RATE.equals(fstRepayType)) {
			fstPayMentAmt = goodsPrice.multiply(fstPayVal.divide(new BigDecimal("100")));
			loanAmt = goodsPrice.subtract(fstPayMentAmt);
		} else if (ProdConstant.FSTPAYTYPE_AMT.equals(fstPayVal)) {
			fstPayMentAmt = fstPayVal;
			loanAmt = goodsPrice.subtract(fstPayMentAmt);
		}
		// 调用费用计算组件的方法
		List<PubProdFeeCalc> prodFeeCalcs = acctFeeCalService.calc(prodNo, loanAmt, perios);

		ProFeeCalResultDto calResultDto = new ProFeeCalResultDto();
		calResultDto.setPaymentAmt(fstPayMentAmt);
		calResultDto.setLoanAmt(loanAmt);
		calResultDto.setFeeResultBassDtos(BeanUtils.copyProperties(prodFeeCalcs, ProdFeeResultBassDto.class));
		return calResultDto;
	}

	/**
	 * 查询群组信息
	 */
	@Override
	public Page<PubProdGroupDto> queryPubGroup(Page<PubProdGroupDto> page) {
		page.getPageParams().put("judgeCont", "true");
		return (pubGroupCrowdService.queryGrpCrowd(page.toPage(PubCrowdGroupBO.class))).toPage(PubProdGroupDto.class);
	}

	/**
	 * 查询产品和群组信息
	 */
	@Override
	public Page<PubProdGroupDto> queryPubProdGroup(Page<PubProdGroupDto> page) {
		Page<ProdGroupBO> page2 = pubProdGroupService.queryForPageGroupTeam(page.toPage(ProdGroupBO.class));
		return page2.toPage(PubProdGroupDto.class);
	}

	/**
	 * 保存产品和群组关系
	 */
	@Override
	@Transactional
	public void savePubProdGroup(List<PubProdGroupDto> prodGroupDtos) {
		Map<String, Object> param = new HashMap<>();
		PubProdCrowd pubProdCrowd = new PubProdCrowd();
		PubProdGroup pubProdGroup = new PubProdGroup();
		for (PubProdGroupDto pubProdGroupDto : prodGroupDtos) {
			if (PubBusinessConstant.SALETYPE_GROUP.equals(pubProdGroupDto.getProperty())) {
				param.put("prodNo", pubProdGroupDto.getProdNo());
				param.put("groupNo", pubProdGroupDto.getGroupNo());
				List<PubProdGroup> pubProdGroups = pubProdGroupService.queryForList(param);
				if (pubProdGroups == null || pubProdGroups.size() == 0) {
					pubProdGroup.setId(RandomUtil.getUUID());
					pubProdGroup.setGroupName(pubProdGroupDto.getGroupName());
					pubProdGroup.setGroupNo(pubProdGroupDto.getGroupNo());
					pubProdGroup.setProdNo(pubProdGroupDto.getProdNo());
					pubProdGroupService.insert(pubProdGroup);
				}
			}
			if (PubBusinessConstant.SALETYPE_CROWD.equals(pubProdGroupDto.getProperty())) {
				param.put("prodNo", pubProdGroupDto.getProdNo());
				param.put("crowdNo", pubProdGroupDto.getGroupNo());
				List<PubProdCrowd> pubProdTeams = pubProdCrowdService.queryForList(param);
				if (pubProdTeams == null || pubProdTeams.size() == 0) {
					pubProdCrowd.setId(RandomUtil.getUUID());
					pubProdCrowd.setCrowdName(pubProdGroupDto.getGroupName());
					pubProdCrowd.setCrowdNo(pubProdGroupDto.getGroupNo());
					pubProdCrowd.setProdNo(pubProdGroupDto.getProdNo());
					pubProdCrowdService.insert(pubProdCrowd);
				}
			}
		}

	}

	/**
	 * 删除产品和群组关系
	 */
	@Override
	@Transactional
	public void removePubProdGroup(List<PubProdGroupDto> prodGroupDtos) {
		for (PubProdGroupDto pubProdGroupDto : prodGroupDtos) {
			if (PubBusinessConstant.SALETYPE_CROWD.equals(pubProdGroupDto.getProperty())) {
				pubProdCrowdService.deleteByPrimaryKey(pubProdGroupDto.getId());
			}
			if (PubBusinessConstant.SALETYPE_GROUP.equals(pubProdGroupDto.getProperty())) {
				pubProdGroupService.deleteByPrimaryKey(pubProdGroupDto.getId());
			}
		}

	}

	/**
	 * 检查产品跟还款类型关系
	 */
	public boolean queryProdRepayType(String confNo) {

		Map<String, Object> param = new HashMap<>();
		param.put("confNo", confNo);
		List<PubProdRepayTyp> list = pubProdRepayTypService.queryForList(param);
		if (list == null || list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检查产品跟还款方式关系
	 */
	public boolean queryProdRepayKind(String repayKind) {
		Map<String, Object> param = new HashMap<>();
		param.put("repayKind", repayKind);
		List<PubProd> list = pubProdService.queryForList(param);
		if (list == null || list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 通过费用号查询 PUB_产品与费用项关系
	 * 
	 * @param primaryKey
	 * @return 返回true可以删除 费用项 false则不可以删除 说明费用项和已有产品有关系
	 */
	public boolean queryExistRelaProdAndFee(String feeNo) {
		return pubProdFeeService.queryExistRelaProdAndFee(feeNo);
	}

}
