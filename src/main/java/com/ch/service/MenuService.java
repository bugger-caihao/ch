package com.ch.service;

import com.ch.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ch.dto.MenuDto;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * <p>
 *  service接口
 * </p>
 *
 * @author caihao
 * @since 2019-08-06
 */
public interface MenuService extends IService<Menu> {

    PageInfo<Menu> queryAll(MenuDto menuDto);

    List<Menu> findParentMenu();

    List<Menu> findSonMenu(Menu menu);
}
