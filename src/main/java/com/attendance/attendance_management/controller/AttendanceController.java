package com.attendance.attendance_management.controller;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @GetMapping("/status")
    public Map<String, String> status() {

        Map<String, String> response = new HashMap<>();

        response.put("status", "Attendance Service Running Successfully");//testing for pipeline

        return response;
    }

    @PostMapping("/checkin")
    public Map<String, String> checkIn(@RequestParam String user) {

        Map<String, String> response = new HashMap<>();

        response.put("message", user + " checked in");

        return response;
    }
}