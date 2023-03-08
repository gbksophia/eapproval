package com.hitejinro.eapproval.service.impl;

import com.hitejinro.eapproval.domain.EApprovalDetail;
import com.hitejinro.eapproval.domain.EApprovalDetailRepository;
import com.hitejinro.eapproval.dto.EApprovalDetailDto;
import com.hitejinro.eapproval.dto.EApprovalSearchDto;
import com.hitejinro.eapproval.service.EApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service @Slf4j
public class EApprovalServiceImpl implements EApprovalService {

    final
    EApprovalDetailRepository eApprovalDetailRepository;
    ModelMapper modelMapper;

    public EApprovalServiceImpl (EApprovalDetailRepository eApprovalDetailRepository, ModelMapper modelMapper){
        this.eApprovalDetailRepository = eApprovalDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JSONArray getEApprovalDetail(EApprovalSearchDto searchDto){
        log.info("searchDto="+ searchDto);
        /*List<EApprovalDetailDto> eappDetailList = eApprovalDetailRepository.findByIfFkeyAndUserAndLt
                (searchDto.getIfkey(), searchDto.getUser(), searchDto.getLt()).stream()
                .map(eApprovalDetail -> modelMapper.map(eApprovalDetail, EApprovalDetailDto.class))
                .collect(Collectors.toList());*/

        List<EApprovalDetailDto> eAppDetailList = eApprovalDetailRepository.findEApprovalDetailList
                        (searchDto.getIfkey(), searchDto.getUser(), searchDto.getLt()).stream()
                .map(eApprovalDetail -> modelMapper.map(eApprovalDetail, EApprovalDetailDto.class))
                .collect(Collectors.toList());

        JSONArray eAppDetailArr = new JSONArray();
        for(EApprovalDetailDto dto: eAppDetailList){
            String aaValidate = validateIfAAIsDebitOrCredit(dto.getAa());
            log.info("aaValidate="+ aaValidate);
            if(aaValidate.equals("debit")){
                dto.setAaDebit(dto.getAa());
            }else if(aaValidate.equals("credit")){
                dto.setAaCredit(dto.getAa() * -1);
            }
            log.info("aa="+  dto.getAa() + " || aaDebit="+  dto.getAaDebit() + " || aaCredit="+  dto.getAaCredit());

            //JSONObject jObj = EApprovalDetailDtoIntoJSONObject(dto.toString());
            JSONObject jObj = eAppDetailDtoIntoJSONObject(dto);
            eAppDetailArr.add(jObj);

        }
        //log.info("eAppDetailList="+ eAppDetailList);
        log.info("eAppDetailArr="+ eAppDetailArr);

        return eAppDetailArr;
    }

    private String validateIfAAIsDebitOrCredit(Float aa){
        String result = null;

        if(aa != null){

            log.info("aa=" + aa + " || Math.signum(aa)="+ Math.signum(aa));

            if(Math.signum(aa) == 1.0 || Math.signum(aa) == 1.0f ){
                return "debit";
            }else if(Math.signum(aa) == -1.0 || Math.signum(aa) == -1.0f ){
                return "credit";
            }

            /*if(Math.signum(twaa).compareTo(1.0f) == 0){
                return "debit";
            }else if (Math.signum(twaa).compareTo(-1.0f) == 0) {
                return "credit";
            }*/

        }
        return result;
    }

    private JSONObject EApprovalDetailDtoIntoJSONObject(String objectToString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String obj = objectToString.substring(0, objectToString.length() - 1);
        log.debug("--- objectToString : " + objectToString);
        log.debug("--- obj(1) : " + obj);
        log.debug("--- ogj.split 결과="+ Arrays.toString(obj.split("\\(")));
        obj = obj.split("\\(")[1];
        String[] objArr = obj.split(",");
        log.debug("--- obj(2) : " + obj);
        log.debug("--- objArr : " + objArr);

        Map<String, Object> objMap = new HashMap<>();
        for (String objStr : objArr) {
            log.debug("--- objStr : " + objStr);
            String key = objStr.trim().split("=")[0];
            java.io.Serializable value;
            try {
                value = objStr.trim().split("=")[1];
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                log.error("---- 에러발생: toJsonObject : " + aiobe.getMessage());
                value = null;
            }
            if (value != null && value.equals("null")) {
                value = null;
            }
            if (isCastToDate(value + "")) {
                value = sdf.format(value);
            }
            if (isCastToInteger(value + "")) {
                value = Integer.parseInt(value + "");
            }
            if (isCastToBoolean(value + "")) {
                value = Boolean.parseBoolean(value + "");
            }
            objMap.put(key, value);
        }
        return new JSONObject(objMap);
    }

    private boolean isCastToDate(String dateString) {
        try {
            Date d = new Date(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isCastToLocalDate(String dateString) {
        try {
            LocalDate d = LocalDate.parse(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isCastToFloat(String floatString) {
        try {
            float i = Float.parseFloat(floatString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isCastToInteger(String integerString) {
        try {
            int i = Integer.parseInt(integerString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isCastToBoolean(String booleanString) {
        return booleanString.equalsIgnoreCase("true") || booleanString.equalsIgnoreCase("false");
    }

    private JSONObject eAppDetailDtoIntoJSONObject(EApprovalDetailDto dto){
        JSONObject jObj = new JSONObject();
        jObj.put("id", dto.getId());
        jObj.put("ifkey", dto.getIfkey());
        jObj.put("user", dto.getUser());
        jObj.put("ukid", dto.getUkid());
        jObj.put("lt", dto.getLt());
        jObj.put("icut" ,dto.getIcut());
        jObj.put("dl10" ,dto.getDl10());
        jObj.put("icu", dto.getIcu());
        jObj.put("kco", dto.getKco());
        jObj.put("dct", dto.getDct());
        jObj.put("dl01", dto.getDl01());
        jObj.put("doc", dto.getDoc());

        jObj.put("dgj", dto.getDgj());
        jObj.put("gddgj", dto.getGddgj());
        jObj.put("dicj", dto.getDicj());
        jObj.put("gddicj", dto.getGddicj());
        jObj.put("dsvj", dto.getDsvj());
        jObj.put("gddsvj", dto.getGddsvj());

        jObj.put("mcu", dto.getMcu());
        jObj.put("dl02", dto.getDl02());
        jObj.put("obj", dto.getObj());
        jObj.put("sub", dto.getSub());
        jObj.put("ani", dto.getAni());
        jObj.put("dl03", dto.getDl03());

        jObj.put("aa", dto.getAa());
        jObj.put("aaDebit", dto.getAaDebit());
        jObj.put("aaCredit", dto.getAaCredit());


        jObj.put("acr", dto.getAcr());
        jObj.put("crcd", dto.getCrcd());
        jObj.put("crr", dto.getCrr());
        jObj.put("fy", dto.getFy());
        jObj.put("pn", dto.getPn());
        jObj.put("re", dto.getRe());

        jObj.put("hdgj", dto.getHdgj());
        jObj.put("gdhdgj", dto.getGdhdgj());


        jObj.put("exr", dto.getExr());
        jObj.put("exa", dto.getExa());
        jObj.put("dcto", dto.getDcto());
        jObj.put("pdct", dto.getPdct());
        jObj.put("po", dto.getPo());
        jObj.put("u", dto.getU());
        jObj.put("um", dto.getUm());
        jObj.put("sblt", dto.getSblt());
        jObj.put("sbl", dto.getSbl());
        jObj.put("dl09", dto.getDl09());
        jObj.put("glc", dto.getGlc());
        jObj.put("itm", dto.getItm());

        jObj.put("dsc1", dto.getDsc1());
        jObj.put("exr1", dto.getExr1());
        jObj.put("txa1", dto.getTxa1());
        jObj.put("vinv", dto.getVinv());
        jObj.put("asid", dto.getAsid());
        jObj.put("dl04", dto.getDl04());

        return jObj;
    }
}
