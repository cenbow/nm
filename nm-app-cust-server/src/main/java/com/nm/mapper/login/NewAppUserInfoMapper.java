package com.nm.mapper.login;

import com.nm.api.frame.auth.model.AppUserInfo;
import com.nm.core.comp.bean.NewAppUserInfo;
import com.nm.mybatis.annotation.MyBatisRepository;
import com.nm.mybatis.mapper.common.Mapper;

/**
 * Created by lenovo on 2017/5/10.
 */
@MyBatisRepository
public interface NewAppUserInfoMapper extends Mapper<NewAppUserInfo> {
}
