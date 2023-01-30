package pdp.uz.appclickup.service;

import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.MemberDto;
import pdp.uz.appclickup.payload.WorkSpaceDTO;
import pdp.uz.appclickup.payload.WorkSpaceRoleDTO;

import java.util.List;


public interface WorkSpaceService {

    ApiResponse addWorkSpace(WorkSpaceDTO workSpaceDTO, User user);


    ApiResponse editWorkSpace(Integer id, WorkSpaceDTO workSpaceDTO, User user);

    ApiResponse editOwnerWorkSpace(Integer id, Integer ownerId);

    ApiResponse deleteWorkSpace(Integer id);

    ApiResponse addOrEditOrRemoveWorkSpace(Integer id, MemberDto memberDto);

    ApiResponse joinWorkSpace(Integer id, User user);

    List<MemberDto> getWorkSpaceMembersAndGuest(Integer id);

    List<WorkSpaceDTO> getWorkSpace(User user);

    ApiResponse addOrRemovePermissionToRole(WorkSpaceRoleDTO workSpaceRoleDTO);

    ApiResponse addRole(Integer workspaceId, WorkSpaceRoleDTO workSpaceRoleDTO, User user);
}
