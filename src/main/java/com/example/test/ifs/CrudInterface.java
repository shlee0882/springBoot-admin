package com.example.test.ifs;

import com.example.test.model.network.Header;
import com.example.test.model.network.request.UserApiRequest;
import com.example.test.model.network.response.UserApiResponse;

public interface CrudInterface<Req,Res> {
    Header<Res> create(Req request);    // todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Req request);

    Header delete(Long id);
}
