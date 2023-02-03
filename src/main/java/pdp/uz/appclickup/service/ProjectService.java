package pdp.uz.appclickup.service;

import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.ProjectDto;
import pdp.uz.appclickup.payload.ProjectUserDto;

public interface ProjectService {
	
	public ApiResponse add(ProjectDto projectDto);
	
	public ApiResponse delete(Integer id);
	
	public ApiResponse edit(Integer id , ProjectDto projectDto);
	
	public ApiResponse addProjectUser(Integer id , ProjectUserDto projectUserDto);
	
	public ApiResponse deleteProjectUser(Integer id);
	
	public ApiResponse editProjectUser(Integer id , ProjectUserDto projectUserDto);
}
