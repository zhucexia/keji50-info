/**
 * StateEnum.java
 * com.keji50.k5.common.utils.constants
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年12月9日 		chao.li
 *
 * Copyright (c) 2015, Howbuy Rights Reserved.
 */

package com.keji50.k5.common.utils.constants;

/**
 * 状态集合类
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月9日 下午3:36:25
 *
 * @see
 */
public enum States {
    // 草稿
    TEMP("c"), 

    // 已审核， 已上线
    ONLINE("s"), 

    // 已删除
    DELETED("d");

    private String value;

    private States(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
