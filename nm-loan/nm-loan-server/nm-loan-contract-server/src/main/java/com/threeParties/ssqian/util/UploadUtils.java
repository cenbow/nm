package com.threeParties.ssqian.util;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author lijianhang
 *
 */
public class UploadUtils {

	private HttpServletRequest request = null;;

	public UploadUtils(HttpServletRequest request) {

		this.request = request;
	}

	@SuppressWarnings("rawtypes")
	public Iterator getIterator(long fileSizeMax, long sizeMax) {

		return getIteratorDO(fileSizeMax, sizeMax, DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, "UTF-8");
	}

	@SuppressWarnings("rawtypes")
	private Iterator getIteratorDO(long fileSizeMax, long sizeMax, int sizeThreshold, String encoding) {

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(sizeThreshold);

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(fileSizeMax);
        servletFileUpload.setSizeMax(sizeMax);

        if (WebUtils.isNotEmpty(encoding)) {
        	servletFileUpload.setHeaderEncoding(encoding);
        }

        try {
			return servletFileUpload.parseRequest(request).iterator();
		} catch (FileUploadException e) {
		}

		return null;
	}

}
