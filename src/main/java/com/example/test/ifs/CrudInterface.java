package com.example.test.ifs;

import com.example.test.model.network.Header;
import com.example.test.model.network.request.UserApiRequest;
import com.example.test.model.network.response.UserApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudInterface<Req,Res> {
    Header<Res> create(Header<Req> request);    // todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);

    Header<List<Res>> search(Pageable pageable);

}
