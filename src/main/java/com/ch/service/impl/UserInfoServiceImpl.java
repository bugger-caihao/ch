package com.ch.service.impl;

import com.ch.entity.UserInfo;
import com.ch.mapper.UserInfoMapper;
import com.ch.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caihao
 * @since 2019-07-12
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper mapper;

    //@Cacheable(value = "user") //将方法的返回值放到缓存组件中
    @Override
    public UserInfo login(UserInfo user) {
        System.out.println("查询用户："+user.getUserName());
        return mapper.login(user);
    }

    @Override
    public List<UserInfo> selectAccount(UserInfo map) {
        return mapper.selectAccount(map);
    }


    //@CachePut(value = "user") 同样是将方法的返回值放到缓存中，但是这里的方法返回值是void，所以在没有将注册账号信息添加到缓存中去。
    @Override
    public void insert(UserInfo user) {
        String userPassword = user.getUserPassword();
        if (userPassword != null) {
            user.setUserPassword(MD5Utils.MD5Encode(userPassword));
        }
        System.out.println("插入用户：" + user);
        mapper.insertAccount(user);
    }
}
