package com.il.il_service.controller;

import com.il.il_service.exception.IlAlreadyExistException;
import com.il.il_service.exception.IlNotFoundException;
import com.il.il_service.model.Il;
import com.il.il_service.service.IlService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class IlController {

    private final IlService ilService;



    @GetMapping
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){
        return new ResponseEntity<>(ilService.getIller(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable String id){
        //Il result= getIlById(id);

        return new ResponseEntity<>(getIlById(id),OK);
    }

    private Il getIlById(String id) {

        return ilService.getIlById(id);


    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){
        /*newIl.setCreateDate(new Date());
        province.add(newIl);*/
        return new ResponseEntity<>(ilService.createIl(newIl), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getIl(@PathVariable String id,@RequestBody Il newIl){
        Il oldIl=getIlById(id);
        oldIl.setName(newIl.getName());
        oldIl.setCreateDate(newIl.getCreateDate());
        oldIl.setCreateDate(new Date());

        ilService.updateIl(id,newIl);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id){
        ilService.deleteIl(id);

        return new ResponseEntity<>(OK);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),NOT_FOUND);
    }
    @ExceptionHandler(IlAlreadyExistException.class)
    public ResponseEntity<String> handleIlAlreadyExistException(IlAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(),CONFLICT);
    }
}
