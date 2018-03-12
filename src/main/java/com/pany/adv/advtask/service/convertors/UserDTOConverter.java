package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDTOConverter {

    private final MunicipalityDTOConverter converter;

    @Autowired
    public UserDTOConverter(MunicipalityDTOConverter converter) {
        this.converter = converter;
    }

    public UserDTO toDto(User user) {
        final UserDTO dto = new UserDTO();
        dto.id = user.getId();
        dto.role = user.getRole();
        dto.login = user.getLogin();
        dto.municipalities = converter.toDtoList(user.getMunicipalities());
        dto.name = user.getName();
        dto.patronymic = user.getPatronymic();
        dto.surname = user.getSurname();
        return dto;
    }

}
