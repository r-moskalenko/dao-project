package com.romanm.pis.controller;

import com.romanm.pis.domain.UserType;
import com.romanm.pis.service.UserTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserTypeController {
    private final Logger logger = LoggerFactory.getLogger(UserTypeController.class);

    private final UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping("/user-types")
    public String getAllUserTypes(Model model) {
        List<UserType> userTypes = userTypeService.getAllUserTypes();
        model.addAttribute("listUserTypes", userTypes);

        return "userType-list";
    }

    @GetMapping("/user-types/create")
    public String getCreatePage() {
        return "create-report";
    }

    @PostMapping("/user-types/create")
    public String createReport(@RequestParam Long eventId, @RequestBody MultiValueMap<String,String> paramMap) {

        return "redirect:/api/reports?eventId=" + eventId;
    }
}
