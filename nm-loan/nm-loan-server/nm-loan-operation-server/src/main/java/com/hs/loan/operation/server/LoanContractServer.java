package com.hs.loan.operation.server;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.operation.api.LoanContractApi;
import com.hs.loan.operation.dto.LoanContractFileDto;
import com.hs.loan.operation.entity.PubLoanContractFile;
import com.hs.loan.operation.service.PubLoanContractFileService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly=true)
public class LoanContractServer implements LoanContractApi {
	public static final Logger log = LoggerFactory.getLogger(LoanContractServer.class);
	@Autowired
	private PubLoanContractFileService pubLoanContractFileService;

	/**
	 * 信托文件导出数据
	 * @param map dataFormat 日期
	 * @return List<HashMap<String,Object>>  fileName文件名 in输入流
	 * @throws ServiceException
	 * @throws AppException
     */
	@Transactional
	public List<HashMap<String,Object>> downEntr(java.util.Map map)throws ServiceException,AppException{
		if(!this.isDownEntr(map)){
			throw new ServiceException("当天无数据导出");
		}
		String dataFormat= null;
		try {
			dataFormat = DateFormatUtils.format(org.apache.commons.lang.time.DateUtils.parseDate(map.get("dataFormat").toString(),new String[]{"yyyyMMdd","yyyy-MM-dd","yyyy/MM/dd","yyyy年MM月dd"}),"yyyyMMdd");
		} catch (ParseException e) {
			throw new ServiceException("日期格式不支持");
		}
		if(null==dataFormat||"".equals(dataFormat.trim())){
			throw new ServiceException("下载日期为空!");
		}
		map.put("dataFormat",dataFormat);
		List<HashMap<String,Object>> list=new ArrayList<>();
		List<String> sendLoanApply = pubLoanContractFileService.getSendLoanApply(map);
        if(null!=sendLoanApply&&!sendLoanApply.isEmpty()){
			StringBuilder stringBuilder = new StringBuilder();
			for (String string : sendLoanApply) {
				stringBuilder.append(string).append("\n");
			}
			//ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));
			HashMap<String,Object> sendLoanApplyMap=new HashMap<>();
			sendLoanApplyMap.put("fileName",new StringBuilder().append(dataFormat).append("_052_01_LoanApply.txt").toString());
			sendLoanApplyMap.put("in",stringBuilder.toString());
			list.add(sendLoanApplyMap);
		}
		List<String> sendLoanResult = pubLoanContractFileService.getSendLoanResult(map);
		if(null!=sendLoanResult&&!sendLoanResult.isEmpty()){
			StringBuilder stringBuilder = new StringBuilder();
			for (String string : sendLoanResult) {
				stringBuilder.append(string).append("\n");
			}
			//ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));
			HashMap<String,Object> sendLoanResultMap=new HashMap<>();
			sendLoanResultMap.put("fileName",new StringBuilder().append(dataFormat).append("_052_01_LoanResult.txt").toString());
			sendLoanResultMap.put("in",stringBuilder.toString());
			list.add(sendLoanResultMap);
		}
		List<String> sendSuccessRepayment = pubLoanContractFileService.getSendSuccessRepayment(map);
		if(null!=sendSuccessRepayment&&!sendSuccessRepayment.isEmpty()){
			StringBuilder stringBuilder = new StringBuilder();
			for (String string : sendSuccessRepayment) {
				stringBuilder.append(string).append("\n");
			}
			//ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));
			HashMap<String,Object> sendSuccessRepaymentMap=new HashMap<>();
			sendSuccessRepaymentMap.put("fileName",new StringBuilder().append(dataFormat).append("_052_01_LoanApply.txt").toString());
			sendSuccessRepaymentMap.put("in",stringBuilder.toString());
			list.add(sendSuccessRepaymentMap);
		}
		return list;
	}

	/**
	 * 信托文件是否导出
	 * @param map dataFormat 日期
	 * @return boolean
	 * @throws ServiceException
	 * @throws AppException
     */
	@Transactional
	public boolean isDownEntr(java.util.Map map)throws ServiceException,AppException{
		if(null==map.get("dataFormat")||"".equals(map.get("dataFormat").toString().trim())){
			throw new ServiceException("下载日期为空!");
		}
		String dataFormat=map.get("dataFormat").toString();
		Date currentDate=new Date();
		currentDate= org.apache.commons.lang.time.DateUtils.addDays(currentDate,1);
		try {
			Date date = org.apache.commons.lang.time.DateUtils.parseDate(dataFormat, new String[]{"yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy年MM月dd"});
			map.put("dataFormat",DateFormatUtils.format(date,"yyyyMMdd"));
			if(date.getTime()>currentDate.getTime()){
				throw new ServiceException("下载日期不能超过今天");
			}
		} catch (ParseException e) {
			throw new ServiceException("日期格式不支持");
		}
		boolean result =false;
		List<String> sendLoanApply = pubLoanContractFileService.getSendLoanApply(map);
		List<String> sendLoanResult = pubLoanContractFileService.getSendLoanResult(map);
		List<String> sendSuccessRepayment = pubLoanContractFileService.getSendSuccessRepayment(map);
		if((null!=sendLoanApply&&!sendLoanApply.isEmpty())||(null!=sendLoanResult&&!sendLoanResult.isEmpty())||(null!=sendSuccessRepayment&&!sendSuccessRepayment.isEmpty())){
			result=true;
		}
		return result;
	}
	@Transactional
	public int insertAppEntr(String context)throws ServiceException, AppException{
		List<HashMap<String,Object>> list=new ArrayList<>();
		if(null==context||context.isEmpty()){
			throw new ServiceException("上传内容不能为空");
		}
		String[] row = context.split("\n");
		if(null==row){
			throw new ServiceException("上传内容格式不正确");
		}
		for (String r : row) {
			HashMap<String,Object> entr=new HashMap<>();
			String[] split = r.split("\\+");
			if(null==split||split.length!=7){
				throw new ServiceException("上传内容格式不正确");
			}
			if(null==split||split.length!=7){
				break;
			}
			//机构代码
			String instNo = split[0].substring(0, split[0].length() - 1);
			String loanNo = split[1].substring(1, split[1].length() - 1);
			//放款结果
			String entrResulr = split[2].substring(1, split[2].length() - 1);
			if("10".equals(entrResulr)){
				entrResulr="未处理";
			}else if("20".equals(entrResulr)){
				entrResulr="正在处理";
			}else if("30".equals(entrResulr)){
				entrResulr="放款成功";
			}else if("40".equals(entrResulr)){
				entrResulr="放款失败";
			}
			//银行回盘时间
			String ctofDate = split[3].substring(1, split[3].length() - 1);
			//失败原因
			String failReason = split[4].substring(1, split[4].length() - 1);
			//审批结果
			String apprResult = split[5].substring(1, split[5].length() - 1);
			if("01".equals(apprResult)){
				apprResult="审批通过";
			}else if("02".equals(apprResult)){
				apprResult="审批拒绝";
			}
			String refuReason=null;
			if("02".equals(apprResult)){
				//审批拒绝原因
				refuReason = split[6].substring(1, split[6].length());
			}
			entr.put("id",RandomUtil.getUUID());
			entr.put("loanNo",loanNo);
			entr.put("entrResulr",entrResulr);
			entr.put("ctofDate",ctofDate);
			entr.put("failReason",failReason);
			entr.put("apprResult",apprResult);
			entr.put("refuReason",refuReason);
			entr.put("entrDate",new Date());
			list.add(entr);
		}
		java.util.Map map=new HashMap();
		map.put("list",list);
		return pubLoanContractFileService.insertAppEntr(map);
	};
	public Page<HashMap<String,Object>> getAppEntr(java.util.Map map){
		Object ctofObj=map.get("CTOF_DATE");
		Object entrDate = map.get("entrDate");if(null!=ctofObj){
			try {
				Date date = org.apache.commons.lang.time.DateUtils.parseDate(ctofObj.toString(), new String[]{"yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd"});
				date = org.apache.commons.lang.time.DateUtils.setHours(date, 0);
				date= org.apache.commons.lang.time.DateUtils.setMinutes(date, 0);
				date= org.apache.commons.lang.time.DateUtils.setSeconds(date, 0);
				String ctofStart= DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
				date = org.apache.commons.lang.time.DateUtils.setHours(date, 23);
				date= org.apache.commons.lang.time.DateUtils.setMinutes(date, 59);
				date= org.apache.commons.lang.time.DateUtils.setSeconds(date,59);
				String ctofEnd= DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
				map.put("ctofStart",ctofStart);
				map.put("ctofEnd",ctofEnd);
			} catch (ParseException e) {
				log.error(e.getMessage(),e);
			}
		}
		if(null!=entrDate){
			try {
				Date date = org.apache.commons.lang.time.DateUtils.parseDate(entrDate.toString(), new String[]{"yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd"});
				date = org.apache.commons.lang.time.DateUtils.setHours(date, 0);
				date= org.apache.commons.lang.time.DateUtils.setMinutes(date, 0);
				date= org.apache.commons.lang.time.DateUtils.setSeconds(date, 0);
				String ctofStart= DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
				date = org.apache.commons.lang.time.DateUtils.setHours(date, 23);
				date= org.apache.commons.lang.time.DateUtils.setMinutes(date, 59);
				date= org.apache.commons.lang.time.DateUtils.setSeconds(date,59);
				String ctofEnd= DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
				map.put("entrStart",ctofStart);
				map.put("entrEnd",ctofEnd);
			} catch (ParseException e) {
				log.error(e.getMessage(),e);
			}
		}
		Page<HashMap<String,Object>> page=new Page<>();
		page.setParams(map);
		Page<HashMap<String, Object>> appEntr = pubLoanContractFileService.getAppEntr(page);
		for (HashMap<String, Object> hashMap : appEntr.getList()) {
			Object entr_date = hashMap.get("ENTR_DATE");
			if(null!=entr_date){
				hashMap.remove("ENTR_DATE");
					Date date = (Date) entr_date;
					String format = DateFormatUtils.format(date, "yyyy-MM-dd");
					hashMap.put("ENTR_DATE", format);
			}
			Object ctof_date = hashMap.get("CTOF_DATE");
			if(null!=ctof_date){
				hashMap.remove("CTOF_DATE");
					Date date = (Date) ctof_date;
					String format = DateFormatUtils.format(date, "yyyy-MM-dd");
					hashMap.put("CTOF_DATE", format);
			}
		}
		return appEntr;
	}
	@Override
	@Transactional
	public void save(LoanContractFileDto loanContractFileDto) throws ServiceException, AppException {
		PubLoanContractFile pubLoanContractFile = new PubLoanContractFile();
		BeanUtils.copyPropertiesNotForce(pubLoanContractFile, loanContractFileDto);
		if(StringUtils.isEmpty(pubLoanContractFile.getFileId())){
			pubLoanContractFile.setInstDate(DateUtils.getCurrentDate());
			pubLoanContractFile.setFileId(RandomUtil.getUUID());
			pubLoanContractFileService.insert(pubLoanContractFile);
		}else{
			Map<String,Object> map = BeanUtils.bean2map(pubLoanContractFile);
			map.put("updtDate", DateUtils.getCurrentDate());
			pubLoanContractFileService.updateByPrimaryKeySelective(map);
		}
	}

	@Override
	@Transactional
	public void deleteByNo(String fileId) throws ServiceException, AppException {
		pubLoanContractFileService.deleteByPrimaryKey(fileId);
	}

	@Override
	public LoanContractFileDto getByNo(String fileId) throws ServiceException, AppException {
		LoanContractFileDto dto = new LoanContractFileDto();
		PubLoanContractFile contractFile = pubLoanContractFileService.getByPrimaryKey(fileId);
		return (LoanContractFileDto) BeanUtils.copyPropertiesNotForce(dto, contractFile);
	}

	@Override
	public List<LoanContractFileDto> getList(String chanNo) throws ServiceException, AppException {
		if(StringUtils.isEmpty(chanNo)){
			throw new ServiceException("渠道信息为空");
		}
		Map<String,Object> param =new HashMap<>();
		param.put("chanNo", chanNo);
		 List<PubLoanContractFile> list = pubLoanContractFileService.queryForList(param);
		return BeanUtils.copyProperties(list, LoanContractFileDto.class);
	}
	
	/**
	 * 根据渠道和类型获取附件列表
	 */
	@Override
	public List<LoanContractFileDto> queryContractListByType(String fileTyp,String chanNo){
		if(StringUtils.isEmpty(chanNo)){
			throw new ServiceException("渠道信息为空");
		}
		if(StringUtils.isEmpty(fileTyp)){
			throw new ServiceException("附件类型为空");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("fileTyp", fileTyp);
		map.put("chanNo", chanNo);
		List<PubLoanContractFile> list = pubLoanContractFileService.queryForList(map);
		return BeanUtils.copyProperties(list, LoanContractFileDto.class);
	}

}
