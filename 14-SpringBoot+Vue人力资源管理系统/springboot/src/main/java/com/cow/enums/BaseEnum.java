package com.cow.enums;

import java.io.Serializable;

/**
 * 通用枚举接口
 *
 * @Author cow
 * @Date 2022/3/21
 * @Version 1.0
 */

public interface BaseEnum<T> extends Serializable {
    /**
     * 获取编号
     *
     * @return
     */
    Integer getCode();

    /**
     * 消息内容
     *
     * @return
     */
    String getMessage();
}
