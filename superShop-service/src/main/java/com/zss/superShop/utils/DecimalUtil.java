package com.zss.superShop.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额计算工具类
 * @author zhushanshan
 * @2018年7月21日 下午3:35:19
 */
public class DecimalUtil{
	
	public static final int DEFAULT_SCALE = 2;

	/**
	 * 加法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static <M extends Number, N extends Number> BigDecimal add(M num1, N num2) {
		return toBigDecimal(num1).add(toBigDecimal(num2));
	}

	/**
	 * 减法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static <M extends Number, N extends Number> BigDecimal subtract(M num1, N num2) {
		return toBigDecimal(num1).subtract(toBigDecimal(num2));
	}

	/**
	 * 乘法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static <M extends Number, N extends Number> BigDecimal multiply(M num1, N num2) {
		return toBigDecimal(num1).multiply(toBigDecimal(num2));
	}
	
	/*public static BigDecimal  multiply(double num1,double num2) {
		BigDecimal b1 = new BigDecimal(Double.toString(num1));
		BigDecimal b2 = new BigDecimal(Double.toString(num2));
		return b1.multiply(b2);
	}*/

	/**
	 * 除法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static <M extends Number, N extends Number> BigDecimal divide(M num1, N num2) {
		return toBigDecimal(num1).divide(toBigDecimal(num2), DEFAULT_SCALE, RoundingMode.HALF_UP);
	}
	public static <M extends Number, N extends Number> BigDecimal divide(M num1, N num2,Integer num) {
		return toBigDecimal(num1).divide(toBigDecimal(num2), num, RoundingMode.HALF_UP);
	}
	
	private static <N extends Number> BigDecimal toBigDecimal(N num) {
		return null == num ? BigDecimal.ZERO : new BigDecimal(num.toString());
	}
	
	/**
	 * 格式化成价格格式（保留2位小数，四舍五入），例：1.99 、 2.00
	 * 
	 * @return
	 */
	public static BigDecimal formatToPrice(BigDecimal num) {
		return null == num ? BigDecimal.ZERO : num.setScale(2, RoundingMode.HALF_UP);
	}
	
	public static BigDecimal stringToDecimal(String v,int scale){
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_CEILING);
    }
	
    public static BigDecimal stringToDecimal(String v){
        BigDecimal b = new BigDecimal(v);
        return b.setScale(DEFAULT_SCALE, BigDecimal.ROUND_CEILING);
    }
    
    public static String decimalToString(BigDecimal v){
        return v.setScale(DEFAULT_SCALE, BigDecimal.ROUND_CEILING).toPlainString();
    }
    
    /**
     * 转换金额除以10000
     * @param v
     * @return
     */
    public static String divAmount2W(String v){
    	return decimalToString(divide(stringToDecimal(v),10000));
    }
    
    public static String divAmount2W(BigDecimal v){
    	return decimalToString(divide(v,10000));
    }
    
    /**
     * 转换金额乘以10000
     * @param v
     * @return
     */
    public static BigDecimal subAmount2W(String v){
    	BigDecimal amount = multiply(Double.parseDouble(v),10000);
    	return null == amount ? BigDecimal.ZERO : amount.setScale(2, RoundingMode.HALF_UP);
    }
}
