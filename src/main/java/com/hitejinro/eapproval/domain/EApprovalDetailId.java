package com.hitejinro.eapproval.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class EApprovalDetailId implements Serializable {
    @Column
    private String tw57fbkey;
    @Column
    private String twuser;
    @Column
    private int twukid;
}
