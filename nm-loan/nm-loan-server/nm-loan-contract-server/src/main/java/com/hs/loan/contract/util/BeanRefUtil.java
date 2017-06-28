package com.hs.loan.contract.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class BeanRefUtil
{
  public static Map<String, String> getFieldValueMap(Object bean)
  {
    if (bean == null) {
      return new HashMap();
    }
    Map valueMap = new HashMap();

    if ((bean instanceof Map)) {
      Map mapObj = (Map)bean;
      for (Object obj : mapObj.keySet()){
    	  if (obj != null)
              valueMap.put(obj.toString(), obj != null ? obj.toString() : "");
      }
    }
    else
    {
      Class cls = bean.getClass();
      valueMap = getFiedValueMap(cls, bean);

      Class clazz = bean.getClass().getSuperclass();
      if (clazz != Object.class) {
        valueMap.putAll(getFiedValueMap(clazz, bean));
      }

    }

    return valueMap;
  }

  public static <T> List<T> mapToBean(List<Map<String, Object>> maps, Class<T> cls)
  {
    List list = new ArrayList();
    if ((maps != null) && (!maps.isEmpty())) {
      for (Map map : maps) {
        try {
          Object t = cls.newInstance();
          setFieldValue(t, map);
          list.add(t);
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return list;
  }

  private static Map<String, String> getFiedValueMap(Class cls, Object bean) {
    Map valueMap = new HashMap();

    Method[] methods = cls.getDeclaredMethods();
    Field[] fields = cls.getDeclaredFields();

    for (Field field : fields)
      try {
        String fieldType = field.getType().getSimpleName();
        String fieldGetName = parGetName(field.getName());
        if (checkGetMet(methods, fieldGetName))
        {
          Method fieldGetMet = cls
            .getMethod(fieldGetName, new Class[0]);
          Object fieldVal = fieldGetMet.invoke(bean, new Object[0]);
          String result = null;
          if ("Date".equals(fieldType)) {
            result = fmtDate((Date)fieldVal);
          }
          else if (fieldVal != null) {
            result = String.valueOf(fieldVal);
          }

          valueMap.put(field.getName(), result);
        }
      }
      catch (Exception localException) {
      }
    return valueMap;
  }

  public static void setFieldValue(Object bean, Map<String, Object> valMap)
  {
    Class cls = bean.getClass();

    Method[] methods = cls.getDeclaredMethods();
    Field[] fields = cls.getDeclaredFields();

    for (Field field : fields)
      try
      {
        String fieldSetName = parSetName(field.getName());
        if (checkSetMet(methods, fieldSetName))
        {
          Method fieldSetMet = cls.getMethod(fieldSetName, new Class[] { field
            .getType() });
          String value = String.valueOf(valMap.get(field.getName()));
          if ((value != null) && (!"".equals(value))) {
            String fieldType = field.getType().getSimpleName();
            if ("String".equals(fieldType)) {
              fieldSetMet.invoke(bean, new Object[] { value });
            } else if ("Date".equals(fieldType)) {
              Date temp = parseDate(value);
              fieldSetMet.invoke(bean, new Object[] { temp });
            } else if (("Integer".equals(fieldType)) || 
              ("int".equals(fieldType))) {
              Integer intval = Integer.valueOf(Integer.parseInt(value));
              fieldSetMet.invoke(bean, new Object[] { intval });
            } else if ("Long".equalsIgnoreCase(fieldType)) {
              Long temp = Long.valueOf(Long.parseLong(value));
              fieldSetMet.invoke(bean, new Object[] { temp });
            } else if ("Double".equalsIgnoreCase(fieldType)) {
              Double temp = Double.valueOf(Double.parseDouble(value));
              fieldSetMet.invoke(bean, new Object[] { temp });
            } else if ("Boolean".equalsIgnoreCase(fieldType)) {
              Boolean temp = Boolean.valueOf(Boolean.parseBoolean(value));
              fieldSetMet.invoke(bean, new Object[] { temp });
            } else if ("Float".equalsIgnoreCase(fieldType)) {
              Float temp = Float.valueOf(Float.parseFloat(value));
              fieldSetMet.invoke(bean, new Object[] { temp });
            } else if ("BigDecimal".equalsIgnoreCase(fieldType)) {
              BigDecimal temp = new BigDecimal(value);
              fieldSetMet.invoke(bean, new Object[] { temp });
            } else {
              System.out.println("not supper type" + fieldType);
            }
          }
        }
      }
      catch (Exception localException)
      {
      }
  }

  public static Date parseDate(String datestr)
  {
    if ((datestr == null) || ("".equals(datestr)))
      return null;
    try
    {
      String fmtstr = null;
      if (datestr.indexOf(':') > 0) {
        fmtstr = "yyyy-MM-dd HH:mm:ss";
      }
      else {
        fmtstr = "yyyy-MM-dd";
      }
      SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
      return sdf.parse(datestr); } catch (Exception e) {
    }
    return null;
  }

  public static String fmtDate(Date date)
  {
    if (date == null)
      return null;
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", 
        Locale.US);
      return sdf.format(date); } catch (Exception e) {
    }
    return null;
  }

  public static boolean checkSetMet(Method[] methods, String fieldSetMet)
  {
    Method[] arrayOfMethod = methods; int j = methods.length; for (int i = 0; i < j; i++) { Method met = arrayOfMethod[i];
      if (fieldSetMet.equals(met.getName())) {
        return true;
      }
    }
    return false;
  }

  public static boolean checkGetMet(Method[] methods, String fieldGetMet)
  {
    Method[] arrayOfMethod = methods; int j = methods.length; for (int i = 0; i < j; i++) { Method met = arrayOfMethod[i];
      if (fieldGetMet.equals(met.getName())) {
        return true;
      }
    }
    return false;
  }

  public static String parGetName(String fieldName)
  {
    if ((fieldName == null) || ("".equals(fieldName))) {
      return null;
    }
    return "get" + fieldName.substring(0, 1).toUpperCase() + 
      fieldName.substring(1);
  }

  public static String parSetName(String fieldName)
  {
    if ((fieldName == null) || ("".equals(fieldName))) {
      return null;
    }
    return "set" + fieldName.substring(0, 1).toUpperCase() + 
      fieldName.substring(1);
  }
}
