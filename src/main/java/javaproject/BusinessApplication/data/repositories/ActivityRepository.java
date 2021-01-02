package javaproject.BusinessApplication.data.repositories;

import javaproject.BusinessApplication.data.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByDateAfter(Date after);
}