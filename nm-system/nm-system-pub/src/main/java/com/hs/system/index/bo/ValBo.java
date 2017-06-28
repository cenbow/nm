package com.hs.system.index.bo;

import java.io.Serializable;

public class ValBo  implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String key;
		private String val;
		 
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	}
