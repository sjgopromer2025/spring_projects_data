package com.example.boardProject.common;

public class CommonResponse<T> {
	private String status; // "success" or "fail"
	private String message; // 설명 메시지
	private T data; // 응답 데이터

	public CommonResponse() {
	}

	public CommonResponse(String status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> CommonResponse<T> success() {
		return new CommonResponse<>("success", null, null);
	}

	public static <T> CommonResponse<T> success(T data) {
		return new CommonResponse<>("success", null, data);
	}

	public static <T> CommonResponse<T> success(String message) {
		return new CommonResponse<>("success", message, null);
	}

	public static <T> CommonResponse<T> success(String message, T data) {
		return new CommonResponse<>("success", message, data);
	}

	public static <T> CommonResponse<T> fail(String message) {
		return new CommonResponse<>("fail", message, null);
	}

	public static <T> CommonResponse<T> fail() {
		return new CommonResponse<>("fail", null, null);
	}

	// Getter & Setter
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
