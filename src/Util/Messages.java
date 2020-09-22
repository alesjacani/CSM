package Util;

public enum Messages {

	PROFESSOR_EXISTS ("xxx The professor you are trying to add, exists. xxx \n"),
	STUDENT_EXISTS ("The student you are trying to add, exists."),
    MENU_MESSAGE ("Please write ONLY INTEGERS FROM 1-3. \nWrite number again. \n "),
	COURSE_EXITS("The course you are trying to add, exits."),
	COURSE_DOES_NOT_EXISTS("The course u are trying to delete does not exists.");
	private String message;
	Messages(String message) {
    this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	

	
}
