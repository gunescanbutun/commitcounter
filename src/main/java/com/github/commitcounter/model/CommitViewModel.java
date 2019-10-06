package com.github.commitcounter.model;

public class CommitViewModel {

	private int totalCommitCount;
	private String loginName;
	private int commitCount;
	public int getTotalCommitCount() {
		return totalCommitCount;
	}
	public void setTotalCommitCount(int totalCommitCount) {
		this.totalCommitCount = totalCommitCount;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getCommitCount() {
		return commitCount;
	}
	public void setCommitCount(int commitCount) {
		this.commitCount = commitCount;
	}
	
	
}
