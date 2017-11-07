package com.lbs.data.demo.topic.model.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.lbs.soho.domain.enumeration.CurrencyType;
//import com.lbs.soho.domain.enumeration.LbsState;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
@Table(name = "\"ACCOUNT\"")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "\"TYPE\"")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LbsSafe.class, name = "LbsSafe"),
        @JsonSubTypes.Type(value = LbsBankAccount.class, name = "LbsBankAccount")
})
public abstract class LbsAccount {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    @Column(name = "\"NAME\"", length = 50)
    private String name;

//    @Enumerated(EnumType.ORDINAL)
//    @Column(name = "\"STATE\"")
//    private LbsState state;

    @Column(name = "\"BALANCE\"")
    private double balance;

//    @Column(name = "\"CURRENCY\"")
//    @Enumerated(EnumType.STRING)
//    private CurrencyType currency;

    @Column(name = "\"BALANCEDATE\"")
    private Long balanceDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    public CurrencyType getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(CurrencyType currency) {
//        this.currency = currency;
//    }

    public Long getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Long balanceDate) {
        this.balanceDate = balanceDate;
    }

//    public LbsState getState() {
//        return state;
//    }
//
//    public void setState(LbsState state) {
//        this.state = state;
//    }
}
