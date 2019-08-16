package com.helios.gao.domain.enumeration;

public enum ReceiptPropertyEnum {
    INVOICE_TYPE_NO("InvoiceTypeNo", "发票类型"),
    BILLING_CODE("BillingCode", "发票代码"),
    BILLING_NO("BillingNo", "发票号码"),
    BILLING_TIME("BillingTime", "开票时间"),
    FEE("fee", "价税合计"),
    DRAWEE_NO("draweeNo", "购买方纳税人识别号"),
    TITLE("title", "购买方抬头"),
    PAYEE("payee", "销售方抬头"),
    PAYEE_NO("payeeNo", "销售方纳税人识别号"),
    CARDSIGN_TYPE("cardsignType", "卡券来源"),
    RECEIPT_OWNER("receiptOwner", "发票归属人"),
    MORE_INFO("moreInfo", "详细信息"),
    FEE_WITHOUT_TAX("feeWithoutTax", "不含税金额"),
    TAX("tax", "税额"),
    CHECK_CODE("checkCode", "校验码"),
    VAT_INVOICE_CURRENCY_CODE("vatInvoiceCurrencyCode", "币种"),;

    private String key;
    private String zhcn;

    ReceiptPropertyEnum(String key, String zhcn) {
        this.key = key;
        this.zhcn = zhcn;
    }

    public static ReceiptPropertyEnum parseKey(String key) {
        if (key == null) {
            return null;
        }
        for (ReceiptPropertyEnum receiptPropertyEnum : ReceiptPropertyEnum.values()) {
            //兼容存储zhcn的老数据
            if (receiptPropertyEnum.key.equals(key) || receiptPropertyEnum.getZhcn().equals(key)) {
                return receiptPropertyEnum;
            }
        }
        return null;
    }

    public static boolean needConvertAmount(ReceiptPropertyEnum propertyEnum) {
        return (propertyEnum == ReceiptPropertyEnum.FEE || propertyEnum == ReceiptPropertyEnum.FEE_WITHOUT_TAX || propertyEnum == ReceiptPropertyEnum.TAX);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getZhcn() {
        return zhcn;
    }

    public void setZhcn(String zhcn) {
        this.zhcn = zhcn;
    }
}

