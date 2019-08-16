package com.helios.gao.domain;

import com.helios.gao.domain.enumeration.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * @author : gaozhiwen
 * @date : 2018/12/31
 */
@Data
public class ContractDTO {
    private Long id;
    private String contractCode;
    private Long type;
    private Long customerId;
    private String name;
    private String legalEntity;
    private String taxPayerId;
    private String address;
    private String telPhone;
    private String bankName;
    private String bankAccount;
    private Long salesId;
    private BigDecimal totalAmount;
    private CurrencyCode code;
    private BigDecimal totalWorkload;
    private Integer signAccountCount;
    private Integer openAccountCount;
    private Long source;
    private String signSubject;
    private LocalDate signDate;
    private Integer version;
    private Integer contractVersion;
    private Boolean isEnabled;
    private Boolean isDeleted;
    private ZonedDateTime createdDate;
    private Long createdBy;
    private ZonedDateTime lastUpdatedDate;
    private Long lastUpdatedBy;
}
