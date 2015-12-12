package com.keji50.k5.dao.mapper;

import com.keji50.k5.dao.po.AccountPo;

/**
 * 作者信息持久层接口
 *
 * @author   chao.li
 * @version  
 * @since    Ver 1.1
 * @Date	 2015年12月12日		下午2:03:32
 *
 * @see 	 
 */
public interface AccountPoMapper {

    AccountPo selectById(int id);
    
}

