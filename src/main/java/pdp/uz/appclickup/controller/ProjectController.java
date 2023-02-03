package pdp.uz.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.ProjectDto;
import pdp.uz.appclickup.payload.ProjectUserDto;
import pdp.uz.appclickup.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired 
	ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody ProjectDto projectDto){
		ApiResponse apiResponse = projectService.add(projectDto);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ApiResponse apiResponse = projectService.delete(id);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@PathVariable Integer id , @RequestBody ProjectDto projectDto){
		ApiResponse apiResponse = projectService.edit(id ,projectDto);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
	@PostMapping("/{id}/addUser")
	public ResponseEntity<?> addUser(@PathVariable Integer id , @RequestBody ProjectUserDto projectUserDto){
		ApiResponse apiResponse = projectService.addProjectUser(id , projectUserDto);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
		ApiResponse apiResponse = projectService.deleteProjectUser(id);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
	@PostMapping("/editUser/{id}")
	public ResponseEntity<?> editUser(@PathVariable Integer id , @RequestBody ProjectUserDto projectUserDto){
		ApiResponse apiResponse = projectService.editProjectUser(id , projectUserDto);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
}
