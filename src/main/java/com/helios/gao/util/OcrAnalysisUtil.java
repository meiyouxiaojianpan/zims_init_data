package com.helios.gao.util;

import com.helios.gao.domain.ReceiptDetail;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
*@author : gaozhiwen
*@date : 2019/7/8
*
*/
public class OcrAnalysisUtil {

    /**
     * 比较属性值，逐个比较每一个属性值，实体类增加属性值需要维护此方法
     * @param dataList
     * @param originalDetail
     * @param latestDetail
     */
    public static void compareFields(List<List<String>> dataList, ReceiptDetail originalDetail, ReceiptDetail latestDetail) {
        if (originalDetail == null || latestDetail == null) {
            return;
        }
        if (originalDetail.getInvoiceTypeNo() != null || latestDetail.getInvoiceTypeNo() != null) {
            //都不为null或者任意一个为null
            if (originalDetail.getInvoiceTypeNo() == null || latestDetail.getInvoiceTypeNo() == null) {
                dataList.add(Arrays.asList("invoiceTypeNo", originalDetail.getId()));
            } else if (!originalDetail.getInvoiceTypeNo().equals(latestDetail.getInvoiceTypeNo())) {
                //都不为null
                dataList.add(Arrays.asList("invoiceTypeNo", originalDetail.getId()));
            }
        }
        if (originalDetail.getBillingCode() != null || latestDetail.getBillingCode() != null) {
            if (originalDetail.getBillingCode() == null || latestDetail.getBillingCode() == null) {
                dataList.add(Arrays.asList("billingCode", originalDetail.getId()));
            } else if (!originalDetail.getBillingCode().equals(latestDetail.getBillingCode())) {
                dataList.add(Arrays.asList("billingCode", originalDetail.getId()));
            }
        }
        if (originalDetail.getBillingNo() != null || latestDetail.getBillingNo() != null) {
            if (originalDetail.getBillingNo() == null || latestDetail.getBillingNo() == null) {
                dataList.add(Arrays.asList("billingNo", originalDetail.getId()));
            } else if (!originalDetail.getBillingNo().equals(latestDetail.getBillingNo())) {
                dataList.add(Arrays.asList("billingNo", originalDetail.getId()));
            }
        }
        if (originalDetail.getBillingTime() != null || latestDetail.getBillingTime() != null) {
            if (originalDetail.getBillingTime() == null || latestDetail.getBillingTime() == null) {
                dataList.add(Arrays.asList("billingTime", originalDetail.getId()));
            } else if (!originalDetail.getBillingTime().equals(latestDetail.getBillingTime())) {
                dataList.add(Arrays.asList("billingTime", originalDetail.getId()));
            }
        }
        if (originalDetail.getFee() != null || latestDetail.getFee() != null) {
            if (originalDetail.getFee() == null || latestDetail.getFee() == null) {
                dataList.add(Arrays.asList("fee", originalDetail.getId()));
            } else if (!originalDetail.getFee().equals(latestDetail.getFee())) {
                dataList.add(Arrays.asList("fee", originalDetail.getId()));
            }
        }
        if (originalDetail.getFeeWithoutTax() != null || latestDetail.getFeeWithoutTax() != null) {
            if (originalDetail.getFeeWithoutTax() == null || latestDetail.getFeeWithoutTax() == null) {
                dataList.add(Arrays.asList("feeWithoutTax", originalDetail.getId()));
            } else if (!originalDetail.getFeeWithoutTax().equals(latestDetail.getFeeWithoutTax())) {
                dataList.add(Arrays.asList("feeWithoutTax", originalDetail.getId()));
            }
        }
        if (originalDetail.getTax() != null || latestDetail.getTax() != null) {
            if (originalDetail.getTax() == null || latestDetail.getTax() == null) {
                dataList.add(Arrays.asList("tax", originalDetail.getId()));
            } else if (!originalDetail.getTax().equals(latestDetail.getTax())) {
                dataList.add(Arrays.asList("tax", originalDetail.getId()));
            }
        }
        if (originalDetail.getCheckCode() != null || latestDetail.getCheckCode() != null) {
            if (originalDetail.getCheckCode() == null || latestDetail.getCheckCode() == null) {
                dataList.add(Arrays.asList("checkCode", originalDetail.getId()));
            } else if (!originalDetail.getCheckCode().equals(latestDetail.getCheckCode())) {
                dataList.add(Arrays.asList("checkCode", originalDetail.getId()));
            }
        }
        if (originalDetail.getDraweeNo() != null || latestDetail.getDraweeNo() != null) {
            if (originalDetail.getDraweeNo() == null || latestDetail.getDraweeNo() == null) {
                dataList.add(Arrays.asList("draweeNo", originalDetail.getId()));
            } else if (!originalDetail.getDraweeNo().equals(latestDetail.getDraweeNo())) {
                dataList.add(Arrays.asList("draweeNo", originalDetail.getId()));
            }
        }
        if (originalDetail.getTitle() != null || latestDetail.getTitle() != null) {
            if (originalDetail.getTitle() == null || latestDetail.getTitle() == null) {
                dataList.add(Arrays.asList("title", originalDetail.getId()));
            } else if (!originalDetail.getTitle().equals(latestDetail.getTitle())) {
                dataList.add(Arrays.asList("title", originalDetail.getId()));
            }
        }
        if (originalDetail.getPayee() != null || latestDetail.getPayee() != null) {
            if (originalDetail.getPayee() == null || latestDetail.getPayee() == null) {
                dataList.add(Arrays.asList("payee", originalDetail.getId()));
            } else if (!originalDetail.getPayee().equals(latestDetail.getPayee())) {
                dataList.add(Arrays.asList("payee", originalDetail.getId()));
            }
        }
        if (originalDetail.getPayeeNo() != null || latestDetail.getPayeeNo() != null) {
            if (originalDetail.getPayeeNo() == null || latestDetail.getPayeeNo() == null) {
                dataList.add(Arrays.asList("payeeNo", originalDetail.getId()));
            } else if (!originalDetail.getPayeeNo().equals(latestDetail.getPayeeNo())) {
                dataList.add(Arrays.asList("payeeNo", originalDetail.getId()));
            }
        }
        if (originalDetail.getCardsignType() != null || latestDetail.getCardsignType() != null) {
            if (originalDetail.getCardsignType() == null || latestDetail.getCardsignType() == null) {
                dataList.add(Arrays.asList("cardsingType", originalDetail.getId()));
            } else if (!originalDetail.getCardsignType().equals(latestDetail.getCardsignType())) {
                dataList.add(Arrays.asList("cardsingType", originalDetail.getId()));
            }
        }
        if (originalDetail.getVatInvoiceCurrencyCode() != null || latestDetail.getVatInvoiceCurrencyCode() != null) {
            if (originalDetail.getVatInvoiceCurrencyCode() == null || latestDetail.getVatInvoiceCurrencyCode() == null) {
                dataList.add(Arrays.asList("vatInvoiceCurrencyCode", originalDetail.getId()));
            } else if (!originalDetail.getVatInvoiceCurrencyCode().equals(latestDetail.getVatInvoiceCurrencyCode())) {
                dataList.add(Arrays.asList("vatInvoiceCurrencyCode", originalDetail.getId()));
            }
        }
        if (originalDetail.getReceiptOwner() != null || latestDetail.getReceiptOwner() != null) {
            if (originalDetail.getReceiptOwner() == null || latestDetail.getReceiptOwner() == null) {
                dataList.add(Arrays.asList("receiptOwner", originalDetail.getId()));
            } else if (!originalDetail.getReceiptOwner().equals(latestDetail.getReceiptOwner())) {
                dataList.add(Arrays.asList("receiptOwner", originalDetail.getId()));
            }
        }
        if (originalDetail.getMoreInfo() != null || latestDetail.getMoreInfo() != null) {
            if (originalDetail.getMoreInfo() == null || latestDetail.getMoreInfo() == null) {
                dataList.add(Arrays.asList("moreInfo", originalDetail.getId()));
            } else if (!originalDetail.getMoreInfo().equals(latestDetail.getMoreInfo())) {
                dataList.add(Arrays.asList("moreInfo", originalDetail.getId()));
            }
        }
    }

    /**
     * 属性值比较，使用反射实现
     * 只支持扁平化对象比较
     * @param dataList
     * @param originalDetail
     * @param latestDetail
     */
    public static void compareFieldsWithReflection(List<List<String>> dataList, ReceiptDetail originalDetail, ReceiptDetail latestDetail) {
        try {
            PropertyDescriptor[] descriptors = Introspector.getBeanInfo(originalDetail.getClass(), ReceiptDetail.class).getPropertyDescriptors();
            for (PropertyDescriptor pd : descriptors) {
                String propertyName = pd.getName();
                Method readMethod = pd.getReadMethod();
                Object originalValue = readMethod.invoke(originalDetail);
                Object latestValue = readMethod.invoke(latestDetail);
                if (originalValue != null || latestValue != null) {
                    if (originalValue == null || latestValue == null) {
                        dataList.add(Arrays.asList(propertyName, originalDetail.getId()));
                    } else if (!originalValue.equals(latestValue)) {
                        dataList.add(Arrays.asList(propertyName, originalDetail.getId()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
