package com.example.test.controller.api;

import com.example.test.controller.CrudController;
import com.example.test.model.entity.Partner;
import com.example.test.model.network.request.PartnerApiRequest;
import com.example.test.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

}
