package com.nm.core.comp;

import com.nm.base.framework.core.exception.SystemException;
import com.nm.base.framework.frame.component.PrincipalCacheComponent;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NewPrincipalCacheComponent extends PrincipalCacheComponent {

    @Autowired
    private CacheManager cacheManager;
    private Cache cache;

    public NewPrincipalCacheComponent() {
    }

    @PostConstruct
    public void init() {
        this.cache = this.cacheManager.getCache("sessionCache");
        if (this.cache == null) {
            throw new SystemException("no sessionCache config.");
        }
    }

    public void addNewCache(String key,Object object){
        this.cache.put(new Element(key, object));
    }

    public void removeNewCache(String key) {
        this.cache.remove(key);
    }

    public Object getNewCahce(String key) {
        Element element = this.cache.get(key);
        if (element == null) {
            return null;
        } else {
            Object objectValue = element.getObjectValue();
            return null == objectValue ? null : objectValue;
        }
    }
}
