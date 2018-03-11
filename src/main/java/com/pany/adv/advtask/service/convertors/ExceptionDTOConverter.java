package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.dto.ExceptionDTO;
import com.pany.adv.advtask.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionDTOConverter {

    public ExceptionDTO toDto(APIException e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.errorCode = e.getErrorCode();
        dto.errorMessage = e.getErrorMessage();
        return dto;
    }

}
