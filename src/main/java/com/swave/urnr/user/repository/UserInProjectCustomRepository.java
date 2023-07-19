package com.swave.urnr.user.repository;

import com.swave.urnr.user.domain.User;
import com.swave.urnr.user.responsedto.UserMemberInfoResponseDTO;

import java.util.List;

public interface UserInProjectCustomRepository {

    Integer countMember(Long projectId);

    Integer deleteUser(Long projectId, Long deleteUserId);

    List<UserMemberInfoResponseDTO> getMembers(Long projectId);


}
