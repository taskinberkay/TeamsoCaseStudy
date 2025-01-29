package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.dtos.PagedResponse;
import com.btaskin.ticketmanagementsystem.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public class BaseRestController<Entity, ID, Repository extends JpaRepository<Entity, ID>, Service extends BaseService<Entity, ID, Repository>> {
    @Autowired
    protected Service service;

    @PostMapping(path = "/save")
    public Entity save(@RequestBody Entity entity) {
        return service.save(entity);
    }

    @GetMapping(path = "/findById/{id}")
    public Entity find(@PathVariable ID id) {
        return (Entity) service.findById(id);
    }

    @GetMapping(path = "/findAll")
    public List<Entity> findAll() {
        return service.findAll();
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable ID id) {
        service.deleteById(id);
        return ResponseEntity.ok("true");
    }

    @PostMapping(path = "/findAllByParams")
    public PagedResponse<Entity> findAllByParams(@RequestBody Map<String, Object> parameterMap) {
        return service.findAllByParams(parameterMap);
    }
}
