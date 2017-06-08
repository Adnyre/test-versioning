package test.dto.converter;

import test.dto.DependencyDto;
import test.model.Dependency;
import test.model.DependencyAbstract;

public class DependencyDtoConverter {
    public static DependencyDto convertToEntity(DependencyAbstract dependency) {
        DependencyDto dto = new DependencyDto();
        dto.setId(dependency.getId());
        dto.setNic(dependency.getNic());
        return dto;
    }

    public static Dependency convertToEntity(DependencyDto dto) {
        Dependency entity = new Dependency();
        entity.setId(dto.getId());
        entity.setNic(dto.getNic());
        return entity;
    }
}
