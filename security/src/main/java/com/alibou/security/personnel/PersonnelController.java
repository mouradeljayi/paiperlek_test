package com.alibou.security.personnel;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personnel")
@CrossOrigin
public class PersonnelController {

    PersonnelService personnelService;

    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @GetMapping
    public ResponseEntity<List<Personnel>> getAllPersonnel() {
        return ResponseEntity.ok(personnelService.findAllPersonnel());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Personnel> createPersonnel(@RequestBody Personnel personnel) {
        return ResponseEntity.ok(personnelService.savePersonnel(personnel));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable Integer id, @RequestBody Personnel personnel) {
        return ResponseEntity.ok(personnelService.updatPersonnel(personnel, id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Personnel> findPersonnel(@PathVariable Integer id) {
        return ResponseEntity.ok(personnelService.findPersonnel(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deletePersonnel(@PathVariable Integer id) {
        personnelService.deletePersonnel(id);
        return ResponseEntity.noContent().build();
    }
}
