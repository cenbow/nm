package com.hs.loan.produce.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ClientFactory {

	private CuratorFramework framework = null;

	private String zk_path;
	private Integer session_timeout;
	private Integer conn_timeout;
	private String name_space;

	public ClientFactory(String zk_path, Integer session_timeout,
			Integer conn_timeout, String name_space) {
		super();
		this.zk_path = zk_path;
		this.session_timeout = session_timeout;
		this.conn_timeout = conn_timeout;
		this.name_space = name_space;
	}

	// 获取zkClient
	public synchronized CuratorFramework newClient() {

		if (framework == null) {
			CuratorFramework client = CuratorFrameworkFactory
					.builder()
					.connectString(zk_path)
					.sessionTimeoutMs(session_timeout)
					.connectionTimeoutMs(conn_timeout)
					.canBeReadOnly(false)
					.retryPolicy(
							new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
					.namespace(name_space).defaultData(null).build();

			client.start();
			framework = client;
		}

		return framework;
	}

	public String getZk_path() {
		return zk_path;
	}

	public void setZk_path(String zk_path) {
		this.zk_path = zk_path;
	}

	public Integer getSession_timeout() {
		return session_timeout;
	}

	public void setSession_timeout(Integer session_timeout) {
		this.session_timeout = session_timeout;
	}

	public Integer getConn_timeout() {
		return conn_timeout;
	}

	public void setConn_timeout(Integer conn_timeout) {
		this.conn_timeout = conn_timeout;
	}

	public String getName_space() {
		return name_space;
	}

	public void setName_space(String name_space) {
		this.name_space = name_space;
	}

}
