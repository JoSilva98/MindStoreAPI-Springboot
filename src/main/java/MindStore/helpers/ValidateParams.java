package MindStore.helpers;

import MindStore.exceptions.NotAllowedValueException;

public class ValidateParams {
    public static void validatePages(int page, int pageSize) {
        if (page <= 0)
            throw new NotAllowedValueException("Only pages above 0");

        if (pageSize < 1 || pageSize > 100)
            throw new NotAllowedValueException("Only between 1 and 100 products per page");
    }
}
