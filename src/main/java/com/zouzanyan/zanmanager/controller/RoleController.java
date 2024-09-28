package com.zouzanyan.zanmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zouzanyan.zanmanager.entity.Role;
import com.zouzanyan.zanmanager.entity.RoleAuthority;
import com.zouzanyan.zanmanager.entity.RoleEmployee;
import com.zouzanyan.zanmanager.service.RoleAuthorityService;
import com.zouzanyan.zanmanager.service.RoleEmployeeService;
import com.zouzanyan.zanmanager.service.RoleService;
import com.zouzanyan.zanmanager.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private RoleEmployeeService roleEmployeeService;

    @GetMapping("/roleList")
    public List<Role> roleList(){
        return this.roleService.list();
    }

    @GetMapping("/list")
    public PageVO list(Integer page, Integer size){
        Page<Role> rolePage = new Page<>(page,size);
        Page<Role> resultPage = this.roleService.page(rolePage);
        PageVO pageVO = new PageVO(
                resultPage.getRecords(),
                resultPage.getSize(),
                resultPage.getTotal());
        return pageVO;
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Role role){
        return this.roleService.save(role);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Role role){
        return this.roleService.updateById(role);
    }

    @DeleteMapping("/delete")
    public boolean delete(Integer id){
        QueryWrapper<RoleAuthority> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("role_id",id);
        this.roleAuthorityService.remove(queryWrapper1);
        QueryWrapper<RoleEmployee> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("role_id", id);
        this.roleEmployeeService.remove(queryWrapper2);
        return this.roleService.removeById(id);
    }

    @PutMapping("/updateAuthority")
    public boolean updateAuthority(@RequestBody Integer[] ids){
        // 约定前端传过来的数组的最后一个元素是role_id,前面的元素都是authority_id
        Integer roleId = ids[ids.length - 1];
        QueryWrapper<RoleAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        this.roleAuthorityService.remove(queryWrapper);
        for (int i = 0; i < ids.length - 1; i++) {
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setAuthorityId(ids[i]);
            roleAuthority.setRoleId(roleId);
            this.roleAuthorityService.save(roleAuthority);
        }
        return true;
    }

    @GetMapping("/getAuthorityById")
    public List<Integer> getAuthorityById(Integer id){
        QueryWrapper<RoleAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        List<RoleAuthority> list = this.roleAuthorityService.list(queryWrapper);
        List<Integer> idList = new ArrayList<>();
        for (RoleAuthority roleAuthority : list) {
            idList.add(roleAuthority.getAuthorityId());
        }
        return idList;
    }

}

