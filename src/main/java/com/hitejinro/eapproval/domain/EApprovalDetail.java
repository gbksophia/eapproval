package com.hitejinro.eapproval.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="VWF57FBWB")
@Entity
public class EApprovalDetail {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(name="tw57ifkey", nullable = false)
    private String ifkey;

    @Column(name="twuser", nullable = false)
    private String user;

    @Column(name="twukid", nullable = false)
    private int ukid;

    @Column(name="twlt")
    private String lt;

    @Column(name="twicut")
    private String icut;

    @Column(name="twdl10")
    private String dl10;

    @Column(name="twicu")
    private Integer icu;

    @Column(name="twkco")
    private String kco;

    @Column(name="twdct")
    private String dct;

    @Column(name="twdl01")
    private String dl01;

    @Column(name="twdoc")
    private Integer doc;

    @Column(name="twdgj")
    private int dgj;

    @Column(name="twdicj")
    private int dicj;

    @Column(name="twdsvj")
    private int dsvj;

    @Column(name="twmcu")
    private String mcu;

    @Column(name="twdl02")
    private String dl02;

    @Column(name="twobj")
    private String obj;

    @Column(name="twsub")
    private String sub;

    @Column(name="twani")
    private String ani;

    @Column(name="twdl03")
    private String dl03;

    @Column(name="twaa")
    private Float aa;

    @Column(name="twacr")
    private Float acr;

    @Column(name="twcrcd")
    private String crcd;

    @Column(name="twcrr")
    private Integer crr;

    @Column(name="twfy")
    private Integer fy;

    @Column(name="twpn")
    private Integer pn;

    @Column(name="twre")
    private String re;

    @Column(name="twhdgj")
    private int hdgj;

    @Column(name="twexr")
    private String exr;

    @Column(name="twexa")
    private String exa;

    @Column(name="twdcto")
    private String dcto;

    @Column(name="twpdct")
    private String pdct;

    @Column(name="twpo")
    private String po;

    @Column(name="twu")
    private Float u;

    @Column(name="twum")
    private String um;

    @Column(name="twsblt")
    private String sblt;

    @Column(name="twsbl")
    private String sbl;

    @Column(name="twdl09")
    private String dl09;

    @Column(name="twglc")
    private String glc;

    @Column(name="twitm")
    private Integer itm;

    @Column(name="twdsc1")
    private String dsc1;

    @Column(name="twexr1")
    private String exr1;

    @Column(name="twtxa1")
    private String txa1;

    @Column(name="twvinv")
    private String vinv;

    @Column(name="twasid")
    private String asid;

    @Column(name="twdl04")
    private String dl04;

    @Column
    private String gddgj;

    @Column
    private String gddicj;

    @Column
    private String gddsvj;

    @Column
    private String gdhdgj;
}
