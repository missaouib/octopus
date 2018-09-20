package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.SourceRecruitProgressVo;

import java.util.List;

public interface HistoryService {
    List<SourceRecruitProgressVo> findRecordsByBatchIdGroupBySchool(int batchId);
    List<SourceRecruitProgressVo> findRecordsByBatchIdGroupByCity(int batchId);
    List<SourceRecruitProgressVo> findRecordsByBatchIdGroupByPost(int batchId);
    List<SourceRecruitProgressVo> findSummaryRecordsGroupBySchool();
    List<SourceRecruitProgressVo> findSummaryRecordsGroupByCity();
    List<SourceRecruitProgressVo> findSummaryRecordsGroupByPost();
}
