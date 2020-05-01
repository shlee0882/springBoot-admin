package com.example.test.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {
    ALL(0, "묶음","모든상품을 묶음 발송"),
    EACH(1,"개별","모든상품을 준비되는대로 발송"),
    COMPLETE(2, "완료", "완료")
    ;

    public Integer id;
    public String title;
    public String description;
}
