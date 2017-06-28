package com.nm.cmd;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by MIfengHe on 2017/3/25.
 */
public class OCOssAttach implements Serializable {
    private static final long serialVersionUID = 1L;
    private String attName;
    private String attFile;
    private String attFileThumb;
    private Boolean hasThumb = false;
    private Boolean isImage = false;

    public String getAttName() {
        return attName;
    }
    public void setAttName(String attName) {
        this.attName = attName;
    }
    public String getAttFile() {
        return attFile;
    }
    public void setAttFile(String attFile) {
        this.attFile = attFile;
    }
    public String getAttFileThumb() {
        return attFileThumb;
    }
    public void setAttFileThumb(String attFileThumb) {
        if (StringUtils.isBlank(attFileThumb)) return;
        this.attFileThumb = attFileThumb;
        this.hasThumb = true;
        this.isImage = true;
    }
    public Boolean getHasThumb() {
        return hasThumb;
    }
    public void setHasThumb(Boolean hasThumb) {
        this.hasThumb = hasThumb;
    }
    public Boolean getIsImage() {
        return isImage;
    }
    public void setIsImage(Boolean isImage) {
        this.isImage = isImage;
    }
}
