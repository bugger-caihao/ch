package com.ch.service.impl;

import com.ch.entity.Mutilpinsert;
import com.ch.mapper.MutilpinsertMapper;
import com.ch.service.IMutilpinsertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caihao
 * @since 2019-07-02
 */
@Service
public class MutilpinsertServiceImpl extends ServiceImpl<MutilpinsertMapper, Mutilpinsert> implements IMutilpinsertService {

    @Autowired
    private MutilpinsertMapper mapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void insertData() {
        List<Mutilpinsert> list = new ArrayList();
        for(long i=0; i<10000; i++){
            Mutilpinsert entity = new Mutilpinsert();
            entity.setId(i);
            entity.setName("ch" + i);
            entity.setSex("男");
            entity.setAddress("武汉" + i);
            list.add(entity);
            //mapper.insert(entity);
        }
        long startTime = System.currentTimeMillis();
        for (Mutilpinsert mutilpinsert : list) {
            mapper.insert(mutilpinsert);
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        System.out.println("单条插入执行时间： " + time + " ms");
        //   15334 ms
    }

    @Override
    public void other() {
        List<Mutilpinsert> list = new ArrayList();
        //可以执行批量操作的sqlSession
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

        //批量保存执行前时间
        try{
            MutilpinsertMapper mutilpinsertMapper =  openSession.getMapper(MutilpinsertMapper.class);
            for (long i = 0; i < 10000; i++) {
                Mutilpinsert entity = new Mutilpinsert();
                entity.setId(i);
                entity.setName("ch" + i);
                entity.setSex("男");
                entity.setAddress("武汉" + i);
                list.add(entity);
                //mapper.insert(entity);
            }

            long start=System.currentTimeMillis();
            for (Mutilpinsert mutilpinsert : list) {
                mutilpinsertMapper.insert(mutilpinsert);
            }

            openSession.commit();
            long end=  System.currentTimeMillis();
            long time = end - start;
            //批量保存执行后的时间
            System.out.println("BATCH执行时间： " + time + " ms");

        }finally{
            openSession.close();
        }
    }


    @Override
    public void otherBatch() {
        List<Mutilpinsert> list = new ArrayList();

            for (long i = 0; i < 10000; i++) {
                Mutilpinsert entity = new Mutilpinsert();
                entity.setId(i);
                entity.setName("ch" + i);
                entity.setSex("男");
                entity.setAddress("武汉" + i);
                list.add(entity);
            }
            long start=System.currentTimeMillis();
            saveBatch(list);
            long end=  System.currentTimeMillis();
            long time = end - start;
            System.out.println("BATCH执行时间： " + time + " ms");
            //  12149 ms

    }

}
