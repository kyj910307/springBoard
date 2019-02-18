package com.spring.myapp.vo;

public class BoardVO {
	private String id;
    private String subject;
    private String content;
    private String writer;
    private String register_datetime;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegister_datetime() {
		return register_datetime;
	}
	public void setRegister_datetime(String register_datetime) {
		this.register_datetime = register_datetime;
	}
	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", subject=" + subject + ", content=" + content + ", writer=" + writer
				+ ", register_datetime=" + register_datetime + "]";
	}
	
}
