package com.keji50.k5.service.out.http;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.util.EntityUtils;

/**
 * Http entity 处理工具类
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年9月25日 下午2:52:12
 *
 * @see
 */
public class HttpEntityUtils {

    /**
     * http entity转化为String， 支持对gzip压缩的entity处理
     *
     * @param  entity   http response entity
     * @param  charset  http 返回编码
     * 
     * @return String对象
     * @throws IOException
     */
    public static String toString(HttpEntity entity, String charset) throws IOException {
        Header header = entity.getContentEncoding();
        if (header != null && header.toString().toLowerCase().indexOf("gzip") != -1) {
            return EntityUtils.toString(new GzipDecompressingEntity(entity), charset);
        }

        return EntityUtils.toString(entity, charset);
    }
}
