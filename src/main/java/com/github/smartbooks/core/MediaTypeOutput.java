package com.github.smartbooks.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class MediaTypeOutput {

	public MediaTypeFormat mediaTypeFormat = null;
	public String charset = "utf-8";

	public void write(HttpServletRequest request, HttpServletResponse response, Object methodResult) throws Exception {

		if (null != methodResult) {

			String contentType = request.getContentType();

			mediaTypeFormat = matchMediaTypeFormatInstance(contentType);

			byte[] responseBytes = mediaTypeFormat.Render(methodResult).getBytes(charset);

			response.addHeader("Content-Type", mediaTypeFormat.contentType);

			response.getOutputStream().write(responseBytes);
		}
	}

	private MediaTypeFormat matchMediaTypeFormatInstance(String contentType) {

		if(null == contentType){
			contentType = "application/json";
		}

		switch (contentType) {
		case "application/json":
			return new JsonMediaTypeFormat();

		case "text/html":
			return new TextMediaTypeFormat();

		default:
			return new JsonMediaTypeFormat();
		}
	}

}
