package com.ajie.mq;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ActiveMQConfig {
	
	public static void main(String[] args) {
		ConfigVo config = ActiveMQConfig.getConfig();
		System.out.println(config.getAccount());
	}
	
	private static final int DEFAULT_PORT = 61616;
	private static ConfigVo config;
	
	public static ConfigVo getConfig(){
		if(null == config){
			synchronized (ActiveMQConfig.class) {
				if(null == config){
					config = loadConfig();
				}
				
			}
		}
		return config;
	}
	
	private static ConfigVo loadConfig(){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mq.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			ConfigVo config = new ConfigVo();
			config.setAccount(properties.getProperty("activemq_account"));
			config.setPassword(properties.getProperty("activemq_password"));
			String strPort = properties.getProperty("activemq_port");
			int port = DEFAULT_PORT;
			try {
				int  p  = Integer.valueOf(strPort);
				if(p > 0){
					port =  p;
				}
			} catch (Exception e) {
			}
			config.setPort(port);
			config.setUrl(properties.getProperty("activemq_url"));
			return config;
		} catch (IOException e) {
			System.err.println("加载配置失败");
			return new ConfigVo();
		}
	}
	
	public static class ConfigVo{
		private String url;
		private int port;
		private String account;
		private String password;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
}
