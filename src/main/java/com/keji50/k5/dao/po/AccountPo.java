package com.keji50.k5.dao.po;

import com.keji50.k5.common.utils.constants.Constants;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * 客户信息对象
 * 
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月8日 下午3:50:54
 * 
 * @see
 */
@Data
public class AccountPo {

    /**
     * 用户id
     */
    private int id;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String image;

    /**
     * 用户状态
     */
    private int status;

    public String getImage() {
        return StringUtils.isEmpty(image) ? Constants.DEFAULT_ACCOUNT_IMAGE : image;
    }

    public String getNickname() {
        if (StringUtils.isNotEmpty(nickname)) {
            return nickname;
        }

        if (StringUtils.isNotEmpty(username)) {
            return username;
        }

        return email;
    }
}
