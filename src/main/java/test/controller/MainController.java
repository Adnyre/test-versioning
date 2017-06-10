package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.dao.MainDao;
import test.dto.DependencyDto;
import test.dto.EntityDto;
import test.dto.SomethingDto;
import test.dto.converter.DependencyDtoConverter;
import test.dto.converter.EntityDtoConverter;
import test.dto.converter.SomethingDtoConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private MainDao dao;

    @GetMapping("/mutable-entities")
    public List<EntityDto> getMutableEntities() {
        return dao.getMutableEntities().stream().map(EntityDtoConverter::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/immutable-entities")
    public List<EntityDto> getImmutableEntities() {
        return dao.getImmutableEntities().stream().map(EntityDtoConverter::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/entities")
    public List<EntityDto> getEntities() {
        return dao.getEntities().stream().map(EntityDtoConverter::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/mutable-entities")
    public EntityDto saveImmutableEntity(@RequestBody EntityDto entity) {
        return EntityDtoConverter.convertToDto(dao.saveMutableEntity(EntityDtoConverter.convertToMutableEntity(entity)));
    }

    @PutMapping("/mutable-entities")
    public EntityDto updateImmutableEntity(@RequestBody EntityDto entity) {
        return EntityDtoConverter.convertToDto(dao.updateMutableEntity(EntityDtoConverter.convertToMutableEntity(entity)));
    }

    @PostMapping("/make-immutable")
    public EntityDto makeImmutable(@RequestParam int id) {
        return EntityDtoConverter.convertToDto(dao.makeImmutable(id));
    }

    @PostMapping("/make-mutable")
    public EntityDto makeMutable(@RequestParam int id) {
        return EntityDtoConverter.convertToDto(dao.makeMutable(id));
    }

    @GetMapping("/dependencies")
    public List<DependencyDto> getDependencies() {
        return dao.getDependencies().stream().map(DependencyDtoConverter::convertToEntity).collect(Collectors.toList());
    }

    //for testing
    @GetMapping("/dependency-snapshots")
    public List<DependencyDto> getDependencySnapshots() {
        return dao.getDependencySnapshots().stream().map(DependencyDtoConverter::convertToEntity).collect(Collectors.toList());
    }

    //for testing
    @GetMapping("/something")
    public List<SomethingDto> getSomething() {
        return dao.getSomething().stream().map(SomethingDtoConverter::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/dependencies")
    public DependencyDto saveDependency(@RequestBody DependencyDto dependency) {
        return DependencyDtoConverter.convertToEntity(dao.saveDependency(DependencyDtoConverter.convertToEntity(dependency)));
    }
}
