package pdp.uz.appclickup.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import pdp.uz.appclickup.entity.Project;
import pdp.uz.appclickup.entity.ProjectUser;
import pdp.uz.appclickup.entity.Space;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.entity.enums.TaskPermission;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.ProjectDto;
import pdp.uz.appclickup.payload.ProjectUserDto;
import pdp.uz.appclickup.repository.ProjectRepository;
import pdp.uz.appclickup.repository.ProjectUserRepository;
import pdp.uz.appclickup.repository.SpaceRepository;
import pdp.uz.appclickup.repository.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ProjectUserRepository projectUserRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SpaceRepository spaceRepository;
	
	@Override
	public ApiResponse add(ProjectDto projectDto) {
		Project project = new Project();
		project.setName(projectDto.getName());
		project.setColor(projectDto.getColor());
		Optional<Space> optionalSpace = spaceRepository.findById(projectDto.getSpaceId());
		if(optionalSpace.isEmpty()) {
			return new ApiResponse("Space Not Found" , false);
		}
		project.setSpace(optionalSpace.get());
		project.setAccessType(projectDto.getAccessType());
		project.setAccessType(projectDto.getArchived());
		projectRepository.save(project);
		return new ApiResponse("Project has been created" , true);
	}
	
	@Transactional
	@Modifying
	@Override
	public ApiResponse delete(Integer id) {
		try {
			projectRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse("Error occured" , false);
		}
		return new ApiResponse("Project has been deleted" , true);
	}

	@Override
	public ApiResponse edit(Integer id, ProjectDto projectDto) {
		Optional<Project> optionalProject = projectRepository.findById(id);
		if(optionalProject.isEmpty()) {
			return new ApiResponse("Not Found" , false);
		}
		
		Project project = optionalProject.get();
		
		project.setName(projectDto.getName());
		project.setColor(projectDto.getColor());
		Optional<Space> optionalSpace = spaceRepository.findById(projectDto.getSpaceId());
		if(optionalSpace.isPresent()) {
			project.setSpace(optionalSpace.get());
		}
		project.setAccessType(projectDto.getAccessType());
		project.setAccessType(projectDto.getArchived());
		projectRepository.save(project);
		return new ApiResponse("Project has been updated" , true);
	}

	@Override
	public ApiResponse addProjectUser(Integer id , ProjectUserDto projectUserDto) {
		ProjectUser projectUser = new ProjectUser();
		Optional<Project> optionalProject = projectRepository.findById(id);
		if(optionalProject.isEmpty()) {
			new ApiResponse("Project Not Found" , false);
		}
		projectUser.setProject(optionalProject.get());
		
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isEmpty()) {
			return new ApiResponse("User has not been found" , false);
		}
		projectUser.setUser(optionalUser.get());
		projectUser.setTaskPermission(TaskPermission.TASK_USER);
		
		projectUserRepository.save(projectUser);
		
		return new ApiResponse("User has been added" , true);
	}
	
	@Transactional
	@Modifying
	@Override
	public ApiResponse deleteProjectUser(Integer id) {
		try {
			projectUserRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse("Error occured" , false);
		}
		
		return new ApiResponse("This user of project has been deleted" , true);
	}

	@Override
	public ApiResponse editProjectUser(Integer id, ProjectUserDto projectUserDto) {
		Optional<ProjectUser> optionalProjectUser = projectUserRepository.findById(id);
		if(optionalProjectUser.isEmpty()) {
			return new ApiResponse("User has not been found" , false);
		}
		ProjectUser projectUser = optionalProjectUser.get();
		Optional<Project> optionalProject = projectRepository.findById(id);
		if(optionalProject.isPresent()) {
			projectUser.setProject(optionalProject.get());
		}
		
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			projectUser.setUser(optionalUser.get());
		}
		
		projectUserRepository.save(projectUser);
		
		return new ApiResponse("User has been updated" , true);
	}
	
}
