package com.cow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.entity.Overtime;
import com.cow.enums.OvertimeEnum;
import com.cow.mapper.OvertimeMapper;
import com.cow.dto.Response;
import com.cow.dto.ResponseDTO;
import com.cow.util.EnumUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 加班表 服务类
 * </p>
 *
 * @author cow
 * @since 2022-03-28
 */
@Service
public class OvertimeService extends ServiceImpl<OvertimeMapper, Overtime> {


    public ResponseDTO add(Overtime overtime) {
        if (save(overtime)) {
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


    public ResponseDTO edit(Overtime overtime) {
        if (updateById(overtime)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Overtime overtime = getById(id);
        if (overtime != null) {
            return Response.success(overtime);
        }
        return Response.error();
    }

    public ResponseDTO find(Integer deptId, Integer typeNum) {
        QueryWrapper<Overtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId).eq("type_num", typeNum);
        Overtime overtime = getOne(queryWrapper);
        if (overtime != null) {
            return Response.success(overtime);
        }
        return Response.error();
    }


    public ResponseDTO setOvertime(Overtime overtime) {
        QueryWrapper<Overtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", overtime.getDeptId()).eq("type_num", overtime.getTypeNum().getCode());
        if (saveOrUpdate(overtime, queryWrapper)) {
            return Response.success();
        }
        return Response.error();
    }


    /**
     * 获取所有加班类型
     * @return
     */
    public ResponseDTO findAll() {
        List<Map<String, Object>> enumList = EnumUtil.getEnumList(OvertimeEnum.class);
        for (Map<String, Object> map : enumList) {
            for (OvertimeEnum overtimeEnum : OvertimeEnum.values()) {
                if (map.get("code") == overtimeEnum.getCode()) {
                    map.put("lowerLimit", overtimeEnum.getLowerLimit());
                }
            }
        }
        return Response.success(enumList);
    }


}




