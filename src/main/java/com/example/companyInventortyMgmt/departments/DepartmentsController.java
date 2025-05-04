package com.example.companyInventortyMgmt.departments;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.companyInventortyMgmt.utils.response.Message;
import com.example.companyInventortyMgmt.utils.response.RestResponseEntity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentsController {

    // Construct injection for DepartmentService
    private final DepartmentService departmentService;

    @Autowired
    DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("hasAuthority('WRITE_DEPARTMENT')")
    // Create department
    @PostMapping("")
    public ResponseEntity<DepartmentsEntity> createDepartment(@RequestBody DepartmentsEntity department) {
        // TODO: Do basic operation
        this.departmentService.createDepartment(department);
        return new ResponseEntity<>(department,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getDepartments() {
        List<DepartmentsEntity> departments = this.departmentService.retrieveAll();
        if (!departments.isEmpty()) {
            return RestResponseEntity.success(departments, HttpStatus.OK, Message.OPERATION_SUCCESS);
        } else {
            return RestResponseEntity.error(HttpStatus.OK, Message.OPERATION_FAILED);
        }
    }

    @PreAuthorize("hasAuthority('READ_DEPARTMENT')")
    @GetMapping("{departmentId}")
    public ResponseEntity<Map<String,Object>> getDepartment(@PathVariable int departmentId) {
        DepartmentsEntity department = this.departmentService.retrieve(departmentId);
        if (department != null) {
            return RestResponseEntity.success(department, HttpStatus.OK, Message.OPERATION_SUCCESS);
        } else {
            return RestResponseEntity.error(HttpStatus.NOT_FOUND, Message.OPERATION_FAILED);
        }
    }

    @PreAuthorize("hasAuthority('DELETE_DEPARTMENT')")
    @DeleteMapping("{departmentCode}")
    public ResponseEntity<Map<String, Object>> deleteDepartment(@PathVariable String departmentCode) {
        try {
            if (this.departmentService.deleteDepartment(departmentCode)) {
                return RestResponseEntity.success(HttpStatus.NO_CONTENT, Message.OPERATION_SUCCESS);
            } else {
                return RestResponseEntity.error(departmentCode + " Department is not exist", HttpStatus.NOT_FOUND, Message.OPERATION_FAILED);
            }
        } catch (Exception e) {
            return RestResponseEntity.error("Failed to delete department: " + departmentCode, HttpStatus.NOT_FOUND, Message.OPERATION_FAILED);
        }
    }

    @PreAuthorize("hasAuthority('EDIT_DEPARTMENT')")
    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> updateDepartment(@PathVariable int id, @RequestParam(required = false) String name, @RequestParam(required = false) String code) {
        Boolean updated = departmentService.updateDepartment(id, name, code);
        if (updated) {
            return RestResponseEntity.success(HttpStatus.OK,Message.OPERATION_SUCCESS);
        } else {
            return RestResponseEntity.error(HttpStatus.OK,Message.OPERATION_FAILED);
        }
    }
    
    
}
