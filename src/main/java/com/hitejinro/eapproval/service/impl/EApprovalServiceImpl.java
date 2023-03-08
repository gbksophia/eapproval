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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                (searchDto.getFbkey(), searchDto.getUser(), searchDto.getLt()).stream()
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

            JSONObject jObj = toJSONObject(dto.toString());
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

    private JSONObject toJSONObject(String objectToString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String obj = objectToString.substring(0, objectToString.length() - 1);
        obj = obj.split("\\(")[1];
        String[] objArr = obj.split(",");

        Map<String, Object> objMap = new HashMap<>();
        for (String objStr : objArr) {
            //log.debug("--- objStr : " + objStr);
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
}
