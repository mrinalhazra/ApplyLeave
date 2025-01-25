package com.CodingNinjas.LeaveXpress.repository;

import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveModel, Long> {

    @Query("SELECT l from LeaveModel l WHERE l.isAccepted = 'true'")
    List<LeaveModel> findAllAcceptedLeave();

    @Query("SELECT l from LeaveModel l WHERE l.isAccepted = 'false'")
    List<LeaveModel> findAllRejectedLeave();
}
