package com.example.WorkMate.controller;

import com.example.WorkMate.dto.EmployeeDTO;
import com.example.WorkMate.dto.ResponceDTO;
import com.example.WorkMate.services.EmployeeService;
import com.example.WorkMate.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/employee")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponceDTO responceDTO;

    @PostMapping("/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res = employeeService.saveEmployee(employeeDTO);
            if (res.equals("00")){
                responceDTO.setCode(VarList.RSP_SUCESS);
                responceDTO.setMessage("Sucess");
                responceDTO.setContent(employeeDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("06")){
                responceDTO.setCode(VarList.RSP_DUPLICATED);
                responceDTO.setMessage("Employee Already Registered");
                responceDTO.setContent(employeeDTO);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);
            }else {
                responceDTO.setCode(VarList.RSP_FAIL);
                responceDTO.setMessage("Error");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(e.getMessage());
            responceDTO.setContent(null);
            return new ResponseEntity(responceDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
