package com.jhonarendra.restoran.customer.api;

import java.util.List;

/**
 * Created by Jhonarendra on 11/2/2018.
 */

public class Value {
    Boolean success;
    List<Result> result;
    String message;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    public List<Result> getResult() {
        return result;
    }
}
