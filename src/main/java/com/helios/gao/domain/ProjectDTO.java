package com.helios.gao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author : gaozhiwen
 * @date : 2019/1/1
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDTO {
    private Long id;
    private Long groupTypeId;
    private Long contractId;
    private Long regionGroupId;
    private String regionGroupName;
    private Long locationId;
    private String locationCode;
    private String locationName;
    private String name;
    private String code;
    private String path;
    private LocalDate plannedReleaseDate;
    private Long customerSuccessManagerId;
    private String CSMName;
    private List<MilestonePlan> milestonePlans;
    private List<ResourcePlan> resourcePlans;
    private Long currentMilestoneId;
    private Integer version;
    private Boolean isEnabled;
    private Boolean isDeleted;
    private ZonedDateTime createdDate;
    private Long createdBy;
    private ZonedDateTime lastUpdatedDate;
    private Long lastUpdatedBy;
    private ContractDTO contractDTO;
    private String pmName;
    private String roleInfo;
}
