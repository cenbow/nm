package com.nm.impl.usercenter;

import com.hs.base.exception.ServiceException;
import com.hs.commons.attach.Attachment;
import com.hs.commons.attach.AttachmentApi;
import com.hs.commons.attach.OssUtil;
import com.hs.commons.attach.tansfer.IFileTransfer;
import com.hs.commons.tools.SpringContextHolder;
import com.hs.system.service.AttachmentService;
import com.hs.utils.BeanUtils;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.nm.base.wechat.file.WechatFileItem;
import com.nm.cmd.OCOssAttach;
import com.nm.service.usercenter.NOssFileTransfer;
import com.nm.util.NFileItem;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by MIfengHe on 2017/3/21.
 */
@Service
public class NOssFileTransferImpl implements NOssFileTransfer {
    private static final Logger LOGGER= LoggerFactory.getLogger(NOssFileTransferImpl.class);

    @Resource
    private AttachmentService attachmentService;

    @Autowired
    private IFileTransfer fileTransfer;

    @Override
    public Attachment getAttachment(MultipartFile multipartFile) {
        return this.getAttachment(multipartFile, null);
    }

    @Override
    public Attachment getAttachment(MultipartFile multipartFile, String extension) {
        String originalFilename = multipartFile.getOriginalFilename();
       // String filename = FilenameUtils.getBaseName(originalFilename);
        if (StringUtils.isBlank(extension)) {
            extension = FilenameUtils.getExtension(originalFilename);
        }

        Attachment attachment = new Attachment();
        try {
            attachment.setFileSize(new Long(multipartFile.getInputStream().available()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachment.setContentType(multipartFile.getContentType());
        attachment.setOriginalName(multipartFile.getOriginalFilename());

        if (StringUtils.isBlank(extension)) {
            if (!com.hs.utils.StringUtils.isBlank(originalFilename) && originalFilename.lastIndexOf(".") != -1) {
                extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            }
        }

        String suffix = StringUtils.isBlank(extension) ? StringUtils.EMPTY : "." + extension;
        attachment.setPresentName(RandomUtil.getUUID() + suffix);

        attachment.setOriginalName(FilenameUtils.getBaseName(originalFilename) + suffix);

        attachment.setPhysicalAddress(ParamUtils.getParam("basePhysicalAddressDir") + com.hs.utils.DateUtils.getCurDate() + "/" + attachment.getPresentName());
        attachment.setNetworkAddress(ParamUtils.getParam("baseNetworkAddressDir") + attachment.getPresentName());
        return attachment;

    }
    @Override
    public Boolean canUpload(MultipartFile multipartFile) {
        return this.canUpload(multipartFile, null);
    }

    @Override
    public boolean canUpload(MultipartFile multipartFile, String extension) {
        if (StringUtils.isBlank(extension)) {
            extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        }
        return attachmentService.canUpload(multipartFile, extension);
    }

    @Transactional
    protected Attachment uploadAttachment(InputStream in, Attachment attachment) {
        try {
            //InputStream in = file.getInputStream();
            this.fileTransfer.upload(in, attachment);
            in.close();
            AttachmentApi sysAttachServer = (AttachmentApi) SpringContextHolder.getBean("sysAttachServer");
            attachment = sysAttachServer.save(attachment);
            return attachment;
        } catch (ServiceException var5) {
            throw var5;
        } catch (Exception var6) {
            LOGGER.error("上传文件失败：" + var6.getMessage(), var6);
            throw new ServiceException("上传文件失败");
        }
    }
    @Override
    @Transactional
    public Attachment uploadAttachment(MultipartFile multipartFile) {
        return attachmentService.uploadAttachment(multipartFile);
    }
    @Override
    public CommonsMultipartFile uploadAttachmentZoov1(InputStream inputStream){
        BufferedImage thumbnail = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        CommonsMultipartFile multipartFile = null;
        try {
            thumbnail = Thumbnails.of(inputStream)
                    .scale(0.25f)
                    .outputFormat("jpg")
                    .asBufferedImage();
            ImageIO.write(thumbnail, "jpg", os);
            inputStream = new ByteArrayInputStream(os.toByteArray());
            WechatFileItem fileItem = new WechatFileItem();
            fileItem.setInputStream(inputStream);
            fileItem.setContentType(multipartFile.getContentType());
            fileItem.setSize(multipartFile.getSize());
            fileItem.setName(multipartFile.getName());
            multipartFile = new CommonsMultipartFile(fileItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return multipartFile;
    }
    @Override
    @Transactional
    public Attachment uploadAttachmentZoom(MultipartFile multipartFile, Attachment attachment) {
        String originalFilename = multipartFile.getOriginalFilename(); //文件名
        String extension = FilenameUtils.getExtension(originalFilename); //扩展名
        String baseName = FilenameUtils.getBaseName(originalFilename);
        ArrayList array = new ArrayList();
        array.add("jpg");array.add("gif");array.add("png");array.add("bmp");array.add("jpge");
        if (StringUtils.isBlank(extension)) {
            extension = FilenameUtils.getExtension(originalFilename);
        }
        this.canUpload(multipartFile, extension); //验证上传大小
        attachment = this.getAttachment(multipartFile);
        boolean isImage=true;
        //File files= new File("d:/images");
        File files= new File("/data/deploy/tempFile");
        InputStream inputStream=null;
        ByteArrayInputStream byteArrayInputStream=null;
        //处理原图
        if(!files.exists()){
            files.mkdir();
        }
        try {
        //图片附件
        if (array.contains(extension)) {
            Thumbnails.of(multipartFile.getInputStream())
                    .scale(1f)
                    .toFile(files+"/"+attachment.getOriginalName());
            inputStream=new FileInputStream(files+"/"+attachment.getOriginalName());
            byte[] byteArrayFile = IOUtils.toByteArray(inputStream);
            byteArrayInputStream= new ByteArrayInputStream(byteArrayFile);
            attachment.setFileSize(new Long(byteArrayInputStream.available()));
            byteArrayFile.clone();
            FileInputStream fileInputStream1=new FileInputStream(files+"/"+attachment.getOriginalName());

            //inputStream = FileHelper.zoomToInputStream(multipartFile, 100, 100, "jpg");
            BufferedImage thumbnail = Thumbnails.of(fileInputStream1)
                    .scale(0.25f)
                    .asBufferedImage();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(thumbnail, extension, imOut);
            inputStream = new ByteArrayInputStream(bs.toByteArray());
            attachment.setBusiRemark("thumb");
            bs.close();
            imOut.close();
            fileInputStream1.close();
            //上传原始附件
            attachment = this.uploadAttachment(byteArrayInputStream, attachment);
            if (!isImage || inputStream == null) return attachment;
            //图片上传

            //byteArrayInputStream.close();
            NFileItem nFileItem = new NFileItem();
            nFileItem.setInputStream(inputStream);
            nFileItem.setSize(new Long(inputStream.available()));
            nFileItem.setFieldName("file");
            //nFileItem.setFieldName(baseName + "_thumb." + extension);
            nFileItem.setContentType(attachment.getContentType());

            //inputStream = FileHelper.zoomToInputStream(multipartFile, 100, 100, "jpg");
            Attachment zAttach = new Attachment();
            BeanUtils.copyProperties(attachment, zAttach);

            zAttach.setFileSize(new Long(inputStream.available()));
            zAttach.setOriginalName(baseName + "_thumb." + extension);

            String presentName = zAttach.getPresentName();
            String networkAddress = zAttach.getNetworkAddress();
            String physicalAddress = zAttach.getPhysicalAddress();

            String suffix = FilenameUtils.getExtension(presentName);
            if (StringUtils.isNotBlank(suffix)) suffix = "." + suffix;

            zAttach.setPresentName(FilenameUtils.getBaseName(presentName) + "_thumb" + suffix);
            zAttach.setNetworkAddress(FilenameUtils.getPath(networkAddress) + FilenameUtils.getBaseName(networkAddress) + "_thumb" + suffix);
            zAttach.setPhysicalAddress(FilenameUtils.getPath(physicalAddress) + FilenameUtils.getBaseName(physicalAddress)+ "_thumb" + suffix);

            fileTransfer.upload(inputStream, zAttach);
            if(null != byteArrayInputStream){
                byteArrayInputStream.close();
            }
            if(null != inputStream){
                inputStream.close();
            }
            File file=new File("/data/deploy/tempFile"+attachment.getOriginalName());
            if(file.exists()){
                boolean tr=file.delete();
                System.out.println("==============="+tr+"==============");
            }
           // OCOssAttach ocOsssAttach = this.getOCOsssAttach(attachment.getId());
            attachment.setOriginalName(FilenameUtils.getBaseName(originalFilename) + suffix);
        }else{
            //非图片附件
            attachment = this.uploadAttachment(multipartFile.getInputStream(), attachment);
        }

        } catch (IOException e) {
            LOGGER.error("附件原件上传失败：", e);
        }finally {
            File file=new File("/data/deploy/tempFile"+attachment.getOriginalName());
            if(file.exists()){
                boolean tr=file.delete();
                System.out.println("==============="+tr+"==============");
            }
        }

        return attachment;
    }

    @Override
    public String getPresignedURL(String attNo) {
        return OssUtil.getPresignedUrl(attNo);
    }

    @Override
    public String getThumbPresignedURL(String attNo){
        return this.getThumbPresignedURL(attNo, null);
    }

    /**
     * @param
     * @return
     */
    @Override
    public String getThumbPresignedURL(String attNo, Integer mins) {
        if (mins == null || mins <= 0) {
            mins = 10;
        }

        AttachmentApi sysAttachServer = (AttachmentApi) SpringContextHolder.getBean("sysAttachServer");
        Attachment attach = sysAttachServer.getById(attNo);


        String busiRemark = attach.getBusiRemark();
        if (StringUtils.isNotBlank(busiRemark) && busiRemark.contains("thumb")) {
            String presentName = attach.getPresentName();
            String extension = FilenameUtils.getExtension(presentName);
            String suffix = StringUtils.isBlank(extension) ? "." + extension : StringUtils.EMPTY;

            String physicalAddress = attach.getPhysicalAddress();
            String networkAddress = attach.getNetworkAddress();

            physicalAddress = FilenameUtils.getPath(physicalAddress)
                    + FilenameUtils.getBaseName(physicalAddress)
                    + "_thumb"
                    + suffix;

            networkAddress = FilenameUtils.getPath(physicalAddress)
                    + FilenameUtils.getBaseName(physicalAddress)
                    + "_thumb"
                    + suffix;

            attach.setPhysicalAddress(physicalAddress);
            attach.setNetworkAddress(networkAddress);

            return OssUtil.generatePresignedUrl(attach, mins);
        }

        return null;

    }
    /**
     * 获取签名阿里云附件
     * @param attNo 附件编号
     * @param
     * @return
     */
    @Override
    public OCOssAttach getOCOsssAttach(String attNo) {
        return this.getOCOsssAttach(attNo, null);
    }

    /**
     * 获取签名阿里云附件
     * @param attNo 附件编号
     * @param mins 分钟
     * @return
     */
    @Override
    public OCOssAttach getOCOsssAttach(String attNo, Integer mins) {
        String attName = null;
        String attFile = null;
        String attFileThumb = null;

        if (mins == null || mins <= 0) {
            mins = 10;
        }

        AttachmentApi sysAttachServer = (AttachmentApi) SpringContextHolder.getBean("sysAttachServer");
        Attachment attach = sysAttachServer.getById(attNo);

        String presignedUrl = OssUtil.generatePresignedUrl(attach, mins);
        attFile = presignedUrl;

        String busiRemark = attach.getBusiRemark();
        if (StringUtils.isNotBlank(busiRemark) && busiRemark.contains("thumb")) {
            String presentName = attach.getPresentName();
            String extension = FilenameUtils.getExtension(presentName);
            String suffix = StringUtils.isNotBlank(extension) ? "." + extension : StringUtils.EMPTY;

            String physicalAddress = attach.getPhysicalAddress();
            String networkAddress = attach.getNetworkAddress();

            physicalAddress = FilenameUtils.getPath(physicalAddress)
                    + FilenameUtils.getBaseName(physicalAddress)
                    + "_thumb"
                    + suffix;

            networkAddress = FilenameUtils.getPath(networkAddress)
                    + FilenameUtils.getBaseName(networkAddress)
                    + "_thumb"
                    + suffix;

            attach.setPhysicalAddress(physicalAddress);
            attach.setNetworkAddress(networkAddress);
            attFileThumb = OssUtil.generatePresignedUrl(attach, mins);
        }

        String contentType = attach.getContentType();
        if (StringUtils.isBlank(contentType)) contentType = StringUtils.EMPTY;

        boolean isImage = contentType.toLowerCase().contains("image");
        String originalName = attach.getOriginalName();
        if (StringUtils.isBlank(originalName)) {
            originalName = attach.getPresentName();
            if (StringUtils.isBlank(originalName)) {
                originalName = "未知文件名";
            }
        }
        if (isImage) {
            String extension = FilenameUtils.getExtension(originalName);
            if (StringUtils.isBlank(extension) || !Arrays.asList("jpg|jpeg|png|gif|bmp".split("|")).contains(extension.toLowerCase())) {
                extension = "jpg";
            }
            attName = FilenameUtils.getBaseName(originalName) + "." + extension;
        } else {
            attName = originalName;
        }

        OCOssAttach ocOssAttach = new OCOssAttach();
        ocOssAttach.setAttName(attName);
        ocOssAttach.setAttFile(attFile);
        ocOssAttach.setAttFileThumb(attFileThumb);
        if (isImage) {
            ocOssAttach.setIsImage(true);
        }
        return ocOssAttach;
    }

}
