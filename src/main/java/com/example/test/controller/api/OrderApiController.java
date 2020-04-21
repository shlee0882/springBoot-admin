package com.example.test.controller.api;

import com.example.test.ifs.CrudInterface;
import com.example.test.model.network.Header;
import com.example.test.model.network.request.UserApiRequest;
import com.example.test.model.network.response.UserApiResponse;

public class OrderApiController implements CrudInterface {
    @Override
    public Header<UserApiResponse> create(UserApiRequest request) {
        return null;
    }

    @Override
    public Header read(Long id) {
        return null;
    }

    @Override
    public Header update() {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
