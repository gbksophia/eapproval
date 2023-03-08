package com.hitejinro.eapproval.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EApprovalSearchDto {
    private String ifkey;
    private String user;
    private String lt;
}
