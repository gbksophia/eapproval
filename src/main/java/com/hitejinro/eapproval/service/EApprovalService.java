package com.hitejinro.eapproval.service;


import com.hitejinro.eapproval.dto.EApprovalDetailDto;
import com.hitejinro.eapproval.dto.EApprovalSearchDto;

import java.util.List;

public interface EApprovalService {
    List<EApprovalDetailDto> getEApprovalDetail(EApprovalSearchDto searchDto);
}
