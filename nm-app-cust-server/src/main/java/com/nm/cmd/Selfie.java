package com.nm.cmd;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/5/19.
 */
public class Selfie implements Serializable {
    private static final long serialVersionUID = 1L;

    private String image_id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
}
