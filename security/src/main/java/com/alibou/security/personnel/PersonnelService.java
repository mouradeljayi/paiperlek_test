package com.alibou.security.personnel;

import java.util.List;

public interface PersonnelService {

     Personnel savePersonnel(Personnel personnel);
     List<Personnel> findAllPersonnel();
     void deletePersonnel(Long id);
}
