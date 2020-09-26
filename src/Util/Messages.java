package Util;

public enum Messages {

	PROFESSOR_EXISTS ("xxx The professor you are trying to add, exists. xxx \n"),
	STUDENT_EXISTS ("The student you are trying to add, exists."),
    MENU_MESSAGE ("Please write ONLY INTEGERS FROM 1-3. \nWrite number again. \n "),
	COURSE_EXITS("The course you are trying to add already exits."),
	COURSE_DOES_NOT_EXISTS("The course u are trying to delete does not exists."),
	ID_DOES_NOT_EXISTS ("THIS ID DOES NOT EXITS."),
	WRONG_USERNAME_OR_PASSWORD("XXX USERNAME OR PASSWORD WAS WRITTEN WRONG! PLEASE TRY AGAIN! XXX");
	
	private String message;
	
	Messages(String message) {
    this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	

	
}
