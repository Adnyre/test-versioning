package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.dao.MainDao;
import test.model.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainDao dao;

    @GetMapping("/mutable-entities")
    public List<MutableEntity> getMutableEntities() {
        return dao.getMutableEntities();
    }

    @GetMapping("/immutable-entities")
    public List<ImmutableEntity> getImmutableEntities() {
        return dao.getImmutableEntities();
    }

    @GetMapping("/entities")
    public List<Entity> getEntities() {
        return dao.getEntities();
    }

    @PostMapping("/mutable-entities")
    public MutableEntity saveImmutableEntity(@RequestBody MutableEntity entity) {
        return dao.saveMutableEntity(entity);
    }

    @PostMapping("/make-immutable")
    public ImmutableEntity makeImmutable(@RequestParam int id) {
        return dao.makeImmutable(id);
    }

    @GetMapping("/dependencies")
    public List<Dependency> getDependencies() {
        return dao.getDependencies();
    }

    @PostMapping("/dependencies")
    public Dependency saveDependency(@RequestBody Dependency dependency) {
        return dao.saveDependency(dependency);
    }
}
