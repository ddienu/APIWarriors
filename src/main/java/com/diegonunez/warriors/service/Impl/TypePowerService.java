package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Request.TypePowerRequestDTO;
import com.diegonunez.warriors.dto.Response.TypePowerResponseDTO;
import com.diegonunez.warriors.entity.TypePower;
import com.diegonunez.warriors.exception.Unchecked.EmptyListException;
import com.diegonunez.warriors.exception.Unchecked.EntityInUseException;
import com.diegonunez.warriors.repository.ITypePowerRepository;
import com.diegonunez.warriors.repository.IWarriorRepository;
import com.diegonunez.warriors.service.ITypePowerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypePowerService implements ITypePowerService {

    private final ITypePowerRepository typePowerRepository;
    private final IWarriorRepository warriorRepository;

    public TypePowerService(ITypePowerRepository typePowerRepository, IWarriorRepository warriorRepository){
        this.typePowerRepository = typePowerRepository;
        this.warriorRepository = warriorRepository;
    }

    @Override
    public List<TypePowerResponseDTO> getAllPowers() {
        List<TypePowerResponseDTO> powers = typePowerRepository.findAll()
                .stream()
                .map(power -> new TypePowerResponseDTO(
                        power.getPowerId(),
                        power.getPowerName(),
                        power.getPowerDamage(),
                        power.getPowerEnergyConsumed(),
                        power.getPowerDescription()
                ))
                .toList();

        if( powers.isEmpty() ){
            throw new EmptyListException("No powers found");
        }
        return powers;
    }

    @Override
    public TypePowerResponseDTO createPower(TypePowerRequestDTO newPowerRequest) {
        TypePower newPower = new TypePower();
        newPower.setPowerName(newPowerRequest.getPowerName());
        newPower.setPowerDamage(newPowerRequest.getPowerDamage());
        newPower.setPowerEnergyConsumed(newPowerRequest.getPowerEnergyConsumed());
        newPower.setPowerDescription(newPowerRequest.getPowerDescription());

        TypePower entity = typePowerRepository.save(newPower);

        return new TypePowerResponseDTO(
                entity.getPowerId(),
                entity.getPowerName(),
                entity.getPowerDamage(),
                entity.getPowerEnergyConsumed(),
                entity.getPowerDescription()
        );
    }

    @Transactional
    @Override
    public TypePowerResponseDTO updatePower(Integer powerId, TypePowerRequestDTO updatedPower) {
        TypePower powerFounded = typePowerRepository.findById(powerId).orElseThrow(
                () -> new EntityNotFoundException("Power not found")
        );

        powerFounded.setPowerName(updatedPower.getPowerName());
        powerFounded.setPowerDamage(updatedPower.getPowerDamage());
        powerFounded.setPowerEnergyConsumed(updatedPower.getPowerEnergyConsumed());
        powerFounded.setPowerDescription(updatedPower.getPowerDescription());

        typePowerRepository.save(powerFounded);

        return new TypePowerResponseDTO(
                powerFounded.getPowerId(),
                powerFounded.getPowerName(),
                powerFounded.getPowerDamage(),
                powerFounded.getPowerEnergyConsumed(),
                powerFounded.getPowerDescription()
        );
    }

    @Override
    public boolean deletePower(Integer powerId) {
        TypePower powerFounded = typePowerRepository.findById(powerId).orElseThrow(
                () -> new EntityNotFoundException("Power not found")
        );

        Integer warriorsUsingPowers = warriorRepository.findWarriorsUsingPowers(powerId);

        if( warriorsUsingPowers != 0){
            throw new EntityInUseException("The power cannot be deleted because it is being used by one or more warriors.");
        }


        typePowerRepository.delete(powerFounded);
        return true;
    }
}
