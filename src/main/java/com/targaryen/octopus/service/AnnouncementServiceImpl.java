package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.AnnouncementRepository;
import com.targaryen.octopus.dao.BatchRepository;
import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dto.AnnouncementDto;
import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.AnnouncementStauts;
import com.targaryen.octopus.util.status.AnnouncementType;
import com.targaryen.octopus.vo.AnnouncementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private AnnouncementRepository announcementRepository;
    private BatchRepository batchRepository;

    @Autowired
    public AnnouncementServiceImpl(DaoFactory daoFactory) {
        announcementRepository = daoFactory.getAnnouncementRepository();
        batchRepository = daoFactory.getBatchRepository();
    }

    @Override
    public List<AnnouncementVo> listAllAnnouncement() {
        return announcementRepository.findAll().stream()
                .map(n -> DataTransferUtil.AnnouncementDtoToVo(n))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementVo> listPublicAnnouncement() {
        return announcementRepository.findAll().stream()
                .filter(n -> AnnouncementStauts.PUBLISHED.equals(n.getAnnouncementStatus()))
                .map(n -> DataTransferUtil.AnnouncementDtoToVo(n))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementVo> listHRAnnouncement() {
        return announcementRepository.findAll().stream()
                .filter(n -> AnnouncementType.HR_VIEW.equals(n.getAnnouncementType()) &&
                        AnnouncementStauts.PUBLISHED.equals(n.getAnnouncementStatus()))
                .map(n -> DataTransferUtil.AnnouncementDtoToVo(n))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public int createNewAnnouncement(AnnouncementVo announcementVo) {
        try {
            AnnouncementDto announcementDto = new AnnouncementDto();
            announcementDto.setAnnouncementTitle(announcementVo.getAnnouncementTitle());
            announcementDto.setAnnouncementDetail(announcementVo.getAnnouncementDetail());
            announcementDto.setAnnouncementType(announcementVo.getAnnouncementType());
            announcementDto.setBatch(batchRepository.findByBatchId(1));
            announcementDto.setAnnouncementStatus(AnnouncementStauts.DRAFT);
            announcementDto.setCreateTime(Calendar.getInstance().getTime());
            announcementDto.setLastModifyTime(Calendar.getInstance().getTime());
            announcementRepository.save(announcementDto);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Transactional
    @Override
    public int updateAnnouncement(AnnouncementVo announcementVo) {
        try {
            AnnouncementDto announcementDto =
                    announcementRepository.findByAnnouncementId(announcementVo.getAnnouncementId());
            announcementDto.setAnnouncementTitle(announcementVo.getAnnouncementTitle());
            announcementDto.setAnnouncementDetail(announcementVo.getAnnouncementDetail());
            announcementDto.setAnnouncementType(announcementVo.getAnnouncementType());
            announcementDto.setLastModifyTime(Calendar.getInstance().getTime());
            announcementRepository.save(announcementDto);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Transactional
    @Override
    public int publishAnnouncementById(int announcementId) {
        try {
            AnnouncementDto announcementDto =
                    announcementRepository.findByAnnouncementId(announcementId);
            announcementDto.setAnnouncementStatus(AnnouncementStauts.PUBLISHED);
            announcementDto.setPublishedTime(Calendar.getInstance().getTime());
            announcementRepository.save(announcementDto);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Transactional
    @Override
    public int revokeAnnouncementById(int announcementId) {
        try {
            AnnouncementDto announcementDto =
                    announcementRepository.findByAnnouncementId(announcementId);
            announcementDto.setAnnouncementStatus(AnnouncementStauts.DRAFT);
            announcementDto.setPublishedTime(Calendar.getInstance().getTime());
            announcementRepository.save(announcementDto);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    public AnnouncementVo findAnnouncementById(int announcementId){

        try {
            AnnouncementDto announcementDto =
                    announcementRepository.findByAnnouncementId(announcementId);
            AnnouncementVo announcementVo = DataTransferUtil.AnnouncementDtoToVo(announcementDto);
            return announcementVo;
        } catch (DataAccessException e) {
            return new AnnouncementVo.Builder().build();
        }
    }
}
