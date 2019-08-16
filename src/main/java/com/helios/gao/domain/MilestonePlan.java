package com.helios.gao.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author : gaozhiwen
 * @date : 2019/1/2
 */
@Data
public class MilestonePlan {
    private Long milestone;
    private LocalDate plannedCompleteDate;
    private Boolean isCollectionInvolved;
    private LocalDate plannedIssueInvoiceDate;
    private BigDecimal invoiceAmount;
    private Boolean isAcceptanceReportNeeded;
}
