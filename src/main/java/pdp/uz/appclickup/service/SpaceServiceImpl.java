package pdp.uz.appclickup.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import pdp.uz.appclickup.entity.Attachment;
import pdp.uz.appclickup.entity.Icons;
import pdp.uz.appclickup.entity.Space;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.entity.WorkSpace;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.SpaceDTO;
import pdp.uz.appclickup.repository.AttachmentRepository;
import pdp.uz.appclickup.repository.IconRepository;
import pdp.uz.appclickup.repository.SpaceRepository;
import pdp.uz.appclickup.repository.UserRepository;
import pdp.uz.appclickup.repository.WorkSpaceRepository;

@Service
public class SpaceServiceImpl implements SpaceService{

	@Autowired
	SpaceRepository spaceRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	@Autowired
	WorkSpaceRepository workSpaceRepository;
	@Autowired
	IconRepository iconRepository;
	
	@Override
	public ApiResponse addSpace(SpaceDTO spaceDTO) {
		
		Space space = new Space();
		space.setName(spaceDTO.getName());
		space.setColor(spaceDTO.getColor());
		Optional<WorkSpace> optionalWorkspace = workSpaceRepository.findById(spaceDTO.getWorkSpaceId());
		if(optionalWorkspace.isEmpty()) {
			return new ApiResponse("Workspace Not Found" , false);
		}
		space.setWorkSpace(optionalWorkspace.get());
		
		Optional<Icons> optionalIcons = iconRepository.findById(spaceDTO.getIconId());
		if(optionalIcons.isEmpty()) {
			return new ApiResponse("Icon Not Found" , false);
		}
		space.setIcons(optionalIcons.get());
		
		
		Optional<Attachment> optionalAvatar = attachmentRepository.findById(spaceDTO.getAvatartId());
		if(optionalAvatar.isEmpty()) {
			return new ApiResponse("Avatar Not Found" , false);
		}
		space.setAttachment(optionalAvatar.get());
		
		Optional<User> optionalOwner = userRepository.findById(spaceDTO.getOwnerId());
		if(optionalOwner.isEmpty()) {
			return new ApiResponse("User Not Found" , false);
		}
		space.setOwner(optionalOwner.get());
		
		space.setAccessType(spaceDTO.getAccessType());
		
		spaceRepository.save(space);
			
		return new ApiResponse("Succesfully created" , true);
	}
	
	@Transactional
	@Modifying
	@Override
	public ApiResponse deleteSpace(Integer id) {
		try {
			spaceRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse("Error occured" , false);
		}
		return new ApiResponse("Space has been deleted" , true);
	}

	@Override
	public ApiResponse editSpace(SpaceDTO spaceDTO, Integer id) {
		Optional<Space> optionalSpace = spaceRepository.findById(id);
		if(optionalSpace.isEmpty()) {
			return new ApiResponse("Not Found" , false);
		}
		Space space = optionalSpace.get();
		space.setName(spaceDTO.getName());
		space.setColor(spaceDTO.getColor());
		Optional<WorkSpace> optionalWorkspace = workSpaceRepository.findById(spaceDTO.getWorkSpaceId());
		if(optionalWorkspace.isPresent()) {
			space.setWorkSpace(optionalWorkspace.get());
		}
		
		Optional<Icons> optionalIcons = iconRepository.findById(spaceDTO.getIconId());
		if(optionalIcons.isPresent()) {
			space.setIcons(optionalIcons.get());
		}
		
		Optional<Attachment> optionalAvatar = attachmentRepository.findById(spaceDTO.getAvatartId());
		if(optionalAvatar.isPresent()) {
			space.setAttachment(optionalAvatar.get());
		}
		
		Optional<User> optionalOwner = userRepository.findById(spaceDTO.getOwnerId());
		if(optionalOwner.isPresent()) {
			space.setOwner(optionalOwner.get());
		}
		
		space.setAccessType(spaceDTO.getAccessType());
		
		spaceRepository.save(space);
		return new ApiResponse("Updated" , true);
	}

	@Override
	public List<Space> getSpaces() {
		return spaceRepository.findAll();
	}


}
