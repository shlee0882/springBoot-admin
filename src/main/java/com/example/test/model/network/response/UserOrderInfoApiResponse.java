package com.example.test.model.network.response;

import com.example.test.model.entity.OrderGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderInfoApiResponse {
    private UserApiResponse userApiResponse;
}
