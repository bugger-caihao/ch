package com.ch.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.ch.entity.Menu;
import com.ch.mapper.MenuMapper;
import com.ch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.github.pagehelper.*;
import com.ch.dto.MenuDto;
import java.util.*;


/**
 * <p>
 *  service接口实现层
 * </p>
 *
 * @author caihao
 * @since 2019-08-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageInfo<Menu> queryAll(MenuDto menuDto){
        //  开始分页
        PageHelper.startPage(menuDto.getPageNumber(), menuDto.getPageSize());
        List<Menu> list = menuMapper.selectList(new Wrapper<Menu>() {
            @Override
            public Menu getEntity() {
                Menu menu = new Menu();
                menu.setParentNumber(menuDto.getParentNumber());
                return menu;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getCustomSqlSegment() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        });
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(list);
        return pageInfo;
    }

    @Override
    public List<Menu> findParentMenu() {
        return menuMapper.findParentMenu();
    }

    @Override
    public List<Menu> findSonMenu(Menu menu) {
        return menuMapper.findSonMenu(menu);
    }
}
