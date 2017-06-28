package com.nm.service.usercenter;

import com.hs.commons.attach.Attachment;
import com.nm.cmd.OCOssAttach;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;

/**
 * Created by MIfengHe on 2017/3/21.
 */
public interface NOssFileTransfer {


    Attachment getAttachment(MultipartFile multipartFile);

    Attachment getAttachment(MultipartFile multipartFile, String extension);

    Boolean canUpload(MultipartFile multipartFile);

    boolean canUpload(MultipartFile multipartFile, String extension);

    Attachment uploadAttachment(MultipartFile multipartFile);

    CommonsMultipartFile uploadAttachmentZoov1(InputStream inputStream);

    Attachment uploadAttachmentZoom(MultipartFile multipartFile, Attachment attachment);

    String getPresignedURL(String attNo);

    String getThumbPresignedURL(String attNo);

    String getThumbPresignedURL(String attNo, Integer mins);

    OCOssAttach getOCOsssAttach(String attNo);

    OCOssAttach getOCOsssAttach(String attNo, Integer mins);

}
