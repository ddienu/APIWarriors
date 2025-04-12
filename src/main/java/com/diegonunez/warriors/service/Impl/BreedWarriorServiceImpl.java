package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Request.BreedWarriorRequestDTO;
import com.diegonunez.warriors.dto.Response.BreedWarriorResponseDTO;
import com.diegonunez.warriors.entity.BreedWarrior;
import com.diegonunez.warriors.entity.Warrior;
import com.diegonunez.warriors.exception.Unchecked.EmptyListException;
import com.diegonunez.warriors.exception.Unchecked.EntityInUseException;
import com.diegonunez.warriors.exception.Unchecked.NoChangesMadeException;
import com.diegonunez.warriors.repository.IBreedWarriorRepository;
import com.diegonunez.warriors.repository.IWarriorRepository;
import com.diegonunez.warriors.service.IBreedWarriorService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.TransientObjectException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedWarriorServiceImpl implements IBreedWarriorService {

    private final IBreedWarriorRepository breedWarriorRepository;
    private final IWarriorRepository warriorRepository;

    public BreedWarriorServiceImpl( IBreedWarriorRepository breedWarriorRepository, IWarriorRepository warriorRepository ){
        this.breedWarriorRepository = breedWarriorRepository;
        this.warriorRepository = warriorRepository;
    }

    @Override
    public List<BreedWarriorResponseDTO> getAllBreedWarrior() {
        List<BreedWarriorResponseDTO> breeds = breedWarriorRepository.findAll()
                .stream()
                .map(
                        breed -> new BreedWarriorResponseDTO(
                                breed.getBreedId(),
                                breed.getBreedName(),
                                breed.getBreedDescription(),
                                breed.getBreedResistance()
                        )
                ).toList();

        if( breeds.isEmpty() ){
            throw new EmptyListException("No breeds found");
        }
       return breeds;
    }


    @Override
    public BreedWarriorResponseDTO createBreedWarrior(BreedWarriorRequestDTO breedWarrior) {
        BreedWarrior newBreedWarrior = new BreedWarrior(
                breedWarrior.getBreedName(),
                breedWarrior.getBreedDescription(),
                breedWarrior.getBreedResistance()
        );
        BreedWarrior savedBreedWarrior = breedWarriorRepository.save(newBreedWarrior);

        return new BreedWarriorResponseDTO(
                savedBreedWarrior.getBreedId(),
                savedBreedWarrior.getBreedName(),
                savedBreedWarrior.getBreedDescription(),
                savedBreedWarrior.getBreedResistance()
        );
    }

    @Override
    public BreedWarriorResponseDTO updateBreedWarrior(Integer breedId, BreedWarriorRequestDTO breedWarriorUpdate) {
        BreedWarrior breedWarriorFounded = breedWarriorRepository.findById(breedId).orElseThrow(
                () -> new EntityNotFoundException("Breed not found")
        );

        if( breedWarriorFounded.getBreedName().equals(breedWarriorUpdate.getBreedName())
                && breedWarriorFounded.getBreedDescription().equalsIgnoreCase(breedWarriorUpdate.getBreedDescription())
                && breedWarriorFounded.getBreedResistance().equalsIgnoreCase(breedWarriorUpdate.getBreedResistance())){
            throw new NoChangesMadeException("Breed warrior is already saved");
        }

        breedWarriorFounded.setBreedName(breedWarriorUpdate.getBreedName());
        breedWarriorFounded.setBreedDescription(breedWarriorUpdate.getBreedDescription());
        breedWarriorFounded.setBreedResistance(breedWarriorUpdate.getBreedResistance());

        breedWarriorRepository.save(breedWarriorFounded);

        return new BreedWarriorResponseDTO(
                breedWarriorFounded.getBreedId(),
                breedWarriorFounded.getBreedName(),
                breedWarriorFounded.getBreedDescription(),
                breedWarriorFounded.getBreedResistance()
        );
    }

    @Override
    public boolean deleteBreedWarrior(Integer breedId) {
        BreedWarrior breedWarriorFounded = breedWarriorRepository.findById(breedId).orElseThrow(
                () -> new EntityNotFoundException("Breed not found")
        );

        List<Warrior> warriorUsingType = warriorRepository.findWarriorsUsingBreed(breedId);

        if(!warriorUsingType.isEmpty()){
            throw new EntityInUseException("The breed cannot be deleted because it is being used by one or more warriors.");
        }

        breedWarriorRepository.delete(breedWarriorFounded);

        return true;
    }
}
