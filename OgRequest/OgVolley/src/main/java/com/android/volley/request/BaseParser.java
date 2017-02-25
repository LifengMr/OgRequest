package com.android.volley.request;

import android.content.Context;

import com.android.volley.bean.BaseBean;

/**
 * 解析基础类
 * @author chenlifeng1
 */
public abstract class BaseParser<T extends BaseBean> {
	protected final String ENCODE = "UTF-8";
	public static final String KEY_ERROR = "error";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_DATA = "data";
	
	/**
	 * @param response
	 * @param context
	 * @return
	 */
	public abstract T executeToObject(String response, Context context);
}
