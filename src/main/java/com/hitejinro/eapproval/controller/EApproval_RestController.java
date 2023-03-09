package com.hitejinro.eapproval.controller;

import com.hitejinro.eapproval.domain.EApprovalDetailRepository;
import com.hitejinro.eapproval.dto.EApprovalSearchDto;
import com.hitejinro.eapproval.service.EApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EApproval_RestController {

    final
    EApprovalService eApprovalService;

    public EApproval_RestController(EApprovalService eApprovalService) {
        this.eApprovalService = eApprovalService;
    }

    @PostMapping("/eApproval/rest/search")
    public JSONArray searchEApprovalDetailData(@RequestBody EApprovalSearchDto searchDto){
        return eApprovalService.getEApprovalDetail(searchDto);
    }
}
