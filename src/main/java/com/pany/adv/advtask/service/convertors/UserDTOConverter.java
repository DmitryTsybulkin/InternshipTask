package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDTOConverter {

    public UserDTO toDto(User user) {
        final UserDTO dto = new UserDTO();
        dto.id = user.getId();
        dto.admin = user.isAdmin();
        dto.editor = user.isEditor();
        dto.login = user.getLogin();
        dto.municipalities = user.getMunicipality();
        dto.name = user.getName();
        dto.patronymic = user.getPatronymic();
        dto.surname = user.getSurname();
        return dto;
    }

}
