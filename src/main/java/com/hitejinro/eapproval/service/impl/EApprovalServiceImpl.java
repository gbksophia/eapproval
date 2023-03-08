package com.hitejinro.eapproval.service.impl;

import com.hitejinro.eapproval.domain.EApprovalDetailRepository;
import com.hitejinro.eapproval.dto.EApprovalDetailDto;
import com.hitejinro.eapproval.dto.EApprovalSearchDto;
import com.hitejinro.eapproval.service.EApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<EApprovalDetailDto> getEApprovalDetail(EApprovalSearchDto searchDto){
        log.info("searchDto="+ searchDto);
        /*List<EApprovalDetailDto> eappDetailList = eApprovalDetailRepository.findByFbkeyAndUserAndLt
                (searchDto.getFbkey(), searchDto.getUser(), searchDto.getLt()).stream()
                .map(eApprovalDetail -> modelMapper.map(eApprovalDetail, EApprovalDetailDto.class))
                .collect(Collectors.toList());*/

        List<EApprovalDetailDto> eAppDetailList = eApprovalDetailRepository.findEApprovalDetailList
                        (searchDto.getFbkey(), searchDto.getUser(), searchDto.getLt()).stream()
                .map(eApprovalDetail -> modelMapper.map(eApprovalDetail, EApprovalDetailDto.class))
                .collect(Collectors.toList());


        for(EApprovalDetailDto dto: eAppDetailList){
            String aaValidate = validateIfAAIsDebitOrCredit(dto.getAa());
            log.info("aaValidate="+ aaValidate);
            if(aaValidate.equals("debit")){
                dto.setAaDebit(dto.getAa());
            }else if(aaValidate.equals("credit")){
                dto.setAaCredit(dto.getAa()*-1);
            }
            log.info("twaa="+  dto.getAa() + " || twaaDebit="+  dto.getAaDebit() + " || twaaCredit="+  dto.getAaCredit());
        }
        log.info("eAppDetailList="+ eAppDetailList);

        return eAppDetailList;
    }

    private String validateIfAAIsDebitOrCredit(Float aa){
        String result = null;

        if(aa != null){
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
}
