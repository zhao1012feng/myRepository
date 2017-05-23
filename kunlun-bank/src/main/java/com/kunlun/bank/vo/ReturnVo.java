package com.kunlun.bank.vo;


public class ReturnVo {

	//状态
	private boolean state;
	
	//错误信息
	private String errorMessage;

	//成功返回的数据
	private Object returnData;

	
	public ReturnVo(boolean state, Object returnData) {
		super();
		this.state = state;
		this.returnData = returnData;
	}

	public ReturnVo(boolean state, String errorMessage) {
		super();
		this.state = state;
		this.errorMessage = errorMessage;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getReturnData() {
		return returnData;
	}

	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}
	
	
	
	
}
