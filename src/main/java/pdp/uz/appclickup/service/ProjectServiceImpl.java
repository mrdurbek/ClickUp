package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.Project;
import pdp.uz.appclickup.entity.ProjectUser;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.entity.enums.TaskPermission;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.ProjectDto;
import pdp.uz.appclickup.repository.ProjectUserRepository;
import pdp.uz.appclickup.repository.ProjectRepository;
import pdp.uz.appclickup.repository.SpaceRepository;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    
    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    ProjectUserRepository projectUserRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ApiResponse deleteProject(Integer id) {
        projectRepository.deleteById(id);
        return new ApiResponse("Project O'chirildi",true);
    }

    @Override
    public ApiResponse editProject(Integer id, ProjectDto projectDto) {
        boolean existsByNameAndIdNot = projectRepository.existsByNameAndIdNot(projectDto.getName(), id);
        if (existsByNameAndIdNot){
            return new ApiResponse("Bunday project mavjud",false);
        }
        Project project = projectRepository.getById(id);
        project.setName(projectDto.getName());
        project.setColor(projectDto.getColor());
        project.setSpace(spaceRepository.getById(projectDto.getSpace()));
        project.setAccessType(projectDto.getAccessType());
        project.setArchived(projectDto.getArchived());
        projectRepository.save(project);
        return new ApiResponse("Project Tahrirlandi",true);
    }

    @Override
    public List<Project> getProject() {
        List<Project> projectList = projectRepository.findAll();
        return projectList;
    }

    @Override
    public ApiResponse addProject(ProjectDto projectDto, User user) {

        //Project table
        Project project = new Project();
        project.setName(projectDto.getName());
        project.setColor(projectDto.getColor());
        project.setSpace(spaceRepository.getById(projectDto.getSpace()));
        project.setAccessType(projectDto.getAccessType());
        project.setArchived(projectDto.getArchived());
        projectRepository.save(project);

        //ProjectUser table
        ProjectUser projectUser = new ProjectUser(
                project,
                user,
                TaskPermission.TASK_USER
        );
        projectUserRepository.save(projectUser);

        return new ApiResponse("Project va ProjectUser saqlandi!",true);
    }
}
