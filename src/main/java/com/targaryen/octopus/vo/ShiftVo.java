package com.targaryen.octopus.vo;

import lombok.Data;

// This is only an object for test!!! Later it will be deleted
// 这只是一个测试用的数据结构！！！之后会被删除

@Data
public class ShiftVo {
    private int shiftId;

    private int shiftDay;
    private String shiftStart;
    private String shiftEnd;
    private int shiftCount;

    private int roleId;
    private String roleName;
}
