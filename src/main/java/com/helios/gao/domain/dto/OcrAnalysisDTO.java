package com.helios.gao.domain.dto;

import com.helios.gao.domain.ReceiptDetail;
import lombok.Data;

import java.util.List;

/**
 * @author : gaozhiwen
 * @date : 2019/7/8
 */
@Data
public class OcrAnalysisDTO {
    /**
     * 解析成bean的数据
     */
    List<ReceiptDetail> details;
    /**
     * 解析失败的数据
     */
    List<String> errors;
}
