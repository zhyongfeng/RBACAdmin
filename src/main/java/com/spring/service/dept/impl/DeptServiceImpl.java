package com.spring.service.dept.impl;

import com.github.pagehelper.Page;
import com.spring.common.exceptions.MyException;
import com.spring.common.utils.DateUtils;
import com.spring.common.utils.UUID;
import com.spring.dao.DeptMapper;
import com.spring.model.Dept;
import com.spring.service.dept.DeptService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 施成
 * @Date 2018/2/11
 * @time 9:32
 * @Describe:
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Page<Dept> getAllDeptMessage(RowBounds rowBounds) {
        return deptMapper.getAllDeptMessage(rowBounds);
    }

    @Override
    public Dept getDeptById(String deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public void insert(Dept info) {
        //生成唯一标识符
        info.setDeptId(UUID.getUUID());
        //获取当前创建时间
        info.setCreateTime(DateUtils.today());
        if (deptMapper.insert(info)<1){
            throw new MyException("新增部门信息失败！");
        }
    }

    @Override
    public void update(Dept info) {

        info.setUpdateTime(DateUtils.today());
        if(deptMapper.updateByPrimaryKey(info)<1){
            throw new MyException("修改部门信息失败！");
        }

    }

    @Override
    public void delete(String deptId) {
        if (deptMapper.deleteByPrimaryKey(deptId)<1){
            throw new MyException("删除部门信息失败!");
        }
    }

    @Override
    public List<Dept> getAllDept() {
        return deptMapper.getAllDept();
    }
}