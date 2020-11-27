package MAIN;

import java.util.Scanner;

import model.notification;
import service.Admin;

public class Main {
	 public static void main(String[] args) {
		 notification m=new notification();
		/*("Dear {x} , your booking of the {y} is confirmed. thanks for using our store :) ");
		("English");
		("your booking sms confirmation message");
		("email");*/
		 Admin a=new Admin();
		 int conti=1;
		
		 while(conti==1)
		 {
			 Scanner in=new Scanner(System.in);
			 
			 int choose=0;
			 System.out.println("choose the operation you want :-\n"
					 +"1)creat Notification\n 2)Read Notification\n 3)update Notification\n 4)delete Notification");
			 choose=in.nextInt();
			 if(choose== 1)
				 {
					 Scanner s2=new Scanner(System.in);
					 Scanner sr=new Scanner(System.in);
					 System.out.println("Enter id");
					 m.setId(s2.nextInt());
					 System.out.println("Enter subject");
					 m.setSubject(sr.nextLine());
					 System.out.println("Enter channels");
					 m.setChannels(sr.nextLine());
					 System.out.println("Enter language");
					 m.setLanguage(sr.nextLine());
					 System.out.println("Enter content");
					 m.setcontent(sr.nextLine());
					 //sr.close();
					 if(a.CreateNotification(m).getStatus())
					 {
						 System.out.println("created");
					 }
					 else
					 {
						 System.out.println("failed");
					 }
					 
				 }
			 else if(choose==2)
				 {
					 Scanner s2=new Scanner(System.in);
					 notification n= new notification();
					 int id;
					 System.out.println("Enter id of notification you want read");
					 id=s2.nextInt();
					
					 n=a.ReadNotification(id);
					 if(n!=null)
					 {
						 System.out.println("subject:- "+n.getSubject()+"\n"+"channels by: "+n.getChannels()+"\n"+"language : "+n.getLanguage()+"\n"+n.getcontent()+"\n");	 
					 }
					 else
					 {
						 System.out.println("can not find");
					 }
	
						
						
						
				 }
			 else if(choose==3)
				 {
					 int id;
					 Scanner s2=new Scanner(System.in);
					 Scanner sr=new Scanner(System.in);
					 System.out.println("Enter id of notification you want update");
					 id=s2.nextInt();
					 m.setId(id);
					 System.out.println("Enter subject");
					 m.setSubject(sr.nextLine());
					 System.out.println("Enter channels");
					 m.setChannels(sr.nextLine());
					 System.out.println("Enter language");
					 m.setLanguage(sr.nextLine());
					 System.out.println("Enter content");
					 m.setcontent(sr.nextLine());
					 //sr.close();
					 if(a.UpdateNotification(id,m).getStatus())
					 {
						 System.out.println("updated");
					 }
					 else
					 {
						 System.out.println("failed");
					 }
					 
				 }
			 else if(choose==4)
				 {
					 Scanner s2=new Scanner(System.in);
					 int id;
					 System.out.println("Enter id of notification you want delete");
					 id=s2.nextInt();
					 m.setId(id);
					 if(a.deleteNotification(1).getStatus())
					 {
						 System.out.println("deleted");
					 }
					 else
					 {
						 System.out.println("failed");
					 }
					 
				 }
			 
			 System.out.println("do you want continue 0/1");
			 Scanner ch=new Scanner(System.in);
			 conti=ch.nextInt();
			 	 
		
	 }
	 }
}
