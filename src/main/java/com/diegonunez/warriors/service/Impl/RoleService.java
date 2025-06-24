package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Request.RoleRequestDTO;
import com.diegonunez.warriors.dto.Response.RoleResponseDTO;
import com.diegonunez.warriors.entity.Role;
import com.diegonunez.warriors.repository.IRoleRepository;
import com.diegonunez.warriors.service.IRoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponseDTO addRole(RoleRequestDTO newRole) {
        Role roleToAdd = new Role(
                newRole.getName(),
                newRole.getDescription()
        );

        Role roleSaved = roleRepository.save(roleToAdd);

        return new RoleResponseDTO(
                roleSaved.getId(),
                roleSaved.getName(),
                roleSaved.getDescription()
        );
    }

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(
                role -> new RoleResponseDTO(
                        role.getId(),
                        role.getName(),
                        role.getDescription()
                )
        ).toList();
    }

    @Override
    public RoleResponseDTO getRoleById(Integer roleId) {
        Role roleFounded = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role with ID: "+roleId+" not found")
        );

        return new RoleResponseDTO(
                roleFounded.getId(),
                roleFounded.getName(),
                roleFounded.getDescription()
        );
    }

    @Override
    public RoleResponseDTO updateRole(Integer roleId, RoleRequestDTO roleUpdated) {
        Role roleFounded = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role with ID: "+roleId+" not founded")
        );

        if(!roleFounded.getName().equals(roleUpdated.getName())){
            roleFounded.setName(roleUpdated.getName());
        }

        if(!roleFounded.getDescription().equals(roleUpdated.getDescription())){
            roleFounded.setDescription(roleFounded.getDescription());
        }

        roleRepository.save(roleFounded);

        return new RoleResponseDTO(
                roleFounded.getId(),
                roleFounded.getName(),
                roleFounded.getDescription()
        );
    }

    @Override
    public boolean deleteRole(Integer roleId) {
        Role roleFounded = roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role with ID: "+roleId+" not founded")
        );

        roleRepository.delete(roleFounded);

        return true;
    }
}
