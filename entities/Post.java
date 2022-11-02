package com.ranjeet.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.stream.events.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "post_title", length = 100, nullable = false)
	private String title;

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Column(length = 1000000000)
	private String content;

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	private String imageName;

	private Date addedDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@ManyToOne
	private User user;
	
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();


	public String getImageName() {
		return imageName;
		
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
		
	}


	public Date getAddedDate() {
		return addedDate;
		
	}


	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
		
	}

}