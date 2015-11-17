package com.keji50.k5.dao.datasource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
 
public class CustomerRoutingDataSource extends AbstractRoutingDataSource {
 
   @Override
   protected Object determineCurrentLookupKey() {
      return CustomerContextHolder.getCustomerType();
   }
}