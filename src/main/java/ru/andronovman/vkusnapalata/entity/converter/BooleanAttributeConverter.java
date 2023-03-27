package ru.andronovman.vkusnapalata.entity.converter;

import javax.persistence.AttributeConverter;

public class BooleanAttributeConverter implements AttributeConverter<Boolean, Character> {
    @Override
    public Character convertToDatabaseColumn(Boolean value) {
        if (value == null) {
            return 'N';
        }
        return value ? 'Y' : 'N';
    }

    @Override
    public Boolean convertToEntityAttribute(Character value) {
        if (value == null) {
            return false;
        }
        if ('Y' == value) {
            return true;
        }
        return false;
    }
}
