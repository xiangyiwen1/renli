package com.cow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.entity.SalaryDeduct;
import com.cow.enums.DeductEnum;
import com.cow.mapper.SalaryDeductMapper;
import com.cow.dto.Response;
import com.cow.dto.ResponseDTO;
import com.cow.util.EnumUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工资扣除表 服务类
 * </p>
 *
 * @author cow
 * @since 2022-03-27
 */
@Service
public class SalaryDeductService extends ServiceImpl<SalaryDeductMapper, SalaryDeduct> {


    public ResponseDTO add(SalaryDeduct salaryDeduct) {
        if (save(salaryDeduct)) {
            return Response.success();
        }
        return Response.error();
    }

    public ResponseDTO deleteById(Integer id) {
        if (removeById(id)) {
            return Response.success();
        }
        return Response.error();
    }

    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO edit(SalaryDeduct salaryDeduct) {
        if (updateById(salaryDeduct)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        SalaryDeduct salaryDeduct = getById(id);
        if (salaryDeduct != null) {
            return Response.success(salaryDeduct);
        }
        return Response.error();
    }

    public ResponseDTO find(Integer deptId, Integer typeNum) {
        QueryWrapper<SalaryDeduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId).eq("type_num", typeNum);
        SalaryDeduct salaryDeduct = getOne(queryWrapper);
        if (salaryDeduct != null) {
            return Response.success(salaryDeduct);
        }
        return Response.error();
    }


    public ResponseDTO setSalaryDeduct(SalaryDeduct salaryDeduct) {
        QueryWrapper<SalaryDeduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", salaryDeduct.getDeptId()).eq("type_num", salaryDeduct.getTypeNum().getCode());
        if (saveOrUpdate(salaryDeduct, queryWrapper)) {
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 获取所有罚款类型
     * @return
     */
    public ResponseDTO findAll() {
        return Response.success(EnumUtil.getEnumList(DeductEnum.class));
    }
}




