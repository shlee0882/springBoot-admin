package com.example.test.controller.api;

import com.example.test.controller.CrudController;
import com.example.test.ifs.CrudInterface;
import com.example.test.model.entity.OrderGroup;
import com.example.test.model.network.Header;
import com.example.test.model.network.request.OrderGroupApiRequest;
import com.example.test.model.network.response.OrderGroupApiResponse;
import com.example.test.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}
