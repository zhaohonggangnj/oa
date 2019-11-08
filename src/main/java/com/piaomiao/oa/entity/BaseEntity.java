package com.piaomiao.oa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
@Setter
@Getter
class BaseEntity {

    @Transient
    private Integer page = 1;

    @Transient
    private  Integer rows = 10;
}
