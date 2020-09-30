package Util;

public enum Messages {

	PROFESSOR_EXISTS ("\nxxx The professor you are trying to add with typed USERNAME exists. TRY ANOTHER USERNAME xxx \n"),
	STUDENT_EXISTS ("\nXXX The student you are trying to add with typed USERNAME exists. TRY ANOTHER USERNAM. XXX\n"),
	
	PROFESSOR_DOES_NOT_EXISTS("\nXXX THIS PROFESSOR DOES NOT EXIST. TRY TO WRITE ANOTHER USERNAME XXX\n"),
	STUDENT_DOES_NOT_EXISTS("\nXXX Student does not exists. TRY AGAIN!  XXX\n"),
    MENU_MESSAGE ("Please write ONLY INTEGERS FROM 1-3. \nWrite number again. \n "),
    
	COURSE_EXITS("\nXXX The course you are trying to add already exits. TRY AGAIN! XXX\n"),
	COURSE_DOES_NOT_EXISTS("\nXXX The course does not exists. TRY AGAIN!  XXX\n"),
	COURSE_HAS_PROFESSOR("\nXXX The course has already a professor! Write an another course. XXX\n"),
	ID_DOES_NOT_EXISTS ("\nTHIS ID DOES NOT EXITS.\n"),
	WRONG_USERNAME_OR_PASSWORD("\nXXX USERNAME OR PASSWORD WAS WRITTEN WRONG! PLEASE TRY AGAIN! XXX\n"),
	INVALID_COURSE_NAME("\nXXX  INVALID COURSE NAME ! WRITE COURSE NAME THAT YOU TEACH. TRY AGAIN!!!!  XXX\n");
	
	private String message;
	
	Messages(String message) {
    this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	

	
}
