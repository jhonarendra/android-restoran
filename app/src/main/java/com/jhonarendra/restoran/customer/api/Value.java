package com.jhonarendra.restoran.customer.api;

import java.util.List;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class Value {
    String value;
    List<Result> result;
    String message;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
    public List<Result> getResult() {
        return result;
    }
}
