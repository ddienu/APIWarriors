package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Request.*;
import com.diegonunez.warriors.dto.Response.WarriorResponseDTO;
import com.diegonunez.warriors.entity.BreedWarrior;
import com.diegonunez.warriors.entity.TypePower;
import com.diegonunez.warriors.entity.TypeWarrior;
import com.diegonunez.warriors.entity.Warrior;
import com.diegonunez.warriors.exception.Unchecked.EmptyListException;
import com.diegonunez.warriors.exception.Unchecked.NoChangesMadeException;
import com.diegonunez.warriors.mapper.WarriorMapper;
import com.diegonunez.warriors.repository.IBreedWarriorRepository;
import com.diegonunez.warriors.repository.ITypePowerRepository;
import com.diegonunez.warriors.repository.ITypeWarriorRepository;
import com.diegonunez.warriors.repository.IWarriorRepository;
import com.diegonunez.warriors.service.IWarriorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WarriorServiceImpl implements IWarriorService {
    private final IWarriorRepository warriorRepository;
    private final IBreedWarriorRepository breedWarriorRepository;
    private final ITypePowerRepository typePowerRepository;
    private final ITypeWarriorRepository typeWarriorRepository;

    public WarriorServiceImpl( IWarriorRepository warriorRepository, IBreedWarriorRepository breedWarriorRepository
                               ,ITypePowerRepository typePowerRepository, ITypeWarriorRepository typeWarriorRepository){
        this.warriorRepository = warriorRepository;
        this.breedWarriorRepository = breedWarriorRepository;
        this.typePowerRepository = typePowerRepository;
        this.typeWarriorRepository = typeWarriorRepository;
    }
    @Override
    public List<WarriorResponseDTO> getAllWarriors() {

        List<Warrior> warriorListDB = warriorRepository.findAll();
        List<WarriorResponseDTO> warriorResponseList = warriorListDB.stream().map(
                WarriorMapper::toDTO
        ).toList();

        if( warriorResponseList.isEmpty()){
            throw new EmptyListException("Warriors not found");
        }

        return warriorResponseList;
    }

    @Override
    public WarriorResponseDTO getWarriorById(Integer warriorId) {
        Warrior warriorFounded = warriorRepository.findById(warriorId).orElseThrow(
                () -> new EntityNotFoundException("Warrior with ID: "+warriorId+" not found")
        );

        return WarriorMapper.toDTO(warriorFounded);
    }

    @Transactional
    @Override
    public WarriorResponseDTO createWarrior(WarriorRequestDTO warrior) throws EntityNotFoundException  {
        BreedWarrior breedWarrior = breedWarriorRepository.findById(warrior.getBreedWarrior()).orElseThrow(
                () -> new EntityNotFoundException("Breed of warrior not found")
        );

        TypeWarrior typeWarrior = typeWarriorRepository.findById(warrior.getTypeOfWarrior()).orElseThrow(
                () -> new EntityNotFoundException("Type warrior not found")
        );

        List<TypePower> powers = typePowerRepository.findAllById(warrior.getPowers());

        Warrior newWarrior = new Warrior();
        newWarrior.setWarriorName(warrior.getWarriorName());
        newWarrior.setWarriorEnergy(warrior.getWarriorEnergy());
        newWarrior.setWarriorLife(warrior.getWarriorLife());
        newWarrior.setBreedWarrior(breedWarrior);
        newWarrior.setTypeOfWarrior(typeWarrior);
        newWarrior.setTypeOfPower(powers);

        warriorRepository.save(newWarrior);

        return WarriorMapper.toDTO(newWarrior);
    }

    @Override
        public WarriorResponseDTO updateWarriorBasics(Integer warriorId, WarriorBasicsUpdateDTO warriorUpdated) {
            Warrior warriorFounded = warriorRepository.findById(warriorId).orElseThrow(
                    () -> new EntityNotFoundException("Warrior with ID: "+warriorId+" not founded")
            );

            if( warriorFounded.getWarriorName().toLowerCase().equalsIgnoreCase(warriorUpdated.getWarriorName())
            && warriorFounded.getWarriorLife().equals(warriorUpdated.getWarriorLife())
            && warriorFounded.getWarriorEnergy().equals(warriorUpdated.getWarriorEnergy())){
                throw new NoChangesMadeException("Warrior's basics are the same as the current ones");
            }

            warriorFounded.setWarriorName(warriorUpdated.getWarriorName());
            warriorFounded.setWarriorLife(warriorUpdated.getWarriorLife());
            warriorFounded.setWarriorEnergy(warriorUpdated.getWarriorEnergy());

            warriorRepository.save(warriorFounded);

            return WarriorMapper.toDTO(warriorFounded);
        }

    @Override
    public WarriorResponseDTO updateWarriorPowers(Integer warriorId, WarriorPowerUpdateDTO warriorPowerUpdated){
        Warrior warriorFounded = warriorRepository.findById(warriorId).orElseThrow(
                () -> new EntityNotFoundException("Warrior not founded")
        );

        List<TypePower> powers = typePowerRepository.findAllById(warriorPowerUpdated.getPowerIds());

        if( powers.size() != warriorPowerUpdated.getPowerIds().size()){
            throw new EntityNotFoundException("Some powers were not founded");
        }

        warriorFounded.setTypeOfPower(powers);
        warriorRepository.save(warriorFounded);

        return WarriorMapper.toDTO(warriorFounded);
    }

    @Override
    public WarriorResponseDTO updateWarriorBreed(Integer warriorId, WarriorBreedUpdateDTO warriorBreedUpdated) {
        Warrior warriorFounded = warriorRepository.findById(warriorId).orElseThrow(
                () -> new EntityNotFoundException("Warrior not founded")
        );

        BreedWarrior breedFounded = breedWarriorRepository.findById(warriorBreedUpdated.getBreedId()).orElseThrow(
                () -> new EntityNotFoundException("Breed not founded")
        );

        if( warriorFounded.getBreedWarrior().getBreedId().equals(breedFounded.getBreedId())){
            throw new NoChangesMadeException("Warrior's breed is the same as the current one");
        }

        warriorFounded.setBreedWarrior(breedFounded);
        warriorRepository.save(warriorFounded);

        return WarriorMapper.toDTO(warriorFounded);
    }

    @Override
    public WarriorResponseDTO updateWarriorType(Integer warriorId, WarriorTypeUpdateDTO warriorTypeUpdated) {
        Warrior warriorFounded = warriorRepository.findById(warriorId).orElseThrow(
                () -> new EntityNotFoundException("Warrior with ID: "+warriorId+" not found")
        );

        TypeWarrior typeWarriorFounded = typeWarriorRepository.findById(warriorTypeUpdated.getTypeWarriorId()).orElseThrow(
                () -> new EntityNotFoundException("Warrior type with ID: "+warriorTypeUpdated.getTypeWarriorId()+" not founded")
        );

        if( warriorFounded.getTypeOfWarrior().getTypeWarriorId().equals(warriorTypeUpdated.getTypeWarriorId()) ){
            throw new NoChangesMadeException("Warrior's type is the same as the current one");
        }

        warriorFounded.setTypeOfWarrior(typeWarriorFounded);
        warriorRepository.save(warriorFounded);

        return WarriorMapper.toDTO(warriorFounded);
    }

    @Override
    public boolean deleteWarrior(Integer warriorId) {
        Warrior warriorFounded = warriorRepository.findById(warriorId).orElseThrow(
                () -> new EntityNotFoundException("Warrior with ID: "+warriorId+" not founded")
        );

        warriorRepository.delete(warriorFounded);
        return true;
    }

}
