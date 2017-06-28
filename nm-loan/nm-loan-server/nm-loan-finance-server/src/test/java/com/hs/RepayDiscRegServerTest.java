package com.hs;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.finance.dto.AccLoanPlanDto;
import com.hs.loan.finance.dto.AccRepayDiscRegInfoDto;
import com.hs.loan.finance.server.RepayDiscRegServer;

public class RepayDiscRegServerTest extends BaseTest {

	@Autowired
	private RepayDiscRegServer repayDiscRegServer;

	// @Test
	// public void test() {
	// try {
	// Page<AccRepayDiscRegDto> page = new Page<AccRepayDiscRegDto>();
	// page.setPageNo(1);
	// page.setPageSize(20);
	// page = repayDiscRegServer.queryForPage(page);
	// System.out.println("Page List Size = " + page.getList().size());
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// }
	// @Test
	// public void test1() {
	// AccRepayDiscRegDto dto = new AccRepayDiscRegDto();
	// dto.setId("2");
	// dto.setDiscAmt(new BigDecimal("1000"));
	// dto.setDiscDate(new Date());
	// dto.setDiscType("滞纳金"); // 减免类型
	// dto.setInstDate(new Date());
	// dto.setLoanNo("001001108022016031400001");
	// dto.setRepayDate("1111");
	// dto.setSetlFlag("否");
	// dto.setTranOrg("admin");
	// dto.setTranStaff("admin");
	// dto.setUpdtDate(new Date());
	//
	// try {
	// repayDiscRegServer.insert(dto);
	// System.out.println("==========插入减免成功==========");
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	//
	// }
	// @Test
	// public void test2() {
	// AccRepayDiscRegDto dto = repayDiscRegServer.getByPrimaryKey("1");
	//
	// dto.setDiscAmt(new BigDecimal("10000"));
	// try {
	// Map<String, Object> map = toMap(dto);
	// map.put("instDate", new Date());
	// map.put("updtDate", new Date());
	// map.put("discDate", new Date());
	// repayDiscRegServer.updateByPrimaryKeySelective(map);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// @Test
	// public void test3() {
	// repayDiscRegServer.deleteByPrimaryKey("2");
	//
	// }
	@Test
	public void test3() {
		Page<AccRepayDiscRegInfoDto> page = new Page<AccRepayDiscRegInfoDto>();
		page.setPageNo(1);
		page.setPageSize(20);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", "002002001022016030800022");
		page.setParams(map);
		try {
			Page<AccRepayDiscRegInfoDto> list = repayDiscRegServer.queryForListByInstDate(page);
			System.out.println(list.getList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public Map<String, Object> toMap(Object javaBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					Object value = method.invoke(javaBean, (Object[]) null);
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {
			}
		}

		return result;
	}
}
