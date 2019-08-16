package com.helios.gao;

import com.helios.gao.domain.ContractDTO;
import com.helios.gao.domain.ProjectDTO;
import com.helios.gao.domain.ResourcePlan;
import com.helios.gao.domain.enumeration.*;
import com.helios.gao.util.ZimsClient;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @author : gaozhiwen
 * @date : 2018/12/31
 */
@Service
public class InitData {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\admin\\Desktop\\内部运营系统\\更新\\初始化数据_1229_2.xlsx");
        Map<String, Long> customNameMap = new HashMap<>(16);
        Map<String, Long> contractMap = new HashMap<>(16);
        try {
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //第一张表是合同表
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                System.out.println(i);
                ContractDTO dto = new ContractDTO();
                dto.setContractCode(row.getCell(0).toString());
                dto.setType(ContractType.parse(row.getCell(1).toString()).getId());
                if (customNameMap.get(row.getCell(2).toString()) != null) {
                    dto.setCustomerId(customNameMap.get(row.getCell(2).toString()));
                }
                dto.setName(row.getCell(2).toString());
                dto.setTaxPayerId(row.getCell(3).toString());
                dto.setAddress(row.getCell(4).toString());
                dto.setTelPhone(row.getCell(5) == null ? null : row.getCell(5).toString());
                dto.setBankName(row.getCell(6).toString());
                dto.setBankAccount(row.getCell(7).toString());
                dto.setSalesId(ZimsClient.getSalesId(row.getCell(8).toString()));
                dto.setTotalAmount(BigDecimal.valueOf(Double.valueOf(row.getCell(9).toString())));
                dto.setCode(CurrencyCode.CNY);
                dto.setTotalWorkload(BigDecimal.valueOf(Double.valueOf(row.getCell(11).toString())));
                dto.setSignAccountCount(Double.valueOf(row.getCell(12).toString()).intValue());
                dto.setOpenAccountCount(Double.valueOf(row.getCell(13).toString()).intValue());
                dto.setSource(ContractSource.parse(row.getCell(14).toString()).getId());
                dto.setSignSubject(row.getCell(15).toString());
                dto.setSignDate(row.getCell(16).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                dto = ZimsClient.insertContract(dto);
                customNameMap.put(dto.getName(), dto.getCustomerId());
                contractMap.put(dto.getContractCode(), dto.getId());
                Thread.sleep(2000L);
            }

            //第二张表是项目表
            sheet = workbook.getSheetAt(1);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                System.out.println(i);
                ProjectDTO dto = new ProjectDTO();
                dto.setContractId(contractMap.get(row.getCell(0).toString()));
                dto.setRegionGroupId(RegionGroup.parse(row.getCell(1).toString()).getId());
                dto.setCustomerSuccessManagerId(CustomerSuccessManager.parse(row.getCell(2).toString()).getId());
                ResourcePlan resourcePlan = new ResourcePlan();
                resourcePlan.setUserId(ZimsClient.getSalesId(row.getCell(3).toString()));
                resourcePlan.setRoleId(995958119030665217L);
                resourcePlan.setOnBoardDate(LocalDate.now());
                resourcePlan.setLeaveDate(LocalDate.now());
                resourcePlan.setOnSiteMandays(BigDecimal.ZERO);
                resourcePlan.setRemoteMandays(BigDecimal.ZERO);
                resourcePlan.setTotalMandays(BigDecimal.ZERO);
                dto.setResourcePlans(Collections.singletonList(resourcePlan));
                dto.setMilestonePlans(Collections.emptyList());
                dto.setLocationCode(LocationCode.parse(row.getCell(4).toString()).getCode());
                dto.setName(row.getCell(5).toString());
                dto.setPlannedReleaseDate(row.getCell(7).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                ZimsClient.insertProject(dto);
                Thread.sleep(2000L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
