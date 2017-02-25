package com.android.volley.request;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.bean.BaseBean;
import com.android.volley.request.ResponseListener.NetworkResponseState;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

/**
 * 网络请求类
 * @author chenlifeng1
 * @param <T>
 */
public class VolleyRequest<T extends BaseBean> extends Request<T> {
	/** JSON默认编码格式  */
    protected static final String PROTOCOL_CHARSET = "utf-8";
    /** FastJson解析类 */
	private Class<T> mClass;
	/** 自定义解析器 */
	private BaseParser<T> mParser;
	/** 网络请求回调 */
	private ResponseListener<T> mResponseListener;
	
	public VolleyRequest(Context context){
		super(context);
	}
	
	/**
	 * 设置请求方式  post get等
	 * @param method
	 */
	public VolleyRequest<T> setMethod(int method){
		super.mMethod = method;
		return this;
	}
	
	public VolleyRequest<T> setUrl(String url){
		this.mUrl = url;
		return this;
	}
	
	/**
	 * Jsonfast解析
	 * @param clazz
	 */
	public VolleyRequest<T> setClass(Class<T> clazz){
		this.mClass = clazz;
		return this;
	}
	
	/**
	 * 自定义解析器
	 * @param parser
	 * @return
	 */
	public VolleyRequest<T> setParser(BaseParser<T> parser){
		this.mParser = parser;
		return this;
	}

	public VolleyRequest<?> setTag(Object tag) {
		super.setTag(tag);
		return this;
	}
	
	/**
	 * 设置请求结果回调
	 * @param callback
	 * @return
	 */
	public VolleyRequest<T> setCallback(ResponseListener<T> callback){
		this.mResponseListener = callback;
		return this;
	} 
	
	/**
	 * 加入线程池，并开始启动
	 * @return
	 */
	public VolleyRequest<T> add(){
		Volley.getQueue(mContext).add(this);
		return this;
	}
	
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            if(mClass != null){
            	//FastJson解析
            	return Response.success(JSON.parseObject(jsonString, mClass),
            			HttpHeaderParser.parseCacheHeaders(response));
            }else if(mParser != null){
            	//自定义解析
            	return Response.success(mParser.executeToObject(jsonString, mContext),
            			HttpHeaderParser.parseCacheHeaders(response));
            }
            
            return Response.error(new VolleyError(NetworkResponseState.RESULT_ERROR));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
	}

	@Override
	protected void deliverResponse(T response) {
		if(mResponseListener != null){
			mResponseListener.onNetSuccess(response);
		}
	}
	
	@Override
	public void deliverError(VolleyError error) {
		if(mResponseListener != null){
			mResponseListener.onError(error);
		}
	}
}
