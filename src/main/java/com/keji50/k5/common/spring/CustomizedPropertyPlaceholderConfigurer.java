package com.keji50.k5.common.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * ClassName:CustomizedPropertyPlaceholderConfigurer Function: TODO ADD FUNCTION
 * Reason: TODO ADD REASON
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月8日 下午4:42:25
 *
 * @see
 */
public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);

        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    public static String getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
}
