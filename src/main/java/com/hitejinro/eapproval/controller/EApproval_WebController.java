package com.hitejinro.eapproval.controller;

import com.hitejinro.eapproval.domain.EApprovalDetailRepository;
import com.hitejinro.eapproval.dto.EApprovalDetailDto;
import com.hitejinro.eapproval.dto.EApprovalSearchDto;
import com.hitejinro.eapproval.service.EApprovalService;
import com.hitejinro.eapproval.service.impl.EApprovalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
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

    @GetMapping(value = "/eapproval/{ifkey}/{user}")
    public String eapproval_main_index(@PathVariable String ifkey, @PathVariable  String user, Model model){
        log.info("-- eapproval interface web 링크 들어감.");

        model.addAttribute("ifkey", ifkey);
        model.addAttribute("user", user);

        return "main_index";
    }

}
