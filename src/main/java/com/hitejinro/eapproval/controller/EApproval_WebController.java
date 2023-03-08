package com.hitejinro.eapproval.controller;

import com.hitejinro.eapproval.domain.EApprovalDetailRepository;
import com.hitejinro.eapproval.dto.EApprovalDetailDto;
import com.hitejinro.eapproval.dto.EApprovalSearchDto;
import com.hitejinro.eapproval.service.EApprovalService;
import com.hitejinro.eapproval.service.impl.EApprovalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class EApproval_WebController {

    final
    EApprovalService eApprovalService;
    EApprovalDetailRepository eAppDetailRepository;

    public EApproval_WebController(EApprovalService eApprovalService, EApprovalDetailRepository eAppDetailRepository){
        this.eApprovalService = eApprovalService;
        this.eAppDetailRepository = eAppDetailRepository;
    }

    @Value("${spring.profiles.active}")
    private String env;

    @GetMapping(value = "/")
    public String eapproval_main_index(Model model){
        EApprovalSearchDto searchDto = new EApprovalSearchDto();
        searchDto.setFbkey("AA382564                                ");
        searchDto.setUser("CON04               ");
        searchDto.setLt("43352");
        List<EApprovalDetailDto> list = eApprovalService.getEApprovalDetail(searchDto);
        log.info("list="+ list);
        return "main/main_index";
    }

}
