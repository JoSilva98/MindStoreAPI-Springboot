package MindStore.enums;

import java.util.List;

public class UserFieldsEnum {
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String DATE_OF_BIRTH = "dateOfBirth";
    public static final String ADDRESS = "address";
    public static final List<String> FIELDS = List.of(ID, FIRST_NAME, LAST_NAME,
            EMAIL, PASSWORD, DATE_OF_BIRTH, ADDRESS);
}
