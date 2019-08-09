package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.github.pagehelper.*;
import com.ch.dto.${table.entityName}Dto;
import java.util.*;


/**
 * <p>
 * ${table.comment!} service接口实现层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.name}Mapper;

    @Override
    public PageInfo<${table.entityName}> queryAll(${table.entityName}Dto ${table.name}Dto){
        //  开始分页
        PageHelper.startPage(${table.name}Dto.getPageNumber(), ${table.name}Dto.getPageSize());
        List<${table.entityName}> list = list();
        PageInfo<${table.entityName}> pageInfo = new PageInfo<${table.entityName}>(list);
        return pageInfo;
    }
 }
</#if>
