package com.rf.labrex.controller;

import com.rf.labrex.dto.SavePatientRequest;
import com.rf.labrex.errorManagement.ApiResponse;
import com.rf.labrex.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // hasta kayıt
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody SavePatientRequest request, HttpServletRequest url){
        return ResponseEntity.ok(patientService.save(request,url));
    }
    // hasta silme -> bu işlemi sadece hasta ve admin yapabilir
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id,HttpServletRequest url){
        return ResponseEntity.ok(patientService.delete(id,url));
    }
}
