package com.CodingNinjas.LeaveXpress.controller;

import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public LeaveModel getLeaveById(@PathVariable Long id){
        return leaveService.getLeaveById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public List<LeaveModel> getAllLeaves(){
        return leaveService.getAllLeaves();
    }

    @GetMapping("/accepted")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public List<LeaveModel> getAllAcceptedLeave(){
        return leaveService.getAllAcceptedLeave();
    }

    @GetMapping("/rejected")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public List<LeaveModel> getAllRejectedLeave(){
        return leaveService.getAllRejectedLeave();
    }

    @GetMapping("/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public boolean getStatusOfLeave(@PathVariable Long id){
        return leaveService.getStatusById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public void updateLeaveRecord(@PathVariable Long id, @RequestBody LeaveDto updatedLeave){
        leaveService.updateLeaveById(id, updatedLeave);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public void deleteLeave(@PathVariable Long id){
        leaveService.deleteLeave(id);
    }

    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public void applyLeave(@RequestBody LeaveDto updatedLeave){
        leaveService.applyLeave(updatedLeave);
    }

    @PostMapping("/accept/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('MANAGER')")
    public void acceptLeaveById(@PathVariable Long id){
        leaveService.acceptLeaveById(id);
    }

    @PostMapping("/reject/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('MANAGER')")
    public void rejectLeaveById(@PathVariable Long id){
        leaveService.rejectLeaveById(id);
    }






}
