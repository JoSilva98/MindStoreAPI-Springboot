package MindStore.enums;

import java.util.List;

public class ProductFieldsEnum {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String PRICE = "price";
    public static final String STOCK = "stock";
    public static final String CATEGORY = "category";
    public static final String RATING = "rating";
    public static final List<String> FIELDS = List.of(TITLE, RATING);

}
