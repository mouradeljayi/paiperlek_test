package com.alibou.security.personnel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonnelServiceImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;

    public PersonnelServiceImpl(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    public Personnel savePersonnel(Personnel personnel) {
        return this.personnelRepository.save(personnel);
    }

    public List<Personnel> findAllPersonnel() {
        return this.personnelRepository.findAll();
    }

    public void deletePersonnel(Long id) {
        if(id == null) {
            log.error("ID de personnel est nul");
            return;
        }
        this.personnelRepository.deleteById(id);
    }
}
