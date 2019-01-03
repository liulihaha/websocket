package good.bean;

import java.util.List;

public class GoodBean {
	
	private int id;
	private String name;
	private int price;
	private String type;
	private int number;
	private List<CommentBean> comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<CommentBean> getComment() {
		return comment;
	}
	public void setComment(List<CommentBean> comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "GoodBean [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", number=" + number
				+ ", comment=" + comment + "]";
	}
	
	

}
