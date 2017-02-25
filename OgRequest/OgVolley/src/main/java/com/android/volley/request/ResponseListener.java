package com.android.volley.request;

import com.android.volley.VolleyError;
import com.android.volley.bean.BaseBean;

public interface ResponseListener<T extends BaseBean> {
	
	/**
	 * 网络获取数据成功
	 */
	public void onNetSuccess(T result);
	
	/**
	 * 失败
	 */
	public void onError(VolleyError error);
	
	public enum NetworkResponseState{
		/** 成功 */
		SUCCESS,
		
		/** 无网络 */
		NETWORK_NOT_AVAILABLE,
		
		/** 网络错误 */
		NETWORK_ERROR,
		
		/** 数据错误 */
		RESULT_ERROR,
		
		/** 未知错误 */
		UNKONW;
	}
}
