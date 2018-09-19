package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by zhouy on 2018/9/18.
 */
@Data
@NoArgsConstructor
public class AnnouncementEntity {

    private int announcementId;
    private int batchId;
    private String announcementTitle;
    private String announcementDetail;
    private Date createTime;
    private Date publishedTime;
    private Date lastModifyTime;
    private boolean announcementType;
    private int announcementStatus;
}
