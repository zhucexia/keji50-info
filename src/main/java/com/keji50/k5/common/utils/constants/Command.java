package com.keji50.k5.common.utils.constants;

import com.alibaba.druid.util.StringUtils;

/**
 * 命令枚举 上一页 下一页
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月10日 下午1:06:32
 *
 * @see
 */
public enum Command {

    PREVIOUS("previous"), NEXT("next");

    private String value;

    private Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        for (Command command : values()) {
            if (StringUtils.equals(value, command.value)) {
                return command;
            }
        }

        return Command.NEXT;
    }

    public String toString() {
        return value;
    }
}
