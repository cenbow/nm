package com.hs.loan.collection.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.collection.entity.AccCustAndBankBo;
import com.hs.loan.collection.entity.AppLoanAcctBo;
import com.hs.loan.collection.entity.AppLoanCustInfoBo;
import com.hs.loan.collection.entity.PlLoanOvduCase;
import com.hs.loan.collection.entity.PlLoanOvduCaseBo;
import com.hs.loan.collection.entity.PlLoanOvduCaseRetAndFlowBo;
import com.hs.loan.collection.mapper.PlLoanOvduCaseMapper;

/**
 * PL_逾期案件 业务处理
 * 
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly = true)
public class PlLoanOvduCaseService {
	@Autowired
	private PlLoanOvduCaseMapper plLoanOvduCaseMapper;

	/**
	 * 新增 PL_逾期案件
	 * 
	 * @param plLoanOvduCase
	 *            新增对象
	 */
	@Transactional
	public void insert(PlLoanOvduCase plLoanOvduCase) {
		plLoanOvduCaseMapper.insert(plLoanOvduCase);
	}

	/**
	 * 通过主键修改 PL_逾期案件
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		plLoanOvduCaseMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_逾期案件
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		plLoanOvduCaseMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PL_逾期案件 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return PL_逾期案件对象
	 */
	public PlLoanOvduCase getByPrimaryKey(String primaryKey) {
		return plLoanOvduCaseMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_逾期案件 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOvduCase> queryForList(Map<String, Object> param) {
		return plLoanOvduCaseMapper.queryForList(param);
	}

	/**
	 * 按登录权限查询 PL_逾期案件 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOvduCase> queryForPermissonList(Map<String, Object> param, UserProfile profile) {
		param.put("staffNo", profile.getStaffNo());
		param.put("orgNo", profile.getOrgNo());
		param.put("roleNos", profile.getRoleNoSet());
		return this.queryForList(param);
	}

	/**
	 * 查询 PL_逾期案件 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOvduCase> queryForPage(Page<PlLoanOvduCase> page) {
		plLoanOvduCaseMapper.queryForList(page.getPageParams());
		return (Page<PlLoanOvduCase>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询 PL_逾期案件 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOvduCase> queryForPermissionPage(Page<PlLoanOvduCase> page, UserProfile profile) {
		return this.queryForPage(page);
	}

	/**
	 * 查询 PL_逾期案件 分页列表根据权限和条件(案件处理)
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOvduCaseBo> queryForParam(Page<PlLoanOvduCaseBo> page, UserProfile profile) {
		Map<String, Object> param = page.getPageParams();
		Set<String> roles = profile.getRoleNoSet();
		if (param.get("ovduLevel") != null) {
			if (Integer.parseInt(param.get("ovduLevel").toString()) < 4) {
				param.put("ovduLev", param.get("ovduLevel"));
			} else {
				param.put("ovduLevBig", param.get("ovduLevel"));
			}
		}
		param.put("staffNo", "00000");
		for (String role : roles) {
			if (PubBusinessConstant.ROLE_R_SYS_SUPER.equals(role)) {
				param.put("staffNo", "");
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_MGR.equals(role)) {
				param.put("staffNo", "");
				break;
			} else if ("r_collec_teamM1".equals(role)) {// 催收组长M1
				param.put("staffNo", "");
				param.put("ovduLev", 1);
				break;
			} else if ("r_collec_teamM2".equals(role)) {// 催收组长M2
				param.put("staffNo", "");
				param.put("ovduLev", 2);
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_STAFF.equals(role)) {
				param.put("staffNo", profile.getStaffNo());
				break;
			}
		}
		if (param.get("bgnDate") != null) {
			param.put("bgnDate", param.get("bgnDate").toString().replaceAll("-", ""));
		}
		if (param.get("endDate") != null) {
			param.put("endDate", param.get("endDate").toString().replaceAll("-", ""));
		}
		if (param.get("dealStat") == null) {
			param.put("dealStatFlg", PubBusinessConstant.COLLECTION_SX);
		}
		if (param.get("collectionState") != null) {
			if ("0".equals(param.get("collectionState"))) {
				param.put("collectionStateNot", PubBusinessConstant.COLLECTION_SX);
			} else if ("1".equals(param.get("collectionState"))) {
				param.put("collectionStateYes", PubBusinessConstant.COLLECTION_SX);

			}
		}

		plLoanOvduCaseMapper.queryForParam(param);
		return (Page<PlLoanOvduCaseBo>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 根据贷款编号查询客户和银行信息
	 * 
	 * @param loanNo
	 * @return
	 */
	public AccCustAndBankBo getCustAndBank(String loanNo) {
		return plLoanOvduCaseMapper.getCustAndBank(loanNo);
	}

	/**
	 * 查询 PL_逾期案件 分页列表根据权限和条件（案件分配）
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOvduCaseBo> queryCaseAllotForParam(Page<PlLoanOvduCaseBo> page, UserProfile profile) {
		// TODO Auto-generated method stub
		Map<String, Object> param = page.getPageParams();
		if (param.get("dealStat") == null) {
			param.put("dealStatFlg", PubBusinessConstant.COLLECTION_SX);
		}
		if (param.get("bgnDate") != null) {
			param.put("bgnDate", param.get("bgnDate").toString().replaceAll("-", ""));
		}
		if (param.get("endDate") != null) {
			param.put("endDate", param.get("endDate").toString().replaceAll("-", ""));
		}
		if (param.get("collectionState") != null) {
			if ("0".equals(param.get("collectionState"))) {
				param.put("collectionStateNot", PubBusinessConstant.COLLECTION_SX);
			} else if ("1".equals(param.get("collectionState"))) {
				param.put("collectionStateYes", PubBusinessConstant.COLLECTION_SX);
			}
		}
		if (param.get("ovduLevel") != null) {
			if (Integer.parseInt(param.get("ovduLevel").toString()) < 4) {
				param.put("ovduLev", param.get("ovduLevel"));
			} else {
				param.put("ovduLevBig", param.get("ovduLevel"));
			}
		}
		Set<String> roles = profile.getRoleNoSet();
		for (String role : roles) {
			if ("r_collec_teamM1".equals(role)) {// 催收组长M1
				param.put("ovduLev", 1);
				break;
			} else if ("r_collec_teamM2".equals(role)) {// 催收组长M2
				param.put("ovduLev", 2);
				break;
			}
		}
		plLoanOvduCaseMapper.queryForParam(param);
		return (Page<PlLoanOvduCaseBo>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询 PL_逾期案件 分页列表根据权限和条件（案件分配）
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public List<PlLoanOvduCaseBo> queryCaseAllotListForParam(Map<String, Object> param, UserProfile profile) {
		// TODO Auto-generated method stub
		if (param.get("ovduLevel") != null) {
			if (Integer.parseInt(param.get("ovduLevel").toString()) < 4) {
				param.put("ovduLev", param.get("ovduLevel"));
			} else {
				param.put("ovduLevBig", param.get("ovduLevel"));
			}
		}
		param.put("staffNo", "00000");
		for (String role : profile.getRoleNoSet()) {
			if (PubBusinessConstant.ROLE_R_SYS_SUPER.equals(role)) {
				param.put("staffNo", "");
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_MGR.equals(role)) {
				param.put("staffNo", "");
				break;
			} else if ("r_collec_teamM1".equals(role)) {// 催收组长M1
				param.put("staffNo", "");
				param.put("ovduLev", 1);
				break;
			} else if ("r_collec_teamM2".equals(role)) {// 催收组长M2
				param.put("staffNo", "");
				param.put("ovduLev", 2);
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_STAFF.equals(role)) {
				param.put("staffNo", profile.getStaffNo());
				break;
			}
		}
		if (param.get("bgnDate") != null) {
			param.put("bgnDate", param.get("bgnDate").toString().replaceAll("-", ""));
		}
		if (param.get("endDate") != null) {
			param.put("endDate", param.get("endDate").toString().replaceAll("-", ""));
		}
		if (param.get("dealStat") == null) {
			param.put("dealStatFlg", PubBusinessConstant.COLLECTION_SX);
		}
		if (param.get("collectionState") != null) {
			if ("0".equals(param.get("collectionState"))) {
				param.put("collectionStateNot", PubBusinessConstant.COLLECTION_SX);
			} else if ("1".equals(param.get("collectionState"))) {
				param.put("collectionStateYes", PubBusinessConstant.COLLECTION_SX);

			}
		}
		return plLoanOvduCaseMapper.queryForParam(param);
	}

	/**
	 * 查询商品信息
	 * 
	 * @param loanNo
	 * @return
	 */
	public AppLoanAcctBo getAppLoanAcctBo(String loanNo) {
		return plLoanOvduCaseMapper.getAppLoanAcctBo(loanNo);
	}

	/**
	 * 查询客户相关联系人信息
	 * 
	 * @param param
	 * @return
	 */
	public List<AppLoanCustInfoBo> queryLoanCustInfoForList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return plLoanOvduCaseMapper.queryLoanCustInfoForList(param);
	}

	public Page<PlLoanOvduCaseRetAndFlowBo> queryLoanOvduCaseFlowPage(Page<PlLoanOvduCaseRetAndFlowBo> page) {
		// TODO Auto-generated method stub
		plLoanOvduCaseMapper.queryLoanOvduCaseFlowList(page.getPageParams());
		return (Page<PlLoanOvduCaseRetAndFlowBo>) page.getPageParams().get(Page.KEY);
	}

	public List<PlLoanOvduCaseRetAndFlowBo> queryLoanOvduCaseFlowList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return plLoanOvduCaseMapper.queryLoanOvduCaseFlowList(param);
	}

	public void updateList(List<Map<String, Object>> listCase) {
		// TODO Auto-generated method stub
		for (Map<String, Object> param : listCase) {
			this.updateByPrimaryKeySelective(param);
		}
	}

}