package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Request.TypeWarriorRequestDTO;
import com.diegonunez.warriors.dto.Response.TypeWarriorResponseDTO;
import com.diegonunez.warriors.entity.TypeWarrior;
import com.diegonunez.warriors.exception.Unchecked.EmptyListException;
import com.diegonunez.warriors.exception.Unchecked.EntityInUseException;
import com.diegonunez.warriors.repository.ITypeWarriorRepository;
import com.diegonunez.warriors.repository.IWarriorRepository;
import com.diegonunez.warriors.service.ITypeWarriorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeWarriorServiceImpl implements ITypeWarriorService {

    private final ITypeWarriorRepository typeWarriorRepository;
    private final IWarriorRepository warriorRepository;

    public TypeWarriorServiceImpl( ITypeWarriorRepository typeWarriorRepository,IWarriorRepository warriorRepository ){
        this.typeWarriorRepository = typeWarriorRepository;
        this.warriorRepository = warriorRepository;
    }

    @Override
    public List<TypeWarriorResponseDTO> getAllTypeWarrior() {
        List<TypeWarrior> typeWarriors = typeWarriorRepository.findAll();

        if( typeWarriors.isEmpty() ){
            throw new EmptyListException("No type warriors founded");
        }

        return typeWarriors.stream()
                .map(
                        typePower -> new TypeWarriorResponseDTO(
                                typePower.getTypeWarriorId(),
                                typePower.getTypeWarriorName(),
                                typePower.getTypeWarriorDescription()
                        )
                ).toList();
    }
    @Override
    public TypeWarriorResponseDTO createTypeWarrior(TypeWarriorRequestDTO typeWarrior) {
        TypeWarrior newTypeWarrior = new TypeWarrior();
        newTypeWarrior.setTypeWarriorName(typeWarrior.getTypeWarriorName());
        newTypeWarrior.setTypeWarriorDescription(typeWarrior.getTypeWarriorDescription());

        TypeWarrior entity = typeWarriorRepository.save(newTypeWarrior);

        return new TypeWarriorResponseDTO(
                entity.getTypeWarriorId(),
                entity.getTypeWarriorName(),
                entity.getTypeWarriorDescription()
        );
    }
    @Transactional
    @Override
    public TypeWarriorResponseDTO updateTypeWarrior(Integer typeWarriorId, TypeWarriorRequestDTO typeWarriorUpdated) {
        TypeWarrior typeWarriorFounded = typeWarriorRepository.findById(typeWarriorId).orElseThrow(
                () -> new EntityNotFoundException("Type warrior not founded")
        );

        typeWarriorFounded.setTypeWarriorName(typeWarriorUpdated.getTypeWarriorName());
        typeWarriorFounded.setTypeWarriorDescription(typeWarriorUpdated.getTypeWarriorDescription());
        typeWarriorRepository.save(typeWarriorFounded);

        return new TypeWarriorResponseDTO(
                typeWarriorFounded.getTypeWarriorId(),
                typeWarriorFounded.getTypeWarriorName(),
                typeWarriorFounded.getTypeWarriorDescription()
        );
    }

    @Override
    public boolean deleteTypeWarrior(Integer typeWarriorId) {
        TypeWarrior typePowerFounded = typeWarriorRepository.findById(typeWarriorId).orElseThrow(
                () -> new EntityNotFoundException("Type warrior not founded")
        );

        Integer warriorsUsingTypeWarrior = warriorRepository.findWarriorsUsingType(typeWarriorId);

        if( warriorsUsingTypeWarrior != 0){
            throw new EntityInUseException("The type warrior cannot be deleted because it is being used by one or more warriors.");
        }

        typeWarriorRepository.delete(typePowerFounded);
        return true;
    }
}
