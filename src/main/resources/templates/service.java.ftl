package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.ch.dto.${table.entityName}Dto;
import com.github.pagehelper.PageInfo;




/**
 * <p>
 * ${table.comment!} service接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    PageInfo<${table.entityName}> queryAll(${table.entityName}Dto ${table.name}Dto);

}
</#if>
