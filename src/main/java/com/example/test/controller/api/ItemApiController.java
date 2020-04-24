package com.example.test.controller.api;

import com.example.test.ifs.CrudInterface;
import com.example.test.model.network.Header;
import com.example.test.model.network.request.ItemApiRequest;
import com.example.test.model.network.request.UserApiRequest;
import com.example.test.model.network.response.ItemApiResponse;
import com.example.test.model.network.response.UserApiResponse;
import com.example.test.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")    // /api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        log.info("{}",request);
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")   // /api/item/1 ... 1000
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        log.info("{}", id);
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")     // /api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        log.info("{}", request);
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/item/1 ... 1000
    public Header delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }
}
