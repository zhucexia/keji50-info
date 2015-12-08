package com.keji50.k5.dao.po;

import com.keji50.k5.common.utils.constants.Constants;
import lombok.Getter;
import lombok.Setter;
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
public class AccountPo {

    /**
     * 用户id
     */
    @Setter
    @Getter
    private int id;

    /**
     * 用户登录名
     */
    @Setter
    @Getter
    private String username;

    /**
     * 用户邮箱
     */
    @Setter
    @Getter
    private String email;

    /**
     * 用户昵称
     */
    @Setter
    @Getter
    private String nickname;

    /**
     * 用户头像
     */
    @Setter
    private String image;

    /**
     * 用户状态
     */
    @Setter
    @Getter
    private int status;

    public String getImage() {
        return StringUtils.isEmpty(image) ? Constants.DEFAULT_ACCOUNT_IMAGE : image;
    }

}
