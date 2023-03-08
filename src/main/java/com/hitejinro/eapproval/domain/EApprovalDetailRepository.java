package com.hitejinro.eapproval.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EApprovalDetailRepository extends JpaRepository<EApprovalDetail, String>, EApprovalDetailRepositoryCustom {

    List<EApprovalDetail> findByIfkeyAndUserAndLt(String ifkey, String user, String lt);

    @Query( nativeQuery = true,
            value = "SELECT * " +
                    "FROM VWF57FBWB" +
                    " WHERE 1=1 " +
                    "   AND TW57IFKEY = TRIM(:ifkey)" +
                    "   AND TWUSER = TRIM(:user)" +
                    "   AND TWLT = TRIM(:lt)"
    )
    List<EApprovalDetail> findEApprovalDetailList(
            @Param(value = "ifkey") String ifkey,
            @Param(value = "user") String user,
            @Param(value = "lt") String lt);
}
