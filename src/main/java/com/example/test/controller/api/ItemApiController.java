package com.example.test.controller.api;

import com.example.test.ifs.CrudInterface;
import com.example.test.model.network.Header;
import com.example.test.model.network.request.ItemApiRequest;
import com.example.test.model.network.response.ItemApiResponse;
import com.example.test.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")    // /api/item
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")   // /api/item/1 ... 1000
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")     // /api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // /api/item/1 ... 1000
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
