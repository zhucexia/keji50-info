package com.keji50.k5.common.utils;

import java.util.Collections;

import java.util.HashMap;
import java.util.Map;
import com.github.pagehelper.Page;

public class PageUtils {

    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String START_ROW = "startrow";
    public static final String END_ROW = "endrow";
    public static final String TOTAL = "total";
    public static final String PAGES = "pages";
    public static final String ROWS = "rows";

    public static void initPageInfo(Map<String, Object> conditions) {
        // 每页的记录数
        Object pageSize = conditions.get(PAGE_SIZE);
        if (pageSize == null || Integer.parseInt(pageSize.toString()) <= 0) {
            conditions.put(PAGE_SIZE, 10);
        }

        // 首次进入 当前页 pageNo = 1
        Object pageNo = conditions.get(PAGE_NUM);
        if (pageNo == null || Integer.parseInt(pageNo.toString()) <= 0) {
            conditions.put(PAGE_NUM, 1);
        }
    }

    public static Map<String, Object> pageToMap(Page<?> page) {
        if (page == null) {
            return Collections.emptyMap();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(END_ROW, page.getEndRow());
        map.put(PAGE_NUM, page.getPageNum());
        map.put(PAGES, page.getPages());
        map.put(PAGE_SIZE, page.getPageSize());
        map.put(START_ROW, page.getStartRow());
        map.put(TOTAL, page.getTotal());
        map.put(ROWS, page.getResult());
        return map;
    }
}
