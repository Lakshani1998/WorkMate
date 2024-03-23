package com.example.WorkMate.controller;

import com.example.WorkMate.dto.EmployeeDTO;
import com.example.WorkMate.dto.ResponceDTO;
import com.example.WorkMate.services.EmployeeService;
import com.example.WorkMate.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res = employeeService.updateEmployee(employeeDTO);
            if (res.equals("00")){
                responceDTO.setCode(VarList.RSP_SUCESS);
                responceDTO.setMessage("Sucess");
                responceDTO.setContent(employeeDTO);
                return new ResponseEntity(responceDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("01")){
                responceDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responceDTO.setMessage("Employee is not yet Registered");
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
    @GetMapping("/getAllEmployee")
    public ResponseEntity getAllEmployees(){
        try{
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
            responceDTO.setCode(VarList.RSP_SUCESS);
            responceDTO.setMessage("Sucess");
            responceDTO.setContent(employeeDTOList);
            return new ResponseEntity(responceDTO,HttpStatus.ACCEPTED);

        }catch(Exception e){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(e.getMessage());
            responceDTO.setContent(null);
            return new ResponseEntity(responceDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchEmployee/{empId}")
    public ResponseEntity searchEmployee(@PathVariable int empId){
        try{
            EmployeeDTO employeeDTO = employeeService.searchEmployee(empId);
            if (employeeDTO != null){
                responceDTO.setCode(VarList.RSP_SUCESS);
                responceDTO.setMessage("Sucess");
                responceDTO.setContent(employeeDTO);
                return new ResponseEntity(responceDTO,HttpStatus.ACCEPTED);
            }else{
                responceDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responceDTO.setMessage("No Employee Available for this Employee ID");
                responceDTO.setContent(null);
                return new ResponseEntity(responceDTO,HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            responceDTO.setCode(VarList.RSP_ERROR);
            responceDTO.setMessage(e.getMessage());
            responceDTO.setContent(e);
            return new ResponseEntity(responceDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



