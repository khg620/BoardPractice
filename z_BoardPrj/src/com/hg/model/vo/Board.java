package com.hg.model.vo;

import java.io.Serializable;

public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3762488101320767714L;
	private static int postNo = 0;
	private String title;
	private String content;
	private String name;
	private String postingDate;
	
	static {
		postNo++;
	}
	
	public Board() {}
	
	//가데이터 생성해 확인하기 위해
	public Board(String title, String content, String name, String postingDate) {
		super();
		this.title = title;
		this.content = content;
		this.name = name;
		this.postingDate = postingDate;
	}

	public Board(int postNo, String title, String content, String name, String postingDate) {
		super();
		Board.postNo = postNo;
		this.title = title;
		this.content = content;
		this.name = name;
		this.postingDate = postingDate;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		Board.postNo = postNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + postNo;
		result = prime * result + ((postingDate == null) ? 0 : postingDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postNo != other.postNo)
			return false;
		if (postingDate == null) {
			if (other.postingDate != null)
				return false;
		} else if (!postingDate.equals(other.postingDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Board [postNo=" + postNo + ", title=" + title + ", content=" + content + ", name=" + name + ", postingDate="
				+ postingDate + "]";
	}
	
	public String getBoardData(int tableIdx) {
	 switch(tableIdx) {
	 case 0 : return Integer.toString(postNo);
	 case 1 : return this.getTitle();
	 case 2 : return getContent();
	 case 3 : return getName();
	 case 4 : return getPostingDate();
	 default : return "error";
	 }
	}
	 
}
