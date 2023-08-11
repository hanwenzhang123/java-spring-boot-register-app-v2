package com.example.university.controllers;

import com.example.university.models.*;
import com.example.university.util.*;
import com.example.university.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Controller
public class WebController {
    private List<DepartmentDetails> departments;

    @Autowired
    RosterDAOService rosterService;
    public WebController() {
        System.out.println("Web Controller...");
    }

    @GetMapping("/")
    public String testRequest(Model model) {
        departments = rosterService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "hello";
    }
}
