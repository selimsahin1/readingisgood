package com.selimsahin.readingisgood.model;

import com.selimsahin.readingisgood.database.entity.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class ModelTest {

    private static final Class<?>[] MODEL_CLASS_ARRAY = {
            Book.class,
            OrderedBook.class,
            Orders.class,
            Role.class,
            User.class
    };
}
