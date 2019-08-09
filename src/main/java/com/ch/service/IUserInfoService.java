package com.ch.service;

import com.ch.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caihao
 * @since 2019-07-12
 */
public interface IUserInfoService extends IService<UserInfo> {

    UserInfo login(UserInfo user);

    List<UserInfo> selectAccount(UserInfo map);

    void insert(UserInfo user);
}
