package pdp.uz.appclickup.service;


import pdp.uz.appclickup.entity.Project;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.ProjectDto;

import java.util.List;


public interface ProjectService {

    ApiResponse addProject(ProjectDto projectDto, User user);

    List<Project> getProject();

    ApiResponse editProject(Integer id, ProjectDto projectDto);

    ApiResponse deleteProject(Integer id);
}
