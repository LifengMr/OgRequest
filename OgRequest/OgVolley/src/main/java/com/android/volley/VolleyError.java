/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley;

import com.android.volley.request.ResponseListener.NetworkResponseState;

/**
 * Exception style class encapsulating Volley errors
 */
@SuppressWarnings("serial")
public class VolleyError extends Exception {
	private NetworkResponseState mErrorState = NetworkResponseState.UNKONW;
    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    public VolleyError() {
        networkResponse = null;
    }
    
    /**
     * 错误类型
     * @param state
     */
    public VolleyError(NetworkResponseState state){
    	networkResponse = null;
    	this.mErrorState = state;
    }

    public VolleyError(NetworkResponse response) {
        networkResponse = response;
    }

    public VolleyError(String exceptionMessage) {
       super(exceptionMessage);
       networkResponse = null;
    }

    public VolleyError(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        networkResponse = null;
    }

    public VolleyError(Throwable cause) {
        super(cause);
        networkResponse = null;
    }

    /* package */ void setNetworkTimeMs(long networkTimeMs) {
       this.networkTimeMs = networkTimeMs;
    }

    public long getNetworkTimeMs() {
       return networkTimeMs;
    }
    
    /**
     * 返回错误类型
     * @return
     */
    public NetworkResponseState getErrorState(){
    	return mErrorState;
    }
}
