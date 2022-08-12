package MindStore.enums;

import lombok.Getter;

@Getter
public enum ProductFieldsEnum {
    ID("ID"),
    TITLE("TITLE"),
    DESCRIPTION("DESCRIPTION"),
    IMAGE("IMAGE"),
    PRICE("PRICE"),
    STOCK("STOCK"),
    CATEGORY("CATEGORY");

    private final String FIELD;

    ProductFieldsEnum(String field) {
        this.FIELD = field;
    }
}
