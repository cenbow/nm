package com.hs.loan.market.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.market.bo.PubBranchBo;
import com.hs.loan.market.entity.PubBranch;

/**
 * PUB_网点信息 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubBranchMapper extends BaseMapper<PubBranch>{

	public List<PubBranch> queryNotInGrpBrhList(Map<String, Object> map);

	//获取销售绑定的网点
	public List<PubBranch> getSalerBindedBranchList(Map<String, Object> param);

	public List<PubBranchBo> queryList(Map<String, Object> pageParams);

	/**
	 * 根据网点名称，账户名称，账户号查询是否已经存在该网点
	 * @param pubBranch(branchName acctNo acctName)
	 * @return
	 */
	public int selectExistPubBranch(PubBranch pubBranch);

	/**
	 * 查询网点组不包含的网点信息列表
	 * @param map
	 * @return
	 */
	public List<PubBranchBo> queryBranchGroupNotExists(Map map);

	/**
	 * 通过产品编号查询未与产品关联的网点列表
	 * @param proNo 产品编号
	 * @return
	 */
	public List<PubBranchBo> queryBranchListByProdNo(Map map);

	/**
	 * 通过网点编号查询该网点是否存在
	 * @param pubBranch PUB_网点信息 对象
	 * @return
	 */
	public int selectExistsPubBranchByBranchNo(PubBranch pubBranch);

	/**
	 * 查询禁用状态已经存在的网点
	 * @param pubBranch 网点实体
	 * @return
	 */
	public List<String> selectExistsDisableState(PubBranch pubBranch);
	/**
	 * 获取挂单商户失效条数
	 * @param branchName
	 * @return
	 */
	public List<Map<String, Object>> judgeBranchStatusByFaild(String branchName);
	
}