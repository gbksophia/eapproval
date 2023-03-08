package com.hitejinro.eapproval.service;


import com.hitejinro.eapproval.dto.EApprovalDetailDto;
import com.hitejinro.eapproval.dto.EApprovalSearchDto;
import org.json.simple.JSONArray;

import java.util.List;

public interface EApprovalService {
    JSONArray getEApprovalDetail(EApprovalSearchDto searchDto);
}
