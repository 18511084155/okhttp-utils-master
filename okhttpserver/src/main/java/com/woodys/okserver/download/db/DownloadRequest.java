package com.woodys.okserver.download.db;


import com.woodys.okserver.network.cache.CacheMode;
import com.woodys.okserver.network.model.HttpHeaders;
import com.woodys.okserver.network.model.HttpParams;
import com.woodys.okserver.network.request.BaseRequest;
import com.woodys.okserver.network.request.DeleteRequest;
import com.woodys.okserver.network.request.GetRequest;
import com.woodys.okserver.network.request.HeadRequest;
import com.woodys.okserver.network.request.OptionsRequest;
import com.woodys.okserver.network.request.PostRequest;
import com.woodys.okserver.network.request.PutRequest;

import java.io.Serializable;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy0216
 * 版    本：1.0
 * 创建日期：16/8/8
 * 描    述：与BaseRequest对应,主要是为了序列化
 * 修订历史：
 * ================================================
 */
public class DownloadRequest implements Serializable {

    private static final long serialVersionUID = -6883956320373276785L;

    public String method;
    public String url;
    public CacheMode cacheMode;
    public String cacheKey;
    public long cacheTime;
    public HttpParams params;
    public HttpHeaders headers;

    public static String getMethod(BaseRequest request) {
        if (request instanceof GetRequest) return "get";
        if (request instanceof PostRequest) return "post";
        if (request instanceof PutRequest) return "put";
        if (request instanceof DeleteRequest) return "delete";
        if (request instanceof OptionsRequest) return "options";
        if (request instanceof HeadRequest) return "head";
        return "";
    }

    public static BaseRequest createRequest(String url, String method) {
        if (method.equals("get")) return new GetRequest(url);
        if (method.equals("post")) return new PostRequest(url);
        if (method.equals("put")) return new PutRequest(url);
        if (method.equals("delete")) return new DeleteRequest(url);
        if (method.equals("options")) return new OptionsRequest(url);
        if (method.equals("head")) return new HeadRequest(url);
        return null;
    }
}
