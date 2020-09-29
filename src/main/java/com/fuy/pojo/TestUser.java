package com.fuy.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestUser {
    private int id;
    private String name;
    private String pwd;


}
