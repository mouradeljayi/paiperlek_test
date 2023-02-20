package com.alibou.security.personnel;

import java.util.List;

public interface PersonnelService {

     Personnel savePersonnel(Personnel personnel);
     List<Personnel> findAllPersonnel();
     void deletePersonnel(Integer id);
     Personnel findPersonnel(Integer id);
     Personnel updatPersonnel(Personnel personnel, Integer id);
}
