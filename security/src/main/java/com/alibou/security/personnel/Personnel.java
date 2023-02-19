package com.alibou.security.personnel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Personnel {

    @Id
    @GeneratedValue
    public Integer id;
    public String nom;
    public String prenom;
    public double salaire;
    public String service;

}
