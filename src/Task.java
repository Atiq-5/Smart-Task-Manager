public abstract class Task {
	private String title;
	private String description;
	protected boolean isCompleted;
	protected String priority;
	
	String getTitle() {
		return title;
	}
	void setTitle(String t){
		title = t;
	}
	
	String getDescription() {
		return description;
	}
	void setDescription(String d){
		description = d;
	}
	

	Task(String t, String d,String p){
		title = t;
		description = d;
		priority = p;
		isCompleted = false;
	}
	public abstract String toString();
}
