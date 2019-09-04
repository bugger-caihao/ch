package com.ch;

import com.ch.dto.MenuDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName: ReflectTest
 * @Description: 反射测试
 * @Author: caihao
 * @Date: 2019/9/4 9:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReflectTest {
    
    /**
     * @Description 利用反射获取运行时类
     * @Author caihao
     * @Date 2019/9/4 9:53
     * @Param [] 
     * @Return void
     */
    @Test
    public void test01() throws Exception{
        //  使用Class 中的静态方法获取运行时类
        Class clazz1 = Class.forName("com.ch.dto.MenuDto");
        //  运行时类对象的class属性
        Class<MenuDto> clazz2 = MenuDto.class;
        //  使用getClass()方法
        MenuDto menuDto = new MenuDto();
        Class clazz3 = menuDto.getClass();

        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz3);
    }
    
    /**
     * @Description 利用反射获取运行时类的属性,并为属性设置值
     * @Author caihao
     * @Date 2019/9/4 10:09
     * @Param [] 
     * @Return void
     */
    @Test
    public void test02() throws Exception {
        Class clazz = Class.forName("com.ch.dto.MenuDto");
        MenuDto menuDto = (MenuDto)clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            System.out.println(name);
        }
        System.out.println("===============================");
        Field parentName = clazz.getDeclaredField("parentName");
        parentName.setAccessible(true);
        parentName.set(menuDto, "父级菜单");
        System.out.println(menuDto);
    }

    /**
     * @Description 利用反射获取运行时类中的方法，并调用方法
     * @Author caihao
     * @Date 2019/9/4 10:34
     * @Param []
     * @Return void
     */
    @Test
    public void test03() throws Exception{
        Class clazz = Class.forName("com.ch.dto.MenuDto");
        MenuDto menuDto = (MenuDto)clazz.newInstance();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            System.out.println(name);
        }

        System.out.println("========================");
        Method method = clazz.getMethod("toString");
        Object invoke = method.invoke(menuDto);
        System.out.println(invoke);
    }
    
    
    
}
