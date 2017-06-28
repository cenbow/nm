package com.hs;

import java.math.BigDecimal;

import com.hs.utils.AmountUtils;

public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println(new BigDecimal(AmountUtils.changeF2Y("661900")));;
	}
}
