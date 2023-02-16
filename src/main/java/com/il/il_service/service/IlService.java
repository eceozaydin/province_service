package com.il.il_service.service;

import com.il.il_service.exception.IlAlreadyExistException;
import com.il.il_service.exception.IlNotFoundException;
import com.il.il_service.model.Il;
import com.il.il_service.repository.IlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service  //injectable hale getirir
@AllArgsConstructor

public class IlService {
    private final IlRepository ilRepository;
    public List<Il> getIller(String name) {
        if(name== null){
            return ilRepository.findAll();
        }
        else{
            return ilRepository.findAllByName(name);
        }
    }

    public Il createIl(Il newIl) {
        newIl.setCreateDate(new Date());
        Optional<Il> ilByName=ilRepository.findByName(newIl.getName());
        if(ilByName.isPresent()){
            throw new IlAlreadyExistException("Il already exist with name:"+ newIl.getName());
        }
        return ilRepository.save(newIl);
    }

    public void deleteIl(String id) {
        ilRepository.deleteById(id);
    }
    public void updateIl(String id,Il newIl){
        Il oldIl=getIlById(id);
        oldIl.setName(newIl.getName());
        //newIl.setCreateDate(new Date());
        ilRepository.save(oldIl);
    }
    public Il getIlById(String id) {
        return ilRepository.findById(id)
        .orElseThrow(() -> new IlNotFoundException("Il not found with id: " + id));
    }
}
