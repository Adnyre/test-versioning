package test.dto.converter;

import test.dto.SomethingDto;
import test.model.Something;

public class SomethingDtoConverter {
    public static SomethingDto convertToDto(Something something) {
        SomethingDto dto = new SomethingDto();
        dto.setId(something.getId());
        dto.setNic(something.getNic());
        dto.setEntity(something.getEntity());
        return dto;
    }

    public static Something convertToEntity(SomethingDto dto) {
        Something entity = new Something();
        entity.setId(dto.getId());
        entity.setNic(dto.getNic());
        entity.setEntity(dto.getEntity());
        return entity;
    }
}
