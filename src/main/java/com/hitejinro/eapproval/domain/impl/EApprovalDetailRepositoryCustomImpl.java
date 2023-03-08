package com.hitejinro.eapproval.domain.impl;

import com.hitejinro.eapproval.domain.EApprovalDetailRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EApprovalDetailRepositoryCustomImpl implements EApprovalDetailRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public EApprovalDetailRepositoryCustomImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
}
