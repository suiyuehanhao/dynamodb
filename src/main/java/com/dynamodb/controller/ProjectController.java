package com.dynamodb.controller;

import com.dynamodb.model.Project;
import com.dynamodb.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public List<Project> login() {

        return projectService.findAllByMemberName("xiaozhang4");
    }

}