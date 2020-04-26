package com.example.test.service;

import com.example.test.model.Pagination;
import com.example.test.model.entity.Partner;
import com.example.test.model.network.Header;
import com.example.test.model.network.request.PartnerApiRequest;
import com.example.test.model.network.response.ItemApiResponse;
import com.example.test.model.network.response.PartnerApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partner -> response(partner))
                .map(newPartner -> Header.OK(newPartner))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    @Override
    public Header<List<PartnerApiResponse>> search(Pageable pageable) {
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> itemApiResponseList = partners.stream()
                .map(partner -> response(partner))
                .collect(Collectors.toList());

        // List<UserApiResponse>
        // Header<List<UserApiResponse>>
        Pagination pagination = Pagination.builder()
                .totalPages(partners.getTotalPages())
                .totalElements(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElements(partners.getNumberOfElements())
                .build();

        log.info("{}",pageable);

        return Header.OK(itemApiResponseList, pagination);
    }

    private PartnerApiResponse response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return partnerApiResponse;
    }
}
