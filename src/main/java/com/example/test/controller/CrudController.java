package com.example.test.controller;

import com.example.test.ifs.CrudInterface;
import com.example.test.model.network.Header;
import com.example.test.model.network.response.UserApiResponse;
import com.example.test.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Component
public abstract class CrudController<Req,Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected BaseService<Req,Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody  Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }

    @GetMapping("")
    public Header<List<Res>> search(@PageableDefault(sort="id", direction = Sort.Direction.ASC, size = 15) Pageable pageable){
        return baseService.search(pageable);
    }
}
