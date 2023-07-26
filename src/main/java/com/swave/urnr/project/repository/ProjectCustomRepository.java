package com.swave.urnr.project.repository;


import com.swave.urnr.project.responsedto.ProjectSearchListResponseDTO;

import java.util.List;

public interface ProjectCustomRepository {


    List<ProjectSearchListResponseDTO> searchProject(String keyword);

}
