public class personalTask extends Task {
	String type = "Personal";
	personalTask(String t, String d,String p){
		super(t,d,p);
	}
	public String toString() {
		return "Type : "+type+"\n   Title: "+getTitle()+"\n   Description: "+getDescription()+
				"\n   Priority Level: "+priority+ "\n   Completed: "+(isCompleted?"Yes\n":"No\n");
	}
}
