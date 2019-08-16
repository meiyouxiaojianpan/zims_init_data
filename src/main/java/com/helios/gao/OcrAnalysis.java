package com.helios.gao;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.helios.gao.domain.ReceiptDetail;
import com.helios.gao.domain.dto.OcrAnalysisDTO;
import com.helios.gao.util.JsonUtil;
import com.helios.gao.util.OcrAnalysisUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

/**
 * @author : gaozhiwen
 * @date : 2019/7/4
 */
public class OcrAnalysis {
    public static void main(String[] args) throws FileNotFoundException {
        OcrAnalysis analysis = new OcrAnalysis();
        OcrAnalysisDTO analysisDTO = analysis.importData("C:\\Users\\admin\\Desktop\\ocr准确性数据.csv");
        analysis.writerExcel(analysisDTO);
    }

    private OcrAnalysisDTO importData(String filePath) {
        File file = new File(filePath);
        InputStream inputStream = null;
        CSVParser parser = null;
        List<CSVRecord> records = new ArrayList<>();
        try {
            inputStream = new FileInputStream(file);
            parser = new CSVParser(new InputStreamReader(inputStream, "UTF-8"), CSVFormat.DEFAULT);
            records = parser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (parser != null) {
                try {
                    parser.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return analysisData(records);
    }

    private OcrAnalysisDTO analysisData(List<CSVRecord> records) {
        OcrAnalysisDTO ocrAnalysisDTO = new OcrAnalysisDTO();
        List<ReceiptDetail> details = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        ReceiptDetail originalDetail;
        ReceiptDetail latestDetail;
        for (int i = 1; i < records.size(); i++) {
            CSVRecord csvRecord = records.get(i);
            System.out.println(csvRecord.get(0));
            try {
                originalDetail = parseData(csvRecord.get(6));
                latestDetail = parseData(csvRecord.get(7));
            } catch (ArrayIndexOutOfBoundsException e) {
                errors.add(csvRecord.get(0));
                continue;
            }
            originalDetail.setId(csvRecord.get(0));
            latestDetail.setId(csvRecord.get(0));
            details.add(originalDetail);
            details.add(latestDetail);
        }
        ocrAnalysisDTO.setDetails(details);
        ocrAnalysisDTO.setErrors(errors);
        return ocrAnalysisDTO;
    }

    private ReceiptDetail parseData(String detail) {
        String s1 = detail.substring(0, detail.indexOf(",moreInfo="));
        String s2 = detail.substring(detail.indexOf(",moreInfo=") + 1);
        String[] split = s1.split(",");
        Map<String, String> resultMap = new HashMap<>();
        for (String ss : split) {
            String[] split1 = ss.split("=", 2);
            resultMap.put(split1[0], split1[1]);
        }
        String[] split2 = s2.split("=", 2);
        resultMap.put(split2[0], split2[1]);
        return JsonUtil.mapToObject(resultMap, ReceiptDetail.class);
    }

    private void writerExcel(OcrAnalysisDTO ocrAnalysisDTO) throws FileNotFoundException {
        assert ocrAnalysisDTO != null;
        assert ocrAnalysisDTO.getDetails().size() > 0;
        List<List<String>> dataList = new LinkedList<>();
        List<ReceiptDetail> details = ocrAnalysisDTO.getDetails();
        for (int i = 0; i < details.size(); ) {
            OcrAnalysisUtil.compareFields(dataList, details.get(i), details.get(i + 1));
            i += 2;
        }
        OutputStream outputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\ocr准确性分析数据.xlsx");
        try {
            ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);

            Sheet sheet1 = new Sheet(1, 0, ReceiptDetail.class);
            sheet1.setSheetName("数据对比表");
            writer.write(details, sheet1);

            Sheet sheet2 = new Sheet(2, 0);
            sheet2.setSheetName("不一致分析表");
            writer.write0(dataList, sheet2);

            if (CollectionUtil.isNotEmpty(ocrAnalysisDTO.getErrors())) {
                Sheet sheet3 = new Sheet(3, 0);
                sheet3.setSheetName("不可分析数据");
                List<List<String>> errors = new ArrayList<>();
                errors.add(ocrAnalysisDTO.getErrors());
                writer.write0(errors, sheet3);
            }

            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
