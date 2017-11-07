package com.lbs.data.demo.topic.model.entity;

import com.lbs.data.demo.topic.model.ProjectionWithSpecification;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@DiscriminatorValue(value = "LbsBankAccount")
@ProjectionWithSpecification( projector = "")
public class LbsBankAccount extends LbsAccount {

    @Size(max = 30)
    @Column(name = "\"BANK\"", length = 30)
    private String bank;

    @Size(max = 30)
    @Column(name = "\"BRANCH\"", length = 30)
    private String branch;

    @Size(max = 34)
    @Column(name = "\"IBAN\"", length = 34)
    private String iban;

    @Size(max = 50)
    @Column(name = "\"ACCOUNTNO\"", length = 50)
    private String accountNumber;

    @Column(name = "\"CCARDACCOUNT\"")
    private boolean creditCardAccount;

    @Column(name = "\"BLOCKDURATION\"")
    @Max(value = 1000)
    private int blockDuration;

    @Column(name = "\"DEDUCTIONPCT\"")
    @Min(value = 0)
    @Max(value = 99)
    private int deductionPercentage;

    @Size(max = 50)
    @Column(name = "\"OWNERFULLNAME\"", length = 50)
    private String ownerFullName;

    @Size(max = 50)
    @Column(name = "\"OWNERPHONE\"", length = 50)
    private String ownerPhone;

    @Column(name = "\"OWNEREMAIL\"")
    private String ownerEmail;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isCreditCardAccount() {
        return creditCardAccount;
    }

    public void setCreditCardAccount(boolean creditCardAccount) {
        this.creditCardAccount = creditCardAccount;
    }

    public int getBlockDuration() {
        return blockDuration;
    }

    public void setBlockDuration(int blockDuration) {
        this.blockDuration = blockDuration;
    }

    public int getDeductionPercentage() {
        return deductionPercentage;
    }

    public void setDeductionPercentage(int deductionPercentage) {
        this.deductionPercentage = deductionPercentage;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
