package com.example.companyInventortyMgmt.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.owasp.encoder.Encode;

@Service
public class DepartmentService {

    // Construct inject for the DepartmentRepository
    private final DepartmentRepository departmentRepository;

    @Autowired
    DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private String sanitize(String input){
        String sanitized = Encode.forHtml(input);
        sanitized = sanitized.replaceAll("[^\\p{L}\\p{N}\\s]", ""); 
        return sanitized;
    }
    @Transactional
    void createDepartment(DepartmentsEntity departmentsEntity){
        // Seralization 
        departmentsEntity.setName(this.sanitize(departmentsEntity.getName()));       
        departmentsEntity.setCode(this.sanitize(departmentsEntity.getCode()));       

        // Call Department Repository to create the record
        this.departmentRepository.create(departmentsEntity);
    }

    DepartmentsEntity retrieve(int departmentId){
        return this.departmentRepository.findById(departmentId);
    }

    List<DepartmentsEntity> retrieveAll(){
        return this.departmentRepository.findAll();
    }

    public Boolean deleteDepartment(String departmentCode) {
        return departmentRepository.deleteByCode(departmentCode);
    }

    @Transactional
    public Boolean updateDepartment(int id, String name, String departmentCode) {
        String sanitizedName = (name != null && !name.isEmpty()) ? this.sanitize(name) : null;
        String sanitizedCode = (departmentCode != null && !departmentCode.isEmpty()) ? this.sanitize(departmentCode) : null;

        return departmentRepository.updateById(id, sanitizedName, sanitizedCode);
    }
}
