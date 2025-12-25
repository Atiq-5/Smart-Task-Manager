import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class TaskManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		boolean run = true;
		while(run) {
			menu();
			int choice;
			try {
				choice = sc.nextInt();
		        sc.nextLine();
				switch(choice) {
					case(1): addTask();
							 break;
					case(2): displayAll();
							 break;
					case(3): markComplete();
							break;
					case(4): completedTask();
							break;
					case(5): pendingTask();
							break;
					case(6): editTask();
							break;
					case(7): deleteTask();
							break;
					case(0): run = false;
							System.out.println("Exiting the Smart Task Manager");
							break;
	                default: System.out.println("Invalid menu choice. Please try again.");
				 }
				}
			catch(InputMismatchException e) {
				System.out.println("Error: Enter only Integer Number!");
				sc.nextLine();
		    }
				
			
	   }
		
	}
	
	    // *******Menu*******//
		private static void menu(){
			System.out.println("====================================");
			System.out.println("||*******Smart Task Manager*******||");
			System.out.println("====================================");
	        System.out.println("1. Add A New Task");
		    System.out.println("2. Display All Tasks");
		    System.out.println("3. Mark a Task as Completed");
		    System.out.println("4. Display Completed Tasks");
		    System.out.println("5. Display Pending Tasks");
		    System.out.println("6. Edit Task");
		    System.out.println("7. Delete Task");
		    System.out.println("0. Exit");
		    System.out.println("------------------------------------");
		    System.out.print("Enter your choice: ");
			
		}
		//*******Add Task*******//
		private static void addTask() {
			System.out.println("*******Adding Task*******");
		
		    System.out.print("Title       : ");
		    String title = sc.nextLine();
		
		    System.out.print("Description : ");
		    String description = sc.nextLine();
		    int type=-1;
		    boolean ok = true;
			while(ok) {
			    try {
					    System.out.print("Task Type (1 = Work, 2 = Personal): ");
					    type = sc.nextInt();
					    if(type !=1 && type !=2) {
					    	throw new InputMismatchException("Please only type Integer number(1 or 2)!\n");
					    }
					    ok = false;
			    }catch(InputMismatchException e) {
			    	System.out.println("Please only type Integer number(1 or 2)!\n");
			    }
			}
			sc.nextLine();
			ok = true;
			String priority = null;

			while (ok) {
			    System.out.print("Priority (High, Mid, Low): ");
			    priority = sc.nextLine().toUpperCase().trim();

			    if (!priority.equals("HIGH") && !priority.equals("MID") && !priority.equals("LOW")) {
			        System.out.println("Priority can only be: High, Mid, or Low\n");
			    } else {
			        ok = false;
			    }
			}
		
			Task t = (type == 1)
		            ? new workTask(title, description, priority)
		            : new personalTask(title, description, priority);
		
		    tasks.add(t);
		    System.out.println(" Task added successfully!");
		    return;
		}
		//*******Display*******//
		private static void displayAll() {
			try {
				if(tasks.size()>0) {
					int c =1;
					for(Task a: tasks) {
						System.out.print(c+". ");
						System.out.println(a.toString());
						c++;
					}
				}else {
					throw new TaskNotFoundException("No Task to show!");
				}
			}
			catch(TaskNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
		}
		//*******pending Task*******//
		private static void pendingTask(){
			System.out.println("*******Pending Task*******");
			int found = 0;
			int c = 1;
			for(Task a:tasks){
				if(!a.isCompleted){
					System.out.print(c+". "+a.toString());
					found++;
					c++;
				}
			}
			if(!(found>0)){
				System.out.println("No pending tasks!");
			}
		}
		//*******Completed Task*******//
		private static void completedTask(){
			System.out.println("*******Completed Task*******");
			int found = 0;
			int c =1;
			for(Task a:tasks){
				if(a.isCompleted){
					System.out.print(c+". "+a.toString());
					found++;
					c++;
				}
			}
			if(!(found>0)){
				System.out.println("No Completed tasks!");
			}
		}

		//*******Mark Complete*******//
		private static void markComplete() {
			System.out.println("*******Mark Task*******");
			displayAll();
			try{
				System.out.println("Enter the Task number you want to Mark as completed: ");
				int i = sc.nextInt();
				if(i<1 && i>tasks.size()){
					throw new TaskNotFoundException("Index was invalid");
				}
				tasks.get(i-1).isCompleted = true;
				
				System.out.println("The task was successfully marked as completed");
			}
			catch(TaskNotFoundException e){
				System.out.println(e.getMessage());
			}
			
		}
		//*******Edit Task*******//
		private static void editTask(){
			System.out.println("*******Edit Task*******");
			displayAll();
			try{
				System.out.println("Enter the Task number you want to edit: ");
				int i = sc.nextInt();
				sc.nextLine();
				if(i<0 && i>tasks.size()){
					throw new TaskNotFoundException("Index was invalid");
				}
				Task x = tasks.get(i-1);


			System.out.print("Title       : ");
		    String title = sc.nextLine();
		
		    System.out.print("Description : ");
		    String description = sc.nextLine();
		    int type=-1;
		    boolean ok = true;
			while(ok) {
			    try {
					    System.out.print("Task Type (1 = Work, 2 = Personal): ");
					    type = sc.nextInt();
					    if(type !=1 && type !=2) {
					    	throw new InputMismatchException("Please only type Integer number(1 or 2)!\n");
					    }
					    ok = false;
			    }catch(InputMismatchException e) {
			    	System.out.println("Please only type Integer number(1 or 2)!\n");
			    }
			}
			sc.nextLine();
			ok = true;
			String p = null;

			while (ok) {
			    System.out.print("Priority (High, Mid, Low): ");
			    p = sc.nextLine().toUpperCase().trim();

			    if (!p.equals("HIGH") && !p.equals("MID") && !p.equals("LOW")) {
			        System.out.println("Priority can only be: High, Mid, or Low\n");
			    } else {
			        ok = false;
			    }
			}
		
			x.setTitle(title);
			x.setDescription(description);
			x.priority = p;
			System.out.println("The task was successfully Edited");
			}
			catch(TaskNotFoundException e){
				System.out.println(e.getMessage());
			}
		}
		//*******Delete Task*******//
		private static void deleteTask(){
			System.out.println("*******Delete Task*******");
			displayAll();
			try{
				System.out.println("Enter the Task number you want to Delete: ");
				int i = sc.nextInt();
				sc.nextLine();
				if(i<0 && i>tasks.size()){
					throw new TaskNotFoundException("Index was invalid");
				}
				tasks.remove(i-1);
				System.out.println("The task was successfully Deleted");
			}
			catch(TaskNotFoundException e){
				System.out.println(e.getMessage());
			}
		}
}