package pdp.uz.appclickup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pdp.uz.appclickup.entity.Space;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.SpaceDTO;
import pdp.uz.appclickup.service.SpaceService;

@RestController
@RequestMapping("/api/space")
public class SpaceController {
	
	@Autowired
	SpaceService spaceService;
	
	@GetMapping
	public ResponseEntity<List<Space>> getSpaces(){
		return ResponseEntity.ok(spaceService.getSpaces());
	}
	
	@PostMapping
	public ResponseEntity<?> addSpace(@RequestBody SpaceDTO spaceDTO){
		ApiResponse apiResponse = spaceService.addSpace(spaceDTO);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ApiResponse apiResponse = spaceService.deleteSpace(id);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editSpace(@PathVariable Integer id , @RequestBody SpaceDTO spaceDTO){
		ApiResponse apiResponse = spaceService.editSpace(spaceDTO , id);
		if(apiResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(405).body(apiResponse.getMessage());
	}
}
