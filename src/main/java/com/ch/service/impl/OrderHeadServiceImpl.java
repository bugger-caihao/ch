package com.ch.service.impl;

import com.ch.entity.OrderHead;
import com.ch.mapper.OrderHeadMapper;
import com.ch.service.OrderHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  service接口实现层
 * </p>
 *
 * @author caihao
 * @since 2019-12-03
 */
@Service
public class OrderHeadServiceImpl extends ServiceImpl<OrderHeadMapper, OrderHead> implements OrderHeadService {

    @Autowired
    private OrderHeadMapper mapper;

 }
