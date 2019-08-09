package com.ch.mapper;

import com.ch.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caihao
 * @since 2019-08-06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findParentMenu();

    List<Menu> findSonMenu(Menu menu);
}
