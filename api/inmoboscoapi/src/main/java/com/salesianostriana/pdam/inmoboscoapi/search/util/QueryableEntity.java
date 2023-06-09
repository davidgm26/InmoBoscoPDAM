package com.salesianostriana.pdam.inmoboscoapi.search.util;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface QueryableEntity {

    static boolean checkQueyParam(Class clazz , String fieldname){
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .anyMatch(n -> n.equalsIgnoreCase(fieldname));

        }
    }



