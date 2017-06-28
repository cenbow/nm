package com.hs.system.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysStaff;


public class BeanUtil 
{
	
	private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);
	
	/** 
     * 根据List<Map<String, Object>>数据转换为JavaBean数据 
     * @param datas 
     * @param beanClass 
     * @return 
     * @throws CommonException 
     */  
    public static <T> List<T> ListMap2JavaBean(List<Map<String, Object>> datas, Class<T> beanClass) 
    {  
        // 返回数据集合  
        List<T> list = null;  
        // 对象字段名称  
        String fieldname = null;  
        // 对象方法名称  
        String methodname = null;  
        // 对象方法需要赋的值  
        Object methodsetvalue = null; 
        T t = null;
        Class<?>[] clazz = null;
        try {  
            list = new ArrayList<T>();  
            // 得到对象所有字段  
            Method[] methods = beanClass.getDeclaredMethods();  
            // 遍历数据  
            for (Map<String, Object> mapdata : datas) {  
            	//key全部大写存放，特殊情况会从这里去取
            	Map<String,Object> upperCaseMap = new HashMap<String, Object>();
            	for(Entry<String, Object> en:mapdata.entrySet()){
            		upperCaseMap.put(en.getKey().toUpperCase(), en.getValue());
            	}
            	
                // 创建一个泛型类型实例  
                t = beanClass.newInstance();  
                // 遍历所有字段，对应配置好的字段并赋值  
                for (Method method : methods) {
                	methodname = method.getName();
                	if(!methodname.startsWith("set")) {
                		continue;
                	}
                	if(methodname.length() > 3 && methodname.length() == 4) {
                		fieldname = methodname.substring(3,4).toLowerCase();
                	} else if (methodname.length() > 4) {
                		fieldname = methodname.substring(3,4).toLowerCase() + methodname.substring(4);
                	} else {
                		continue;
                	}
           
                	methodsetvalue = mapdata.get(fieldname);
//                	logger.info("-------------------------------------------------------------缓冲redis1");
                	if(methodsetvalue == null && fieldname!=null) {
//                		logger.info("-------------------------------------------------------------缓冲redis2");
                		String _fieldname = fieldname.toUpperCase();
                		methodsetvalue = upperCaseMap.get(_fieldname);
//                		logger.info("-------------------------------------------------------------缓冲redis3 _fieldname="+_fieldname+" methodsetvalue="+methodsetvalue);
                	}
//                	logger.info("-------------------------------------------------------------缓冲redis4");
                	if(methodsetvalue == null) {
                		continue;
                	}
                	clazz = method.getParameterTypes();
                	if(clazz.length == 0) {
                		continue;
                	}
                	if(methodsetvalue instanceof Date && Date.class == clazz[0]){
                		method.invoke(t, methodsetvalue);
                		continue;
                	}
                	if(methodsetvalue.getClass() == clazz[0]) {
                		method.invoke(t, methodsetvalue);
                	}
                }
                // 存入返回列表  
//                if(t instanceof SysStaff){
//                	SysStaff s = (SysStaff) t;
//                	System.out.println(s.getStaffName()+"--s.getStaffAutName()="+s.getStaffAutName()+"--s.getStaffAutName()="+s.getStaffAutPwd());
//                }
                list.add(t);
            }  
        } catch (Exception e) { 
        	logger.error(e.getMessage(), e);
        	throw new ServiceException(e.getMessage(),e);
        } 
            
        // 返回  
        return list;  
    }  
}
