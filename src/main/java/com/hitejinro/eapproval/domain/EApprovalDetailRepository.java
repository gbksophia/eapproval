package com.hitejinro.eapproval.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EApprovalDetailRepository extends JpaRepository<EApprovalDetail, String>, EApprovalDetailRepositoryCustom {

    List<EApprovalDetail> findByFbkeyAndUserAndLt(String fbkey, String user, String lt);

    @Query( nativeQuery = true,
            value = "SELECT * " +
                    "FROM VWF57FBWB" +
                    " WHERE 1=1 " +
                    "   AND TW57IFKEY = :fbkey" +
                    "   AND TWUSER = :user" +
                    "   AND TWLT = :lt"
    )
    List<EApprovalDetail> findEApprovalDetailList(
            @Param(value = "fbkey") String fbkey,
            @Param(value = "user") String user,
            @Param(value = "lt") String lt);
}
