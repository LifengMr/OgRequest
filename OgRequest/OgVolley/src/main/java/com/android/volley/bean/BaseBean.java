package com.android.volley.bean;

/**
 * Bean基础类
 * @author chenlifeng1
 */
public class BaseBean {
	private String description;
	/**
	 * 0:成功,并有实际数据 (返回结果的data字段不为空)
     * 10000:当前用户的登录状态过期或有异常,客户端需要重新登录
     * 10001:请求缺少必要的参数
     * 10002:没有找到实际的数据 (返回结果的data字段为空)
     * (其它):提示性的文字,需要客户端弹出对话框显示description字段内容
	 */
	private int error;
	private long server_time;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public long getServer_time() {
		return server_time;
	}
	public void setServer_time(long server_time) {
		this.server_time = server_time;
	}
}
