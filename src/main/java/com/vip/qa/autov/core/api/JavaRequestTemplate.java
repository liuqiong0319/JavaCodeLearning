package com.vip.qa.autov.core.api;

import java.util.List;

public class JavaRequestTemplate {

	protected String method;

	protected List<Object> requests;

	public List<Object> getRequests() {
		return requests;
	}

	public void setRequests(List<Object> requests) {
		this.requests = requests;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
