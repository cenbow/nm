package com.hs.loan.market.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.market.entity.PubBranch;
import com.hs.loan.market.entity.PubBranchGroup;
import com.hs.loan.market.entity.PubBranchGroupInfo;
import com.hs.loan.market.mapper.PubBranchGroupInfoMapper;
import com.hs.loan.market.mapper.PubBranchGroupMapper;
import com.hs.loan.market.mapper.PubBranchMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_网点分组信息 业务处理
 * 
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly = true)
public class PubBranchGroupInfoService {
	// 网点组mapper
	@Autowired
	private PubBranchGroupInfoMapper pubBranchGroupInfoMapper;
	// 网点组和网点关联表mapper
	@Autowired
	private PubBranchGroupMapper pubBranchGroupMapper;
	// 网点mapper
	@Autowired
	private PubBranchMapper pubBranchMapper;

	/**
	 * 新增 PUB_网点分组信息
	 * 
	 * @param pubBranchGroupInfo
	 *            新增对象
	 */
	@Transactional
	public void insert(PubBranchGroupInfo pubBranchGroupInfo) {
		pubBranchGroupInfoMapper.insert(pubBranchGroupInfo);
	}

	/**
	 * 通过主键修改 PUB_网点分组信息
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		pubBranchGroupInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_网点分组信息
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		pubBranchGroupInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_网点分组信息 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return PUB_网点分组信息对象
	 */
	public PubBranchGroupInfo getByPrimaryKey(String primaryKey) {
		return pubBranchGroupInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_网点分组信息 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<PubBranchGroupInfo> queryForList(Map<String, Object> param) {
		return pubBranchGroupInfoMapper.queryForList(param);
	}

	/**
	 * 查询 PUB_网点分组信息 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<PubBranchGroupInfo> queryForPage(Page<PubBranchGroupInfo> page) {
		pubBranchGroupInfoMapper.queryForList(page.getPageParams());
		return (Page<PubBranchGroupInfo>) page.getPageParams().get(Page.KEY);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 保存或更新 网点组基本信息
	 */
	@Transactional
	public void save(PubBranchGroupInfo pubBranchGroupInfo) throws ServiceException {
		Date inserDate = pubBranchGroupInfo.getInstDate();
		if (inserDate != null) {
			// 更新
			Map<String, Object> param = BeanUtils.bean2map(pubBranchGroupInfo);
			param.put("updtDate", new Date());
			updateByPrimaryKeySelective(param);
		} else {
			Map<String, Object> param = new HashMap<>();
			param.put("groupNo", pubBranchGroupInfo.getGroupNo());
			List<PubBranchGroupInfo> lst = queryForList(param);
			if (lst != null && lst.size() > 0) {
				throw new ServiceException("网点组编号已存在！");
			}
			// 保存
			pubBranchGroupInfo.setInstDate(new Date());
			insert(pubBranchGroupInfo);
		}
	}

	/**
	 * 保存或跟新 网点组和网点，(关联网点组和网点) 根据网点组的id判断是更新还是保存
	 * 
	 */
	@Transactional
	public void saveBrhGrp(PubBranchGroupInfo pubBranchGroupInfo, List<String> branchNoLst) {
		// 先保存或者更新网点组
		save(pubBranchGroupInfo);
		saveBrhGrpRel(pubBranchGroupInfo.getGroupNo(), branchNoLst);
	}

	/**
	 * 通过网点组编码 获取 网点组的基本信息
	 */
	public PubBranchGroupInfo getByNo(String groupNo) {
		return getByPrimaryKey(groupNo);
	}

	/**
	 * 查询网点组list
	 * 
	 * @param param
	 * @return
	 */
	public List<PubBranchGroupInfo> getList(Map<String, Object> param) {
		return queryForList(param);
	}

	/**
	 * 分页查询网点组
	 * 
	 * @param page
	 * @return
	 */
	public Page<PubBranchGroupInfo> queryBrhGrp(Page<PubBranchGroupInfo> page) {
		return queryForPage(page);
	}

	/**
	 * 获取网点组包含的 网点 list
	 * 
	 * @param groupNo
	 * @return
	 */
	public List<PubBranch> getBranchLst(String groupNo) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupNo", groupNo);
		param.put("stat", CommonConstant.STAT_ENABLE);
		return pubBranchMapper.queryForList(param);
	}

	/**
	 * 分页查询 网点组包含的 网点 参数必须包含groupNo
	 * 
	 * @param groupNo
	 * @return
	 */
	public Page<PubBranch> queryBranchPage(Page<PubBranch> page) {
		if (StringUtils.isEmpty((String) page.getParams().get("groupNo"))) {
			throw new AppException("groupNo不可为空");
		}
		page.getParams().put("stat", CommonConstant.STAT_ENABLE);
		pubBranchMapper.queryForList(page.getPageParams());
		return (Page<PubBranch>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 分页查询 未在该网点组 下的网点 必须的参数 groupNo
	 * 
	 * @param page
	 * @return
	 */
	public Page<PubBranch> queryNotInGrpBrhPage(Page<PubBranch> page) {
		if (StringUtils.isEmpty((String) page.getParams().get("groupNo"))) {
			throw new AppException("groupNo不可为空");
		}
		page.getParams().put("stat", CommonConstant.STAT_ENABLE);
		pubBranchMapper.queryNotInGrpBrhList(page.getPageParams());
		return (Page<PubBranch>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 通过groupNo删除网点组，同时会删除网点组和网点的关联关系
	 */
	@Transactional
	public void deleteByNo(String groupNo) {
		if (StringUtils.isEmpty(groupNo)) {
			throw new AppException("groupNo不可为空");
		}
		// 删除网点组
		deleteByPrimaryKey(groupNo);
		// 删除网点组和网点的关联关系
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupNo", groupNo);
		pubBranchGroupMapper.rmvBrhGrpRel(param);
	}

	/**
	 * 移除网点组和网点的关系
	 * 
	 * @param pubBranchGroup
	 */
	@Transactional
	public void rmvBrhGrpRel(String groupNo, List<String> branchNoLst) {
		if (StringUtils.isEmpty(groupNo)) {
			throw new AppException("groupNo不可为空");
		}
		if (branchNoLst == null || branchNoLst.size() == 0) {
			// throw new AppException("请选择网点后再移除");
			return;
		}
		for (String branchNo : branchNoLst) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("groupNo", groupNo);
			param.put("branchNo", branchNo);
			pubBranchGroupMapper.rmvBrhGrpRel(param);
		}
	}

	/**
	 * 保存或更新 网点组和网点的关系 list里的groupNo是必须的，且没项的groupNo应该且必须是相同的
	 * 
	 * @param pubBranchGroupLst
	 */
	@Transactional
	public void saveBrhGrpRel(String groupNo, List<String> branchNoLst) {
		if (StringUtils.isEmpty(groupNo)) {
			throw new AppException("groupNo不可为空");
		}
		// 保存新的关联关系
		if (branchNoLst == null || branchNoLst.size() == 0) {
			throw new AppException("网点不可为空");
		}
		for (String branchNo : branchNoLst) {
			// 删除原有的关联关系
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("groupNo", groupNo);
			param.put("branchNo", branchNo);
			pubBranchGroupMapper.rmvBrhGrpRel(param);

			// 建立新的关系
			PubBranchGroup pgb = new PubBranchGroup();
			pgb.setId(RandomUtil.getUUID());
			pgb.setGroupNo(groupNo);
			pgb.setBranchNo(branchNo);
			pubBranchGroupMapper.insert(pgb);
		}
	}

}