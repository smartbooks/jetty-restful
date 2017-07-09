package com.ljja.web.core;

public abstract class MediaTypeFormat {

	public String contentType = null;

	public abstract String Render(Object content) throws Exception;

}
