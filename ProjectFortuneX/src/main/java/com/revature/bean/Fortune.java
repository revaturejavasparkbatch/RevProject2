package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Fortune {
	@Id
	@SequenceGenerator(sequenceName="fortune_seq", name="f_seq")
	@GeneratedValue(generator="f_seq", strategy=GenerationType.SEQUENCE)
	private int fId;
	
	@Column(name="f_id", nullable = false)
	private String id;
	
	@ManyToOne
	@JoinColumn(name="u_id")
	private User user;
	
	@Column(name="lucky_num")
	private Integer luckyNum;
	
	public Fortune() {
		super();
	}

	public Fortune(String id, Integer luckyNum) {
		super();
		this.id = id;
		this.luckyNum = luckyNum;
	}

	public Fortune(String id, User user, Integer luckyNum) {
		super();
		this.id = id;
		this.user = user;
		this.luckyNum = luckyNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getLuckyNum() {
		return luckyNum;
	}

	public void setLuckyNum(Integer luckyNum) {
		this.luckyNum = luckyNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((luckyNum == null) ? 0 : luckyNum.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Fortune other = (Fortune) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (luckyNum == null) {
			if (other.luckyNum != null)
				return false;
		} else if (!luckyNum.equals(other.luckyNum))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fortune [id=" + id + ", user=" + user + ", luckyNum=" + luckyNum + "]";
	}
	
}
