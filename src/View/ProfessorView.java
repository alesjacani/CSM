package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Accounts.Grade;
import Accounts.Professor;
import Accounts.Student;
import Course.Courses;
import Exceptions.ProjectException;
import Service.AdminService;
import Service.ProfessorService;
import Util.Messages;

public class ProfessorView {
	public void professorMenu(Professor professor) {
		System.out.println("\n");

		System.out.printf("<<<<<<<<<< WELCOME TO PROFESSOR MENU %S %S  >>>>>>>>>>\n", professor.getFirstNameProf(),professor.getLastNameProf());
		System.out.println("You are added to the course/courses  below:");
		//for (Professor p :AdminService.listAllProfessorById(professor.getIdProfessor())) {
			for (Courses c : AdminService.getCourseByProfessorUsername(professor.getUsernameProf()) ) {
				System.out.printf("-%s   with id  %d.\n",c.getCourseName(),c.getCourseId());
			 }
	
		
		System.out.println("\n");
		System.out.println("Please write down the number to continue.");
		System.out.println("For your menu                    For Student                               For Course                  GO BACK");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                2-Add Student into course");
		System.out.println("                                3-Grade student");
		System.out.println("1-Change password               4-List student in ur course              7- Edit course details        8- GO BACK TO LOGIN PAGE." );
		System.out.println("                                5-See grade of student                   ");
		System.out.println("                                   with specific id                                                      ");
		System.out.println("                                6-See all grade-student                                 ");
		
		try {
		
		Scanner inputMenu =new Scanner (System.in);	
		System.out.println(" ");
		System.out.println(" > Write the number for the function you want to do next < \n");
	    int number = inputMenu.nextInt();
	    
        switch (number) {
		
		case 1:
			ChangePassword();
			break;
			
		case 2 :
			addStudentIntoActualCourse();
			break;
			
		case 3:
		gradeStudent();
			break;
		case 4 :
			break;
		case 5 :
			break;
		case 6 :
			break;
		case 7 :
			editCourseDetails();
			break;
		case 8 :
			new Menu().start();
			break;
		default:
			System.out.println("Please write ONLY INTEGERS FROM 1-8. ");
			professorMenu(professor);
		}
		}catch (InputMismatchException e) {
			System.out.println("Please write ONLY INTEGERS FROM 1-8. ");
			professorMenu(professor);
		}
		
	
		
		
	}//end of professorMenu()
	
	public  void ChangePassword() {
		Scanner input = new Scanner (System.in);
		Professor prof = new Professor();
		
		System.out.println("Please enter your username: ");
		prof.setUsernameProf(input.next());
		System.out.println("Please enter your password: ");
		prof.setPasswordProf(input.next());
		
		try {
		AdminService.authenticateProfessor(prof);
		
		System.out.println("Enter your new password: ");
		prof.setPasswordProf(input.next());
		
		ProfessorService.changePassword(prof.getUsernameProf(), prof.getPasswordProf());
		System.out.println("Your password is changed!!!! :)");
		
		professorMenu(prof);
		}catch (ProjectException exception) {
			System.out.println(">>>> "+exception.getMessage());
			ChangePassword();
		}
		finally {
			input.close();
		}
	}

public void addStudentIntoActualCourse() {
		
		System.out.println(" * ADD Student Category. * ");
		
		Scanner sc = new Scanner(System.in);
		Student student = new Student();
		Courses course = new Courses();
		Professor p = new Professor();
		
	
		try {
			
			//System.out.println("Please add student's details.");
			System.out.print("Please enter your username: ");
			p.setUsernameProf(sc.next());
			
			AdminService.authenticateProfessorByUsername(p);
			
			System.out.print("Please write course NAME in which you want to add the student. -->  ");
			course.setCourseName(sc.next());
			
			
			
			
           List<String> coursesName = ProfessorService.getCourseNameByProfessorUsername(p.getUsernameProf());
			
           
			if (coursesName.contains(course.getCourseName()) ) {
				
				System.out.print("Give name: ");
				student.setFirstNameStudent(sc.next());
		
				System.out.print("Give last name: "); 
				student.setLastNameStudent(sc.next());
				
				System.out.print("Give usernamename: ");
				student.setUserNameStudent(sc.next());
				
				System.out.print("Give password: ");
				student.setPasswordStudent(sc.next());
				
		     	ProfessorService.addStudent(student,course);
		    	//ProfessorService.getIdStudentByUsername(student);
			
			 /*   for (Courses c : AdminService.getCourseByID(course.getCourseId())) {
                     ProfessorService.addStudentCoursebyIds(student.getIdStudent(), course.getCourseId());
		   */
                     System.out.printf("<<<<<<<<<< You just added student %s %s with id %d ", student.getFirstNameStudent() , student.getLastNameStudent(),student.getIdStudent());
		             System.out.printf("into course %s . >>>>>>>>>>",course.getCourseName());
	        //                                }
			
			Professor professor = ProfessorService.getProfByCourseName(course.getCourseName());
			professorMenu(professor);
			}
			
			else {
				System.out.printf("\n %s \n\n",Messages.INVALID_COURSE_NAME.getMessage());
				addStudentIntoActualCourse();
			}
			
			
			
			
		} catch (ProjectException exception) {
			System.out.println(exception.getMessage());
			
			addStudentIntoActualCourse();
			
		}catch (InputMismatchException exception) {
	        System.out.println("Write word OR number in  requested details please.\n");
			addStudentIntoActualCourse();
			
			
			}
		finally {
			sc.close();
		}
		
}
public  void editCourseDetails() {
	
	Scanner input = new Scanner (System.in);
	Courses course = new Courses();
	Professor professor = new Professor();
	
	System.out.println("Give username: ");
	professor.setUsernameProf(input.next());
	
	System.out.println("Give course NAME:");
	course.setCourseName(input.next());
	
	AdminService.authenticateCourse(course);
	
	List<String> coursesName = ProfessorService.getCourseNameByProfessorUsername(professor.getUsernameProf());
	
	
	try{
	
		if (coursesName.contains(course.getCourseName()) ) {
			
			System.out.print("Add course description: ");
		    course.setDesciption(input.next());
		    System.out.print("Add course duration time: ");
		    course.setDurationTime(input.next());
		    
		    ProfessorService.editCourseDetails(course.getDesciption(), course.getDurationTime(), course.getCourseName());
		 
		    Professor p = ProfessorService.getProfByCourseName(course.getCourseName());
			professorMenu(p);
		    
		}
		
		else {
			System.out.println(Messages.INVALID_COURSE_NAME.getMessage());
		    editCourseDetails();
	}
	
	}
	catch (ProjectException exception) {
		System.out.println(exception.getMessage());
		
		editCourseDetails();
	}finally {
		input.close();
	}
}//


public void gradeStudent() {
	Courses course = new Courses();
	Professor professor = new Professor();
	Student student = new Student ();
	Grade grade= new Grade ();
	
	System.out.println("*Grade Student*\n");
	Scanner sc = new Scanner (System.in);
	
	System.out.print("Give your username: ");
	professor.setUsernameProf(sc.next());
	AdminService.authenticateProfessorByUsername(professor);
	System.out.print("Give course NAME you want to grade the student ");
	course.setCourseName(sc.next());
	Courses c =AdminService.getCourseByName(course.getCourseName());
	  List<String> coursesName = ProfessorService.getCourseNameByProfessorUsername(professor.getUsernameProf());
	
    try {
			if (coursesName.contains(course.getCourseName()) ) {
				
				System.out.print("Give student id you want to grade: ");
				student.setIdStudent(sc.nextInt());
				Student s =AdminService.getStudentBySId(student.getIdStudent());
			    Student st = AdminService.getAllStudentByUsername(s.getUserNameStudent());
			              
			    
			            try {
			            if(ProfessorService.studentHasGrade(c, st)) {
			    	       System.out.printf("Student %s %s into %s course has already a grade.\n",st.getFirstNameStudent(),st.getLastNameStudent(),c.getCourseName());
					      System.out.println("Press 1 to update the grade\nPress 2 to go back to menu");
					      int number = sc.nextInt();
					              
					            	switch (number) {
					            	
					               case 1:
					            	System.out.print("Enter Grade :" );
					   				grade.setGrade(sc.nextInt());
					   				
					   				    if (grade.getGrade()>=4 && grade.getGrade()<=10) {
					            	    ProfessorService.gradeStudent(c, st, grade);
					            	    System.out.printf("<<<<<<<<<< You successfully just graded student %s %s in course %s with grade %d ", st.getFirstNameStudent(),st.getLastNameStudent(),course .getCourseName(),grade.getGrade());
					       	            Professor p = ProfessorService.getProfByCourseName(course.getCourseName());
					       			    professorMenu(p);}
					       			    else{
					       				System.out.println("Grade should be between numbers 4 to 10. TRY AGAIN!");}
					       			break;
					       			
					       			
					               case 2:
					            	   professorMenu(professor);
					       			break;
					       			
					       		default:
					       			System.out.println("PLEASE WRITE  ||1 OR 2.||");
					            	   }
						     }
			    
			            else {
						System.out.print("Enter Grade :" );
		   				grade.setGrade(sc.nextInt());
		   				       if (grade.getGrade()>=4 && grade.getGrade()<=10) {
			            	   ProfessorService.gradeStudent(c, st, grade);
			            	   System.out.printf("<<<<<<<<<< You successfully just graded student %s %s in course %s with grade %d ", st.getFirstNameStudent(),st.getLastNameStudent(),course .getCourseName(),grade.getGrade());
			       	           Professor p = ProfessorService.getProfByCourseName(course.getCourseName());
			       			   professorMenu(p);}
			       			   else{
			       				System.out.println("Grade should be between numbers 4 to 10. TRY AGAIN!");
			       			}
		   		            }
			           }catch (InputMismatchException e) {
							System.out.println("Write only ||1 OR 2.||numbers");
						}catch (ProjectException exception) {
							System.out.println(exception.getMessage());
							gradeStudent(); }
						
		 
			}//END OF FIRST IF
	        else {
				System.out.printf("\n %s \n\n",Messages.INVALID_COURSE_NAME.getMessage());
				gradeStudent();
			}
			
	}catch (ProjectException exception) {
		System.out.println(exception.getMessage());
		
		gradeStudent();
	}catch (InputMismatchException exception) {
        System.out.println("Write word OR number in  requested details please.\n");
		gradeStudent();
		
		
		}
			finally {
				sc.close();
			}
			
}












}//end of CLASS
