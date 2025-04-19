package Repositories;

public class Task {
	private String title;
	private String description;
	private String status;

	public Task(String title, String description, String status) {
		this.title = title;
		this.description = description;
		this.status = status;
	}
	public String getTitle() {
		return this.title;
	}
	public String getStatus() {
		return this.status;
	}
	public String getDescription() {
		return this.description;
	}
}
