package com.helios.gao.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : gaozhiwen
 * @date : 2019/7/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ReceiptDetail extends BaseRowModel {
    @ExcelProperty(value = "id")
    private String id;
    @ExcelProperty(value = "invoiceTypeNo")
    private String invoiceTypeNo;
    @ExcelProperty(value = "billingCode")
    private String billingCode;
    @ExcelProperty(value = "billingNo")
    private String billingNo;
    @ExcelProperty(value = "billingTime")
    private String billingTime;
    @ExcelProperty(value = "fee")
    private String fee;
    @ExcelProperty(value = "feeWithoutTax")
    private String feeWithoutTax;
    @ExcelProperty(value = "tax")
    private String tax;
    @ExcelProperty(value = "checkCode")
    private String checkCode;
    @ExcelProperty(value = "draweeNo")
    private String draweeNo;
    @ExcelProperty(value = "title")
    private String title;
    @ExcelProperty(value = "payee")
    private String payee;
    @ExcelProperty(value = "payeeNo")
    private String payeeNo;
    @ExcelProperty(value = "cardsignType")
    private String cardsignType;
    @ExcelProperty(value = "vatInvoiceCurrencyCode")
    private String vatInvoiceCurrencyCode;
    @ExcelProperty(value = "receiptOwner")
    private String receiptOwner;
    @ExcelProperty(value = "moreInfo")
    private String moreInfo;
}


