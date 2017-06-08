package test.dto.converter;

import test.dto.EntityDto;
import test.model.Entity;
import test.model.MutableEntity;

import java.util.stream.Collectors;

public class EntityDtoConverter {
    public static EntityDto convertToDto(Entity entity) {
        EntityDto dto = new EntityDto();
        dto.setId(entity.getId());
        dto.setNic(entity.getNic());
        dto.setDependency(DependencyDtoConverter.convertToEntity(entity.getDependency()));
        dto.setSomething(entity.getSomething().stream().map(SomethingDtoConverter::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    public static MutableEntity convertToMutableEntity(EntityDto dto) {
        MutableEntity entity = new MutableEntity();
        entity.setId(dto.getId());
        entity.setNic(dto.getNic());
        entity.setDependency(DependencyDtoConverter.convertToEntity(dto.getDependency()));
        entity.setSomething(dto.getSomething().stream().map(SomethingDtoConverter::convertToEntity).collect(Collectors.toList()));
        return entity;
    }

}
