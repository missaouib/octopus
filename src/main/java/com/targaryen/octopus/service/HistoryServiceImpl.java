package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.BatchRepository;
import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.SourceFileRepository;
import com.targaryen.octopus.dto.BatchDto;
import com.targaryen.octopus.dto.SourceFileDto;
import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.util.status.SourceType;
import com.targaryen.octopus.vo.BatchVo;
import com.targaryen.octopus.vo.SourceRecruitProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {
    BatchRepository batchRepository;
    SourceFileRepository sourceFileRepository;

    @Autowired
    public HistoryServiceImpl(DaoFactory daoFactory) {
        this.batchRepository = daoFactory.getBatchRepository();
        this.sourceFileRepository = daoFactory.getSourceFileRepository();
    }

    public List<BatchVo> findAllBatches() {
        return batchRepository.findAll().stream()
                .sorted(Comparator.comparing(x -> x.getStartTime()))
                .map(x -> DataTransferUtil.BatchDtoToVo(x))
                .collect(Collectors.toList());
    }

    private List<SourceRecruitProgressVo> findRecordsByBatchIdGroupBySourceType(int batchId, int sourceType) {
        BatchDto batchDto;
        List<SourceFileDto> sourceFileDtos;
        try {
            batchDto = batchRepository.findByBatchId(batchId);
            sourceFileDtos = batchDto.getSourceFiles();
            if(sourceFileDtos == null)
                return new ArrayList<>();
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
        return sourceFileDtos.stream().filter(x -> sourceType == x.getSourceType())
                .map(x -> DataTransferUtil.SourceFileDtoToSourceRecruitProgressVo(x))
                .collect(Collectors.toList());
    }

    public List<SourceRecruitProgressVo> findRecordsByBatchIdGroupBySchool(int batchId) {
        return findRecordsByBatchIdGroupBySourceType(batchId, SourceType.SCHOOL);
    }

    public List<SourceRecruitProgressVo> findRecordsByBatchIdGroupByCity(int batchId) {
        return findRecordsByBatchIdGroupBySourceType(batchId, SourceType.CITY);
    }

    public List<SourceRecruitProgressVo> findRecordsByBatchIdGroupByPost(int batchId) {
        return findRecordsByBatchIdGroupBySourceType(batchId, SourceType.POST);
    }

    private List<SourceRecruitProgressVo> findSummaryRecordsGroupBySourceType(int sourceType) {
        List<SourceFileDto> sourceFileDtos;
        Map<String, Map<String, Integer>> summaryMap = new HashMap<>();
        List<SourceRecruitProgressVo> sourceRecruitProgressVos = new ArrayList<>();

        try {
            sourceFileDtos = sourceFileRepository.findAllBySourceType(sourceType);
            for(SourceFileDto s: sourceFileDtos) {
                if(!summaryMap.containsKey(s.getSourceName())) {
                    Map<String, Integer> sourceMap = new HashMap<>();
                    sourceMap.put("app", 0);
                    sourceMap.put("resume", 0);
                    sourceMap.put("interview", 0);
                    sourceMap.put("offer", 0);
                    sourceMap.put("entry", 0);
                    summaryMap.put(s.getSourceName(), sourceMap);
                }
                int old;
                old = summaryMap.get(s.getSourceName()).get("app");
                summaryMap.get(s.getSourceName()).put("app", old + s.getApplicationNum());
                old = summaryMap.get(s.getSourceName()).get("resume");
                summaryMap.get(s.getSourceName()).put("resume", old + s.getFilterPassNum());
                old = summaryMap.get(s.getSourceName()).get("interview");
                summaryMap.get(s.getSourceName()).put("interview", old + s.getInterviewPassNum());
                old = summaryMap.get(s.getSourceName()).get("offer");
                summaryMap.get(s.getSourceName()).put("offer", old + s.getOfferNum());
                old = summaryMap.get(s.getSourceName()).get("entry");
                summaryMap.get(s.getSourceName()).put("entry", old + s.getEntryNum());
            }

            for(String key: summaryMap.keySet()) {
                Map<String, Integer> tmp_map = summaryMap.get(key);
                sourceRecruitProgressVos.add(
                        new SourceRecruitProgressVo.Builder()
                                .sourceName(key)
                                .sourceType(sourceType)
                                .applicationNum(tmp_map.get("app"))
                                .filterPassNum(tmp_map.get("resume"))
                                .interviewPassNum(tmp_map.get("interview"))
                                .offerNum(tmp_map.get("offer"))
                                .entryNum(tmp_map.get("entry")).build());
            }

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return sourceRecruitProgressVos;
    }

    public List<SourceRecruitProgressVo> findSummaryRecordsGroupBySchool() {
        return findSummaryRecordsGroupBySourceType(SourceType.SCHOOL);
    }

    public List<SourceRecruitProgressVo> findSummaryRecordsGroupByCity() {
        return findSummaryRecordsGroupBySourceType(SourceType.CITY);
    }

    public List<SourceRecruitProgressVo> findSummaryRecordsGroupByPost() {
        return findSummaryRecordsGroupBySourceType(SourceType.POST);
    }
}
