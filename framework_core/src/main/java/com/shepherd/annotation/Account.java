package com.shepherd.annotation;

import com.shepherd.annotation.anno.TableField;
import com.shepherd.annotation.anno.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/8/24 10:44
 */
@Data
@TableName(name = "account_info")
public class Account {
    @TableField("_id")
    private Integer id;
    private String name;
    private Date birthday;
    private String sex;
    private String address;
    @TableField("total_amount")
    private BigDecimal amount;

}
