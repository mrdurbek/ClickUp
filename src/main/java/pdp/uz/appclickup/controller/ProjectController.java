package pdp.uz.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appclickup.entity.Project;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.ProjectDto;
import pdp.uz.appclickup.security.CurrentUser;
import pdp.uz.appclickup.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public HttpEntity<?> getProject(){
        List<Project> project = projectService.getProject();
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public HttpEntity<?> addProject(@Valid @RequestBody ProjectDto projectDto, @CurrentUser User user){
        ApiResponse apiResponse = projectService.addProject(projectDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProject(@PathVariable Integer id,@RequestBody ProjectDto projectDto){
        ApiResponse apiResponse = projectService.editProject(id, projectDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProject(@PathVariable Integer id){
        ApiResponse apiResponse = projectService.deleteProject(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }



}
