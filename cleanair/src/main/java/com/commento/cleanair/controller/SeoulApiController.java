package com.commento.cleanair.controller;

import com.commento.cleanair.service.SeoulApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/air-info")
//@RequiredArgsConstructor 이걸 사용해도 됨
public class SeoulApiController {

    private final SeoulApiService seoulApiService;

    //@Autowired 생략됨
    public SeoulApiController(SeoulApiService seoulApiService) {
        this.seoulApiService = seoulApiService;
    }

    @GetMapping("/seoul")
    public String getAllAirConditionInfo(){
        
        return "ok";
    }


}
