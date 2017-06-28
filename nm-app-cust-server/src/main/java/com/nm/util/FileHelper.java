package com.nm.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by MIfengHe on 2017/3/21.
 */
public class FileHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

    public final static Map<String, String> FILE_TYPE_DICT = new HashMap<String, String>();
    
    static{
        getAllFileType();  //初始化文件类型信息    
    }

    private static void getAllFileType() {
        FILE_TYPE_DICT.put("jpg", "FFD8FF"); //JPEG (jpg)    
        FILE_TYPE_DICT.put("png", "89504E47");  //PNG (png)    
        FILE_TYPE_DICT.put("gif", "47494638");  //GIF (gif)    
        FILE_TYPE_DICT.put("tif", "49492A00");  //TIFF (tif)    
        FILE_TYPE_DICT.put("bmp", "424D"); //Windows Bitmap (bmp)    
        FILE_TYPE_DICT.put("dwg", "41433130"); //CAD (dwg)    
        FILE_TYPE_DICT.put("html", "68746D6C3E");  //HTML (html)    
        FILE_TYPE_DICT.put("rtf", "7B5C727466");  //Rich Text Format (rtf)    
        FILE_TYPE_DICT.put("xml", "3C3F786D6C");
        FILE_TYPE_DICT.put("zip", "504B0304");
        FILE_TYPE_DICT.put("rar", "52617221");
        FILE_TYPE_DICT.put("psd", "38425053");  //Photoshop (psd)    
        FILE_TYPE_DICT.put("eml", "44656C69766572792D646174653A");  //Email [thorough only] (eml)    
        FILE_TYPE_DICT.put("dbx", "CFAD12FEC5FD746F");  //Outlook Express (dbx)    
        FILE_TYPE_DICT.put("pst", "2142444E");  //Outlook (pst)    
        FILE_TYPE_DICT.put("xls", "D0CF11E0");  //MS Word    
        FILE_TYPE_DICT.put("doc", "D0CF11E0");  //MS Excel 注意：word 和 excel的文件头一样    
        FILE_TYPE_DICT.put("mdb", "5374616E64617264204A");  //MS Access (mdb)    
        FILE_TYPE_DICT.put("wpd", "FF575043"); //WordPerfect (wpd)     
        FILE_TYPE_DICT.put("eps", "252150532D41646F6265");
        FILE_TYPE_DICT.put("ps", "252150532D41646F6265");
        FILE_TYPE_DICT.put("pdf", "255044462D312E");  //Adobe Acrobat (pdf)    
        FILE_TYPE_DICT.put("qdf", "AC9EBD8F");  //Quicken (qdf)    
        FILE_TYPE_DICT.put("pwl", "E3828596");  //Windows Password (pwl)    
        FILE_TYPE_DICT.put("wav", "57415645");  //Wave (wav)    
        FILE_TYPE_DICT.put("avi", "41564920");
        FILE_TYPE_DICT.put("ram", "2E7261FD");  //Real Audio (ram)    
        FILE_TYPE_DICT.put("rm", "2E524D46");  //Real Media (rm)    
        FILE_TYPE_DICT.put("mpg", "000001BA");  //    
        FILE_TYPE_DICT.put("mov", "6D6F6F76");  //Quicktime (mov)    
        FILE_TYPE_DICT.put("asf", "3026B2758E66CF11"); //Windows Media (asf)    
        FILE_TYPE_DICT.put("mid", "4D546864");  //MIDI (mid)    
    }

    /**
     *
     * @param f
     * @return
     */
    public final static String getImageFileType(File f) {
        if (isImage(f)) {
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(f);
                Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
                if (!iter.hasNext()) {
                    return null;
                }
                ImageReader reader = iter.next();
                iis.close();
                return reader.getFormatName();
            }
            catch (IOException e) {
                return null;
            }
            catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     *
     * @param b
     * @return
     */
    public final static String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param b
     * @return
     */
    public final static String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_DICT.entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String,String> entry =  entryiterator.next();
            String fileTypeHexValue = entry.getValue();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     *
     * @param file
     * @return
     */
    public final static String getRealExtension(File file) {
        String extension = StringUtils.EMPTY;

        if (file != null) {
            try {
                InputStream inputStream = new FileInputStream(file);
                extension = getRealExtension(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return extension;
    }

    /**
     *
     * @param multipartFile
     * @return
     */
    public final static String getRealExtension(MultipartFile multipartFile) {
        String extension = StringUtils.EMPTY;
        if (multipartFile != null) {
            try {
                InputStream inputStream = multipartFile.getInputStream();
                extension = getRealExtension(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return extension;
    }

    /**
     *
     * @param inputStream
     * @return
     */
    public final static String getRealExtension(InputStream inputStream) {
        String extension = StringUtils.EMPTY;

        byte[] b = new byte[4];
        try {
            inputStream.read(b, 0, b.length);
            extension = getFileTypeByStream(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extension;
    }

    /**
     * 判断文件是否图片格式
     * @param multipartFile
     * @return
     */
    public final static boolean isImage(MultipartFile multipartFile) {
        boolean flag = false;
        try {
            if (multipartFile == null) return false;
            BufferedImage bufreader = ImageIO.read(multipartFile.getInputStream());
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if(width==0 || height==0){
                flag = false;
            }else {
                flag = true;
            }
        } catch (IOException e) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 判断文件是否图片格式
     * @param file
     * @return
     */
    public final static boolean isImage(File file) {
        boolean flag = false;
        try {
            if (file == null) return false;
            BufferedImage bufreader = ImageIO.read(file);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if(width==0 || height==0){
                flag = false;
            }else {
                flag = true;
            }
        } catch (IOException e) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 判断文件是否图片格式
     * @param inputStream
     * @return
     */
    public final static boolean isImage(InputStream inputStream) {
        boolean flag = false;
        try {
            if (inputStream == null) return false;
            BufferedImage bufreader = ImageIO.read(inputStream);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if(width==0 || height==0){
                flag = false;
            }else {
                flag = true;
            }
        } catch (IOException e) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 缩放文件
     * @param srcImage
     * @param destImage
     * @param width
     * @param height
     * @return
     */
    public static File zoom(File srcImage, File destImage, Integer width, Integer height ) {

        if ( !srcImage.exists() || !srcImage.isFile()
                || !destImage.exists() || !destImage.isFile() ) {
            return destImage;
        }

        Double wr = 0d ,hr = 0d;
        try {
            BufferedImage bufImg = ImageIO.read(srcImage);
            wr = width * 1.0 / bufImg.getWidth();
            hr = height * 1.0 / bufImg.getHeight();

            if ( wr > 1 ) {
                width = bufImg.getWidth();
            }
            if ( hr > 1 ) {
                height = bufImg.getHeight();
            }

            if ( wr >= 1 && hr >= 1 ) {
                FileUtils.copyFile(srcImage, destImage);
            } else {
                Image Itemp = bufImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
                Itemp = ato.filter(bufImg, null);



                ImageIO.write((BufferedImage)Itemp, "jpg", destImage);
            }
        } catch (Exception e) {
            LOGGER.error("缩放图片失败：", e);
        }
        return destImage;

    }

    public static InputStream zoom(InputStream srcIS, Integer width, Integer height) throws IOException {
        return zoom(srcIS, width, height, null);
    }

    public static InputStream zoom(InputStream srcIS, Integer width, Integer height, String extension ) throws IOException {
        InputStream destIS = null;
        if ( srcIS == null ) {
            return destIS;
        }

        Double wr = 0d ,hr = 0d;
        try {
            BufferedImage bufImg = ImageIO.read(srcIS);
            wr = width * 1.0 / bufImg.getWidth();
            hr = height * 1.0 / bufImg.getHeight();

            if ( wr > 1 ) {
                width = bufImg.getWidth();
            }
            if ( hr > 1 ) {
                height = bufImg.getHeight();
            }

            if ( wr >= 1 && hr >= 1 ) {
                destIS =(InputStream) srcIS;
            } else {

                if (StringUtils.isEmpty(extension)) extension = getRealExtension((InputStream) srcIS);
                if (StringUtils.isBlank(extension)) extension = "jpg";
                //Image os = bufImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                File file = File.createTempFile("tmp", "zoom." + extension);

                Image Itemp = bufImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
                Itemp = ato.filter(bufImg, null);
                ImageIO.write((BufferedImage)Itemp, extension, file);
                destIS = new FileInputStream(file);
                file.delete();
            }
        } catch (Exception e) {
            LOGGER.error("缩放图片失败：", e);
            throw e;
        }
        /*Iterator<ImageReader> readers = ImageIO.getImageReaders(srcIS);
        System.out.println(readers.hasNext());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedImage thumbnail = Thumbnails.of(srcIS)
                .scale(0.25f)
                .asBufferedImage();
        //destIS = new ByteArrayInputStream(os.toByteArray());
        ImageIO.write(thumbnail, extension, os);
        destIS = new ByteArrayInputStream(os.toByteArray());*/
        return destIS;
    }


    public static MultipartFile zoom(MultipartFile multipartFile, Integer width, Integer height, String extension) throws IOException {
        try {
            String contentType = multipartFile.getContentType();
            String originalFilename = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            if (inputStream == null) return null;

            inputStream = zoom(inputStream, width, height, extension);
            if (inputStream == null) return null;

            NFileItem fileItem = new NFileItem();
            fileItem.setInputStream(inputStream);
            fileItem.setContentType(contentType);
            fileItem.setSize(new Long(inputStream.available()));
            fileItem.setName(originalFilename);
            return new CommonsMultipartFile(fileItem);
        } catch (Exception e) {
            LOGGER.error("缩放图片失败：", e);
            throw e;
        }

    }

    public static InputStream zoomToInputStream(MultipartFile multipartFile, Integer width, Integer height, String extension) throws IOException {
        InputStream zoomInputStream = null;

        try {
           // String contentType = multipartFile.getContentType();
            //String originalFilename = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            if (inputStream == null) return null;

            zoomInputStream = zoom(inputStream, width, height, extension);
            if (zoomInputStream == null) return null;

        } catch (Exception e) {
            LOGGER.error("缩放图片失败：", e);
            throw e;
        }

        return zoomInputStream;
    }


}
