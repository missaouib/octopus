package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.AnnouncementVo;

import java.util.List;

public interface AnnouncementService {
    /**
     * list all announcement by batchId
     *
     */
    List<AnnouncementVo> listAllAnnouncement();

    /**
     * list public announcement by batchId
     *
     */
    List<AnnouncementVo> listPublicAnnouncement();

    /**
     * list hr announcement
     *
     */
    List<AnnouncementVo> listHRAnnouncement();

    /**
     * create new announcement, announcementVo should include announcementTitle, announcementDetail
     *
     */
    int createNewAnnouncement(AnnouncementVo announcementVo);

    /**
     * update announcement, announcementVo should include announcementId, announcementTitle, announcementDetail, annoucementType
     *
     */
    int updateAnnouncement(AnnouncementVo announcementVo);

    /**
     * publish announcement by announcementId
     *
     */
    int publishAnnouncementById(int announcementId);

    /**
     * revoke announcement by announcementId
     *
     */
    int revokeAnnouncementById(int announcementId);

    AnnouncementVo findAnnouncementById(int announcementId);
}
