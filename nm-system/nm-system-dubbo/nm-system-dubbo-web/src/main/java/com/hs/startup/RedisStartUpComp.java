package com.hs.startup;

import com.hs.cache.extend.RedisStorageFactory;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.tools.SpringContextHolder;
import com.hs.system.entity.SysParam;
import com.hs.system.mapper.PubSysParamMapper;
import com.hs.utils.ParamUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MIfengHe on 2017/4/11.
 */
@Component
public class RedisStartUpComp {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisStartUpComp.class);

    @PostConstruct
    public void startup() {

        String key = "SYS_RUN_ENV";

        PubSysParamMapper pubSysParamMapper = SpringContextHolder.getBean(PubSysParamMapper.class);
        RedisStorageFactory redisStorageFactory = SpringContextHolder.getBean(RedisStorageFactory.class);

        Boolean redisEnable = redisStorageFactory.getRedisEnable();
        String redisSysRunEnv = redisStorageFactory.getRedisSysRunEnv();

        Map<String,Object> map = new HashMap<>();
        map.put("cod", key);
        map.put("stat", CommonConstant.STAT_ENABLE);
        List<SysParam> list = pubSysParamMapper.queryForList(map);

        String dbSysRunEnv = null;
        if (list != null && !list.isEmpty()) {
            dbSysRunEnv = list.get(0).getVal();
        }

        if (StringUtils.isBlank(dbSysRunEnv)) dbSysRunEnv ="EMPTY";

        String paramSysRunEnv = ParamUtils.getParam(key);
        if (StringUtils.isBlank(paramSysRunEnv)) paramSysRunEnv = "EMPTY";

        LOGGER.info("# ************************************ 系统运行环境校验开始 ************************************ #");
        LOGGER.info("# sys.run.env: " + redisSysRunEnv);
        LOGGER.info("# redis.enable: " + redisEnable);
        LOGGER.info("# DB.SYS_RUN_ENV: " + dbSysRunEnv);
        LOGGER.info("# PARAM.SYS_RUN_ENV: " + paramSysRunEnv);
        LOGGER.info("# PARAM.SYS_RUN_ENV: 不一定是redis缓存， 唯有redis.enable=true且redis不报错，才获取的是redis值。");

        String msg = StringUtils.EMPTY;
        if (dbSysRunEnv.contains("EMPTY") || paramSysRunEnv.contains("EMPTY")) {
            msg = "DB数据库的运行参数与redis运行参数为空。";
        } else if (!dbSysRunEnv.equals(paramSysRunEnv)) {
            msg ="DB数据库的运行参数与redis运行参数不一致。";
        }
        if (StringUtils.isBlank(msg)) {
            LOGGER.info("# DB数据库的运行参数与redis运行参数校验成功.");
        } else {
            LOGGER.error("# " + msg);
        }

        if (StringUtils.isNotBlank(redisSysRunEnv) && !dbSysRunEnv.equals(redisSysRunEnv)) {
            if (!redisSysRunEnv.equals("dev") || ! dbSysRunEnv.equals("test")) {
                LOGGER.warn("# 开发环境(dev) 运行 测试环境(test).");
            }
        }

        LOGGER.info("# ************************************ 系统运行环境校验结束 ************************************ #");
        if (StringUtils.isNotBlank(msg)) {
            LOGGER.error(msg, new RuntimeException(msg));
        }
    }

}
