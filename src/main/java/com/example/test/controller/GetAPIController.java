package com.example.test.controller;
import com.example.test.model.SearchVO;
import com.example.test.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController // 여기는 컨트롤러라고 알려주는 @RestController 어노테이션 사용
@RequestMapping("/api") // 여기로 들어올 path를 지정할 @RequestMapping 어노테이션 사용 localhost:8080/api
public class GetAPIController {

    @RequestMapping(method = RequestMethod.GET, path = "/getRequest")   // localhost:8080/api/getRequest
    public String getRequest(){
        return "this is getRequest";
    }

    // GetMapping은 RequestMapping과는 다르게 메소드유형 없이 주소만 지정해주면된다.
    @GetMapping("/getParameters")  // localhost:8080/api/getParameters?id=shlee0882&email=shlee0882@gmail.com
    public String getParameters(@RequestParam String id, @RequestParam String email){
        return "아이디는 "+id+" 이메일은 "+email;
    }

    @GetMapping("/getMultiParameters")
    public String getMultiParameters(SearchVO searchVo) {
        return "";
        // return "VO사용 아이디는 "+searchVo.getId()+" 이메일은 "+searchVo.getEmail();
    }

    @GetMapping("/getMultiParametersRtnJson")
    public SearchVO getMultiParametersRtnJson(SearchVO searchVo) {
        return searchVo;
    }

    @GetMapping("/header")
    public Header getHeader(){

        // {"resultCode: "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
