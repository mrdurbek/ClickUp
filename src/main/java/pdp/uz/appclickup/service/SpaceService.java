package pdp.uz.appclickup.service;

import java.util.List;

import pdp.uz.appclickup.entity.Space;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.SpaceDTO;

public interface SpaceService {

	public ApiResponse addSpace(SpaceDTO spaceDTO);
	
	public ApiResponse deleteSpace(Integer id);
	
	public ApiResponse editSpace(SpaceDTO spaceDto , Integer id);
	
	public List<Space> getSpaces();
}
