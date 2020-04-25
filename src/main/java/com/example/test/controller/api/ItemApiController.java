package com.example.test.controller.api;

import com.example.test.controller.CrudController;
import com.example.test.ifs.CrudInterface;
import com.example.test.model.entity.Item;
import com.example.test.model.network.Header;
import com.example.test.model.network.request.ItemApiRequest;
import com.example.test.model.network.request.UserApiRequest;
import com.example.test.model.network.response.ItemApiResponse;
import com.example.test.model.network.response.UserApiResponse;
import com.example.test.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}
