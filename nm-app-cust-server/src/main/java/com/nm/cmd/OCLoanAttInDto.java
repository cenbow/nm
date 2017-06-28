package com.nm.cmd;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.hs.loan.busi.dto.LoanAttInDto;

/**
 * Created by MIfengHe on 2017/3/21.
 */
public class OCLoanAttInDto extends LoanAttInDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean hasThumb = false;
    private String attFileThumb;

    public Boolean getHasThumb() {
        return hasThumb;
    }

    public void setHasThumb(Boolean hasThumb) {
        this.hasThumb = hasThumb;
    }

    public String getAttFileThumb() {
        return attFileThumb;
    }

    public void setAttFileThumb(String attFileThumb) {
        this.hasThumb =StringUtils.isNotBlank(attFileThumb);
        this.attFileThumb = attFileThumb;
    }
}
