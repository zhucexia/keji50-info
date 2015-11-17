package com.keji50.k5.dao.datasource;

public class CustomerContextHolder {

    private CustomerContextHolder(){
        
    }
	/**
	 * 当前活动读写数据源
	 */
	public final static String CURRENT_DATASOURCE = "MASTER";

	private static final ThreadLocal<String> CONTEXTHOLDER = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		CONTEXTHOLDER.set(customerType);
	}

	public static String getCustomerType() {
		return (String) CONTEXTHOLDER.get();
	}

	public static void clearCustomerType() {
		CONTEXTHOLDER.remove();
	}
}