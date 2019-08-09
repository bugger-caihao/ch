package com.ch.mapper;

import com.ch.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caihao
 * @since 2019-07-12
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo login(UserInfo user);

    List<UserInfo> selectAccount(UserInfo map);

    void insertAccount(UserInfo user);
}
