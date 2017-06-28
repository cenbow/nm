package com.hs.system.sqe;

import org.hibernate.validator.constraints.NotBlank;

/**
 * SYS_系统序号表 业务处理
 * @author jqiu
 * @create 2015-11-04
 */
public interface  SysSqeApi{
	/**
	 * 获取下一编号
	 * @param sqeCode	编号类型
	 * @param changeKey 重新记数标记
	 * @return 下一编号
	 */
	public String invokeGetNextSqe(@NotBlank String sqeCode,String changeKey);
	
}