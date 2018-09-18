package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhouy on 2018/9/18.
 */

@Data
@NoArgsConstructor
public class UploadFileEntity {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
