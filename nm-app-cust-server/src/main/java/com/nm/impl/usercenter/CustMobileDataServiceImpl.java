package com.nm.impl.usercenter;

import com.hs.utils.StringUtils;
import com.nm.base.framework.core.validate.Validator;
import com.nm.mapper.usercenter.CustMoblieDataMapper;
import com.nm.service.usercenter.CustMobileDataService;
import com.nm.util.EmojiHandle;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/5/24.
 */
@Service
public class CustMobileDataServiceImpl implements CustMobileDataService{

    @Resource
    private CustMoblieDataMapper custMoblieDataMapper;
    @Override
    public void addCustPhoneBook(String custNo, String phoneKey, String phoneBook) {
        Validator.init(custNo, "客户编号").required().end();
        Map rqMap=new HashMap();
        if(StringUtils.isNotBlank(phoneBook) && StringUtils.isNotBlank(phoneKey)){
            rqMap.put("custNo",custNo);
            rqMap.put("phoneKey",phoneKey);
            //rqMap.put("phoneBook",phoneBook);
            Integer ret=custMoblieDataMapper.getCustPhoneBook(rqMap);
            try {
                rqMap.put("phoneBook",EmojiHandle.resolveToByteFromEmoji(phoneBook));
                if(ret<1){
                    custMoblieDataMapper.addCustPhoneBook(rqMap);
                }else{
                    custMoblieDataMapper.updateCustPhoneBook(rqMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
