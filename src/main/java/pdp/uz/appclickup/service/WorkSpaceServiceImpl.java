package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.*;
import pdp.uz.appclickup.entity.enums.AddType;
import pdp.uz.appclickup.entity.enums.WorkSpaceRoleName;
import pdp.uz.appclickup.entity.enums.WorkspacePermissionName;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.MemberDto;
import pdp.uz.appclickup.payload.WorkSpaceDTO;
import pdp.uz.appclickup.payload.WorkSpaceRoleDTO;
import pdp.uz.appclickup.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {
    @Autowired
    WorkSpaceRepository workSpaceRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    WorkSpaceUserRepository workSpaceUserRepository;

    @Autowired
    WorkSpaceRoleRepository workSpaceRoleRepository;

    @Autowired
    WorkSpacePermissionRepository workSpacePermissionRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public ApiResponse addWorkSpace(WorkSpaceDTO workSpaceDTO, User user) {
        //WORKSPACE OCHILDI
        if (workSpaceRepository.existsByOwnerIdAndName(user.getId(),workSpaceDTO.getName()))
            return new ApiResponse("Sizda Bunday nomli ishxona mavjud",false);
        WorkSpace workSpace = new WorkSpace(
                workSpaceDTO.getName(),
                workSpaceDTO.getColor(),
                user,
                workSpaceDTO.getAvatarId()==null?null: attachmentRepository.getById(workSpaceDTO.getAvatarId())
        );

        workSpaceRepository.save(workSpace);

        //WORKSPACEROLE OCHILDI
        WorkSpaceRole ownerSpaceRole = workSpaceRoleRepository.save(new WorkSpaceRole(
                workSpace,
                WorkSpaceRoleName.ROLE_OWNER.name(),
                null

        ));
        WorkSpaceRole admin = workSpaceRoleRepository.save(new WorkSpaceRole(workSpace, WorkSpaceRoleName.ROLE_ADMIN.name(), null));
        WorkSpaceRole member = workSpaceRoleRepository.save(new WorkSpaceRole(workSpace, WorkSpaceRoleName.ROLE_MEMBER.name(), null));
        WorkSpaceRole guest = workSpaceRoleRepository.save(new WorkSpaceRole(workSpace, WorkSpaceRoleName.ROLE_GUEST.name(), null));

        //OWNERGA XUQUQLAR BERAMIZ
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkSpacePermission> workSpacePermissionList = new ArrayList<>();
        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkSpacePermission workSpacePermission = new WorkSpacePermission(
                    ownerSpaceRole,
                    workspacePermissionName
            );
            workSpacePermissionList.add(workSpacePermission);

            if (workspacePermissionName.getWorkSpaceRoleNameList().contains(WorkSpaceRoleName.ROLE_ADMIN)){
             workSpacePermissionList.add( new WorkSpacePermission(
                     admin,
                     workspacePermissionName
             ));
            }

            if (workspacePermissionName.getWorkSpaceRoleNameList().contains(WorkSpaceRoleName.ROLE_MEMBER)){
                workSpacePermissionList.add( new WorkSpacePermission(
                        member,
                        workspacePermissionName
                ));
            }

            if (workspacePermissionName.getWorkSpaceRoleNameList().contains(WorkSpaceRoleName.ROLE_GUEST)){
                workSpacePermissionList.add( new WorkSpacePermission(
                        guest,
                        workspacePermissionName
                ));
            }

        }
        workSpacePermissionRepository.saveAll(workSpacePermissionList);


        //WORKSPACEUSER OCHILDI
        workSpaceUserRepository.save(new WorkSpaceUser(
                workSpace,
                user,
                ownerSpaceRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));
        return new ApiResponse("Ishxona saqlandi",true);

    }

    @Override
    public ApiResponse editWorkSpace(Integer id, WorkSpaceDTO workSpaceDTO, User user) {

        boolean exists = workSpaceRepository.existsByNameAndIdNot(workSpaceDTO.getName(), id);
        if (exists){
            return new ApiResponse("Bunday workSpace mavjud",false);
        }
        //WORKSPACE TAHRIRLANDI
        WorkSpace workSpace = workSpaceRepository.getById(id);
        workSpace.setName(workSpaceDTO.getName());
        workSpace.setColor(workSpaceDTO.getColor());
        workSpace.setAvatar(workSpaceDTO.getAvatarId()==null?null:attachmentRepository.getById(workSpaceDTO.getAvatarId()));
        workSpace.setOwner(user);
        workSpaceRepository.save(workSpace);
        return new ApiResponse("Worksapace tahrirlandi",true);
    }

    @Override
    public ApiResponse editOwnerWorkSpace(Integer id, Integer ownerId) {
        Optional<WorkSpace> optionalWorkSpace = workSpaceRepository.findByIdAndOwnerId(id, ownerId);
        WorkSpace workSpace = optionalWorkSpace.get();
        workSpace.setOwner(userRepository.getById(ownerId));
        workSpaceRepository.save(workSpace);
        return new ApiResponse("Owner tahrirlandi",true);
    }

    @Override
    public ApiResponse deleteWorkSpace(Integer id) {
        try {
            workSpaceRepository.deleteById(id);
            return new ApiResponse("Ishxona O'chirildi",true);
        }catch (Exception e){
            return new ApiResponse("Xatolik",false);
        }

    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkSpace(Integer id, MemberDto memberDto) {
        if(memberDto.getAddType().equals(AddType.ADD)){
            WorkSpaceUser workSpaceUser = new WorkSpaceUser(
                    workSpaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDto.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workSpaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workSpaceUserRepository.save(workSpaceUser);
        }else if (memberDto.getAddType().equals(AddType.EDIT)){
            WorkSpaceUser workSpaceUser = workSpaceUserRepository.findByWorkSpaceIdAndUserId(id, memberDto.getId()).orElseGet(WorkSpaceUser::new);
            workSpaceUser.setWorkSpaceRole(workSpaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
        }else if (memberDto.getAddType().equals(AddType.REMOVE)){
            workSpaceUserRepository.deleteByWorkSpaceIdAndUserId(id, memberDto.getId());
        }
        return new ApiResponse("Muvaffaqiyatli",true);
    }


    @Override
    public ApiResponse joinWorkSpace(Integer id, User user) {
        Optional<WorkSpaceUser> optionalWorkSpaceUser = workSpaceUserRepository.findByWorkSpaceIdAndUserId(id, user.getId());
        if (optionalWorkSpaceUser.isPresent()){
            WorkSpaceUser workSpaceUser = optionalWorkSpaceUser.get();
            workSpaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workSpaceUserRepository.save(workSpaceUser);
            return new ApiResponse("Success",true);
        }
        return new ApiResponse("Error",false);

    }

    @Override
    public List<MemberDto> getWorkSpaceMembersAndGuest(Integer id) {
       List<WorkSpaceUser> optionalWorkSpaceUser = workSpaceUserRepository.findAllByWorkSpaceId(id);
//       List<MemberDto> members = new ArrayList<>();
//        for (WorkSpaceUser workSpaceUser : optionalWorkSpaceUser) {
//            MemberDto memberDto = mapWorkspaceUserToMemberDto(workSpaceUser);
//            members.add(memberDto);
//        }
//        return members;

        return optionalWorkSpaceUser.stream().map(this::mapWorkspaceUserToMemberDto).collect(Collectors.toList());
    }

    public MemberDto mapWorkspaceUserToMemberDto(WorkSpaceUser workSpaceUser){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(workSpaceUser.getUser().getId());
        memberDto.setFullName(workSpaceUser.getUser().getFullName());
        memberDto.setEmail(workSpaceUser.getUser().getEmail());
        memberDto.setRoleName(workSpaceUser.getWorkSpaceRole().getName());
        memberDto.setLastActive(workSpaceUser.getUser().getLastActive());
        return memberDto;
    }

    @Override
    public ApiResponse addOrRemovePermissionToRole(WorkSpaceRoleDTO workSpaceRoleDTO) {
        WorkSpaceRole workSpaceRole = workSpaceRoleRepository.findById(workSpaceRoleDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("WorkspaceRole"));
        Optional<WorkSpacePermission> optionalWorkSpacePermission = workSpacePermissionRepository.findByWorkSpaceRoleIdAndWorkspacePermissionName(workSpaceRole.getId(), workSpaceRoleDTO.getPermissionName());
        if (workSpaceRoleDTO.getAddType().equals(AddType.ADD)) {
            if (optionalWorkSpacePermission.isPresent()){
                return new ApiResponse("Allaqachon qo'shilgan",false);
            }
            WorkSpacePermission workSpacePermission =  new WorkSpacePermission(workSpaceRole,workSpaceRoleDTO.getPermissionName());
            workSpacePermissionRepository.save(workSpacePermission);
            return new ApiResponse("Muvaffiqiyatli qo'shildi",true);

        }else if (workSpaceRoleDTO.getAddType().equals(AddType.REMOVE)){
            if (optionalWorkSpacePermission.isPresent()){
                workSpacePermissionRepository.delete(optionalWorkSpacePermission.get());
                return new ApiResponse("Muvaffiqiyatli o'chirildi",true);
            }
            return new ApiResponse("Bunday object mavjud emas",false);
        }
        return new ApiResponse("Bunday buyruq yoq",false);
    }

    @Override
    public ApiResponse addRole(Integer workspaceId, WorkSpaceRoleDTO workSpaceRoleDTO, User user) {
        if (workSpaceRoleRepository.existsByWorkSpaceIdAndName(workspaceId, workSpaceRoleDTO.getName())){
            return new ApiResponse("Error",false);
        }
        WorkSpaceRole workSpaceRole = workSpaceRoleRepository.save(new WorkSpaceRole(getWorkspaceAfterThrow(workspaceId), workSpaceRoleDTO.getName(), workSpaceRoleDTO.getExtendsRole()));
        List<WorkSpacePermission> workSpacePermissions = workSpacePermissionRepository.findAllByWorkSpaceRole_NameAndWorkSpaceRole_WorkSpaceId(workSpaceRoleDTO.getExtendsRole().name(), workspaceId);
        List<WorkSpacePermission> newWorkSpacePermissions = new ArrayList<>();
        for (WorkSpacePermission workSpacePermission : workSpacePermissions) {
            WorkSpacePermission newWorkSpacePermission = new WorkSpacePermission(workSpaceRole,workSpacePermission.getWorkspacePermissionName());
            newWorkSpacePermissions.add(newWorkSpacePermission);
        }
        workSpacePermissionRepository.saveAll(newWorkSpacePermissions);
        return new ApiResponse("Accepted",true);
    }

    private WorkSpace getWorkspaceAfterThrow(Integer workspaceId) {
        return workSpaceRepository.getById(workspaceId);
    }

    @Override
    public List<WorkSpaceDTO> getWorkSpace(User user) {
        List<WorkSpaceUser> workSpaceUserList = workSpaceUserRepository.findAllByUserId(user.getId());
        return workSpaceUserList.stream().map(workSpaceUser -> mapWorkSpaceUserToWorkSpaceDto(workSpaceUser.getWorkSpace())).collect(Collectors.toList());
    }
    public WorkSpaceDTO mapWorkSpaceUserToWorkSpaceDto(WorkSpace workSpace){
        WorkSpaceDTO workSpaceDTO = new WorkSpaceDTO();
        workSpaceDTO.setId(workSpace.getId());
        workSpaceDTO.setInitialLetter(workSpace.getInitialLetter());
        workSpaceDTO.setName(workSpace.getName());
        workSpaceDTO.setAvatarId(workSpace.getAvatar()==null?null:workSpace.getAvatar().getId());
        workSpaceDTO.setColor(workSpace.getColor());
        return workSpaceDTO;


    }
}
