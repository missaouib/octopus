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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl {
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

    private List<SourceRecruitProgressVo> findRecordsByBatchIdGrouBySourceType(int batchId, int sourceType) {
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
        return findRecordsByBatchIdGrouBySourceType(batchId, SourceType.SCHOOL);
    }

    public List<SourceRecruitProgressVo> findRecordsByBatchIdGroupByCity(int batchId) {
        return findRecordsByBatchIdGrouBySourceType(batchId, SourceType.CITY);
    }

    public List<SourceRecruitProgressVo> findRecordsByBatchIdGroupByPost(int batchId) {
        return findRecordsByBatchIdGrouBySourceType(batchId, SourceType.POST);
    }
}
