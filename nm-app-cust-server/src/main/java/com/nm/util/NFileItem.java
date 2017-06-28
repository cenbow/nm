package com.nm.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileCleaner;

import java.io.*;
import java.rmi.server.UID;

/**
 * Created by lenovo on 2017/3/21.
 */
public class NFileItem implements FileItem {
    private InputStream inputStream;
    private String contentType;
    private String name;
    private Long size = Long.valueOf(0L);
    private String fieldName = "file";
    private Boolean formField = Boolean.valueOf(false);
    private File repository;
    private static int counter = 0;
    private static final String UID = (new UID()).toString().replace(':', '_').replace('-', '_');

    public NFileItem() {
    }

    public InputStream getInputStream() throws IOException {
        return this.inputStream;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getName() {
        return this.name;
    }

    public boolean isInMemory() {
        return true;
    }

    public long getSize() {
        return this.size.longValue();
    }

    public byte[] get() {
        byte[] fileData = new byte[(int)this.getSize()];
        InputStream fis = this.inputStream;

        try {
            fis.read(fileData);
        } catch (IOException var12) {
            fileData = null;
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

        return fileData;
    }

    public String getString(String encoding) throws UnsupportedEncodingException {
        return new String(this.get(), encoding);
    }

    public String getString() {
        try {
            return this.getString("UTF-8");
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public void write(File file) throws Exception {
    }

    public void delete() {
        InputStream fis = this.inputStream;
        if(fis != null) {
            try {
                fis.close();
            } catch (IOException var3) {
                ;
            }
        }

    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isFormField() {
        return this.formField.booleanValue();
    }

    public void setFormField(boolean state) {
        this.formField = Boolean.valueOf(state);
    }

    public File getRepository() {
        return this.repository;
    }

    public void setRepository(File repository) {
        this.repository = repository;
    }

    public OutputStream getOutputStream() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(this.getTempFile());
        fileOutputStream.write(this.get());
        return fileOutputStream;
    }

    public Boolean getFormField() {
        return this.formField;
    }

    public void setFormField(Boolean formField) {
        this.formField = formField;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    protected File getTempFile() {
        File tempDir = this.repository;
        if(tempDir == null) {
            tempDir = new File(System.getProperty("java.io.tmpdir"));
        }

        String tempFileName = "upload_" + UID + "_" + getUniqueId() + ".tmp";
        File f = new File(tempDir, tempFileName);
        FileCleaner.track(f, this);
        return f;
    }

    private static String getUniqueId() {
        int limit = 100000000;
        Class id = DiskFileItem.class;
        int current;
        synchronized(DiskFileItem.class) {
            current = counter++;
        }

        String id1 = Integer.toString(current);
        if(current < 100000000) {
            id1 = ("00000000" + id1).substring(id1.length());
        }

        return id1;
    }

    public String getOriginalFilename() {
        return null;
    }
}
