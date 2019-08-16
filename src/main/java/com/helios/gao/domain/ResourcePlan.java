package com.helios.gao.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author : gaozhiwen
 * @date : 2019/1/1
 */
@Data
public class ResourcePlan {
    private Long userId;
    private LocalDate onBoardDate;
    private LocalDate leaveDate;
    private BigDecimal onSiteMandays;
    private BigDecimal remoteMandays;
    private BigDecimal totalMandays;
    private Long roleId;
}
