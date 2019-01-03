package good.bean;

public class CommentBean {
	
	private int id;
	private String userName;
	private String time;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CommentBean [id=" + id + ", userName=" + userName + ", time=" + time + ", content=" + content + "]";
	}
	
	

}
