package com.salesianostriana.pdam.inmoboscoapi.others;

import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Converter
public class RoleConverterAttribute implements AttributeConverter<EnumSet<UserRole>,String> {
    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(EnumSet<UserRole> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return attribute.stream()
                .map(UserRole::name)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public EnumSet<UserRole> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        String[] roles = dbData.split(SEPARATOR);
        return Arrays.stream(roles)
                .map(UserRole::valueOf)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(UserRole.class)));
    }
}
