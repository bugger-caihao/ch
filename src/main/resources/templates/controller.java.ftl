package ${package.Controller};


import org.springframework.web.bind.annotation.*;

<#if restControllerStyle>
 import org.springframework.web.bind.annotation.RestController;
<#else>
 import org.springframework.stereotype.Controller;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import com.ch.utils.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import com.github.pagehelper.PageInfo;
import ${package.Service}.${table.serviceName};
import com.ch.dto.${table.entityName}Dto;
import ${package.Entity}.${table.entityName};
<#if superControllerClassPackage??>
 import ${superControllerClassPackage};
</#if>

/**
* <p>
 * ${table.comment!} 前端控制器
 * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Slf4j
@Api(value = "${table.name}CRUD接口")
@RequestMapping("<#if controllerMappingHyphenStyle??>/${controllerMappingHyphen}<#else>/${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${table.name}Service;

    @ApiOperation(value = "分页查询 ${table.name} 列表",notes="")
    @ApiImplicitParam(name = "${table.name}Dto", value = "${table.name} 分页查询实体中的参数")
    @PostMapping("/queryAll")
    public JsonResult<List<${table.entityName}>> queryAll(${table.entityName}Dto ${table.name}Dto){

        PageInfo<${table.entityName}> pageInfo = ${table.name}Service.queryAll(${table.name}Dto);
        return ResultUtil.successList(pageInfo.getList(), pageInfo.getTotal());
    }


}
</#if>
