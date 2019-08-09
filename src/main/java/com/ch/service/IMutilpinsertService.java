package com.ch.service;

import com.ch.entity.Mutilpinsert;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author caihao
 * @since 2019-07-02
 */
public interface IMutilpinsertService extends IService<Mutilpinsert> {

    void insertData();

    void other();

    void otherBatch();
}
