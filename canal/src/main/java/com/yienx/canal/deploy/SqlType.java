/**
  * Copyright 2020 bejson.com 
  */
package com.yienx.canal.deploy;

import lombok.Data;

@Data
public class SqlType {

    private int id;
    // 大宗商品
    private int commodity_name;
    private int commodity_price;
    private int number;
    private int description;
}