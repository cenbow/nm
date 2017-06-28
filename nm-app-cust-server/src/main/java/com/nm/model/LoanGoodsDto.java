package com.nm.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class LoanGoodsDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    @NotBlank(
            message = "分期编号不能为空"
    )
    @Size(
            max = 40,
            message = "分期编号超长"
    )
    private String loanNo;
    @NotBlank(
            message = "商品类型不能为空"
    )
    @Size(
            max = 40,
            message = "商品类型超长"
    )
    private String goodsType;
    private String goodsTypeName;
    @NotBlank(
            message = "商品品牌不能为空"
    )
    @Size(
            max = 64,
            message = "商品品牌超长"
    )
    private String brand;
    @NotBlank(
            message = "商品型号不能为空"
    )
    @Size(
            max = 64,
            message = "商品型号超长"
    )
    private String marques;
    @NotNull(
            message = "价格不能为空"
    )
    @Size(
            max = 24,
            message = "价格超长"
    )
    private String pric;

    public String getPric() {
        return pric;
    }

    public void setPric(String pric) {
        this.pric = pric;
    }

    public LoanGoodsDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsType() {
        return this.goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMarques() {
        return this.marques;
    }

    public void setMarques(String marques) {
        this.marques = marques;
    }


    public String getGoodsTypeName() {
        return this.goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getLoanNo() {
        return this.loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }
}

