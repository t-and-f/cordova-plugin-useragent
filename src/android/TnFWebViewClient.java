package com.tnf.webclient.filter;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TnFWebViewClient extends WebViewClient {

  private String origin = null;

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getOrigin() {
    return this.origin;
  }

  // Handle API until level 21
  @SuppressWarnings("deprecation")
  @Override
  public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
    return getNewResponse(url);
  }

  // Handle API 21+
  @Override
  public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
    String url = request.getUrl().toString();
    return getNewResponse(url);
  }

  private WebResourceResponse getNewResponse(String url) {
    try {
      OkHttpClient httpClient = new OkHttpClient();

      Request request = new Request.Builder().url(url.trim()).header("Origin", this.origin) // Example header
          .build();

      Response response = httpClient.newCall(request).execute();

      return new WebResourceResponse(null, response.header("content-encoding", "utf-8"), response.body().byteStream());

    } catch (Exception e) {
      return null;
    }
  }
}
