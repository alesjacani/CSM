package Util;

public enum Messages {

	PROFESSOR_EXISTS ("xxx The professor you are trying to add, exists. xxx \n"),
	STUDENT_EXISTS ("The professor you are trying to add, exists."),
    MENU_MESSAGE ("Please write ONLY INTEGERS FROM 1-3. \nWrite number again. \n ");
	
	private String message;
	Messages(String message) {
    this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	

	
}
