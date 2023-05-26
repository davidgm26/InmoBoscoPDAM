package com.salesianostriana.pdam.inmoboscoapi.others;

import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Converter
public class RoleConverterAttribute implements AttributeConverter<EnumSet<UserRole>,String> {
    private final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(EnumSet<UserRole> userRole) {
        if(!userRole.isEmpty()) {
            return userRole.stream()
                    .map(UserRole::name)
                    .collect(Collectors.joining(SEPARATOR));
        }
        return null;
    }

    @Override
    public EnumSet<UserRole> convertToEntityAttribute(String dbData) {
        if(dbData != null) {
            if(!dbData.isBlank()) {
                return Arrays.stream(dbData.split(SEPARATOR))
                        .map(UserRole::valueOf)
                        .collect(Collectors.toCollection(() -> EnumSet.noneOf(UserRole.class)));
            }
        }
        return EnumSet.noneOf(UserRole.class);
    }
}
