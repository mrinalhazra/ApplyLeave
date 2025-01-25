package com.CodingNinjas.LeaveXpress.service;

import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.exception.LeaveNotFoundException;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    public LeaveModel getLeaveById(Long id) {
        try {
            return leaveRepository.findById(id).get();
        } catch (Exception e) {
            throw new LeaveNotFoundException("No Leave found by the id: "+id);
        }
    }

    public List<LeaveModel> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public List<LeaveModel> getAllAcceptedLeave() {
        return leaveRepository.findAllAcceptedLeave();
    }

    public List<LeaveModel> getAllRejectedLeave() {
        return leaveRepository.findAllRejectedLeave();
    }

    public boolean getStatusById(Long id) {
        try {
            LeaveModel leaveModel = this.getLeaveById(id);
            return leaveModel.isAccepted();
        } catch (Exception e) {
            throw new LeaveNotFoundException("No Leave found by the id: "+id);
        }
    }

    public void updateLeaveById(Long id, LeaveDto updatedLeave) {
        LeaveModel leaveModel = null;
        try {
            leaveModel = leaveRepository.findById(id).get();
        } catch (Exception e) {
            throw new LeaveNotFoundException("No Leave found by the id: "+id);
        }
        leaveModel.setType(updatedLeave.getType());
        leaveModel.setDescription(updatedLeave.getDescription());
        leaveModel.setEndDate(updatedLeave.getEndDate());
        leaveModel.setStartDate(updatedLeave.getStartDate());
        leaveRepository.save(leaveModel);
    }

    public void deleteLeave(Long id) {
        try {
            leaveRepository.deleteById(id);
        } catch (Exception e) {
            throw new LeaveNotFoundException("No Leave found by the id: "+id);
        }
    }

    public void applyLeave(LeaveDto updatedLeave) {
        LeaveModel leave = new LeaveModel();
        leave.setType(updatedLeave.getType());
        leave.setDescription(updatedLeave.getDescription());
        leave.setEndDate(updatedLeave.getEndDate());
        leave.setStartDate(updatedLeave.getStartDate());
        leaveRepository.save(leave);

    }

    public void acceptLeaveById(Long id) {
        LeaveModel leave = null;
        try {
            leave = this.getLeaveById(id);
        } catch (Exception e) {
            throw new LeaveNotFoundException("No Leave found by the id: "+id);
        }
        leave.setAccepted(true);
        leaveRepository.save(leave);
    }

    public void rejectLeaveById(Long id) {
        try {
            LeaveModel leave = this.getLeaveById(id);
            leave.setAccepted(false);
            leaveRepository.save(leave);
        } catch (Exception e) {
            throw new LeaveNotFoundException("No Leave found by the id: "+id);
        }
    }
}
