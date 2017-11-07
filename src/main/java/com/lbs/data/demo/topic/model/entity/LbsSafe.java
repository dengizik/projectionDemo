package com.lbs.data.demo.topic.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Title :
 * Description :
 * Copyright : Copyright (c) 2016
 * Company : LBS
 * Created on 22.12.2016
 * @author Kemal.Eroglu
 * @version 1.0
 */

@Entity
@DiscriminatorValue(value = "LbsSafe")
public class LbsSafe extends LbsAccount {

}
