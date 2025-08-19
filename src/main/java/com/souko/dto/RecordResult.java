package com.souko.dto;

import com.souko.entity.Record;
import lombok.Data;



@Data
public class RecordResult extends Record {

    private String userName; // 对应 tb_user.no
    private String adminName;  // 对应 tb_user.name
    private String goodsName;
    private String storeName;
    private String goodstypeName;
}
