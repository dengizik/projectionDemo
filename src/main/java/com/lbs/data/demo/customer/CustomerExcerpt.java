package com.lbs.data.demo.customer;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = Customer.class)
public interface CustomerExcerpt {

    String getId();

    String getName();
}
