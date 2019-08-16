package com.helios.gao.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author : gaozhiwen
 * @date : 2019/6/26
 */
public class ReportUtil {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\费用明细报表修改数据.csv");
        String desc = "C:\\Users\\admin\\Desktop\\prod费用明细报表字段顺序修改.sql";
        String reportId = "3";  //注意修改reportId
        List<String> tenantIds = Arrays.asList("948201233380810753"
                ,"948201244671877122"
                ,"948201245120667649"
                ,"948201250237718529"
                ,"948201303228555265"
                ,"948201306680467457"
                ,"948201308731482113"
                ,"948201309985579009"
                ,"948201313622040577"
                ,"950251472354811906"
                ,"950312975699300354"
                ,"961124941887971330"
                ,"961434810021171202"
                ,"970843572888690689"
                ,"987168081853882369"
                ,"994033346768035841"
                ,"996619501883641857"
                ,"999917021623799809"
                ,"1010002880096133122"
                ,"1011873416732536833"
                ,"1014031756656951298"
                ,"1018697125398818818"
                ,"1023772638748282882"
                ,"1027856661338628097"
                ,"1033896015680991233"
                ,"1037159639727915010"
                ,"1042691641222811649"
                ,"1050674052720173057"
                ,"1058376177862918146"
                ,"1058376429328220161"
                ,"1064856800049364993"
                ,"1070136461011795970"
                ,"1071945608919113729"
                ,"1073568157301145602"
                ,"1074507382863474690"
                ,"1078540108562980866"
                ,"1078552643898990594"
                ,"1083274634051911682"
                ,"1090511158535430145"
                ,"1095141862150828033"
                ,"1100593311122247682"
                ,"1109288421443842049"
                ,"1113374893516046338"
                ,"1130838464374857730");
        InputStream inputStream = new FileInputStream(file);
        CSVParser parser = new CSVParser(new InputStreamReader(inputStream, "GBK"), CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();
        PrintStream printStream = new PrintStream(new FileOutputStream(desc));
        for (String tenantId : tenantIds) {
            for (int i = 1; i < records.size(); i++) {
                CSVRecord csvRecord = records.get(i);
                StringBuilder builder = new StringBuilder("update atl_report_column_customized set column_seq_no =");
                builder.append("'").append(csvRecord.get(1)).append("'")
                        .append("where report_id =").append("'").append(reportId).append("'")
                        .append("and tenant_id =").append("'").append(tenantId).append("'")
                        .append("and column_prop_name =").append("'").append(csvRecord.get(0)).append("';");
//                System.out.println(builder.toString());
                printStream.println(builder.toString());
            }
            printStream.println();
        }
        parser.close();
        inputStream.close();
        printStream.close();
    }
}
