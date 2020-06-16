package com.dynamodb.service;

import com.dynamodb.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import com.dynamodb.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangpei
 * @date 2020/6/15 22:23
 * @description
 */
@Service
public class ProjectService {

    ProjectRepository projectRepository;

    @Autowired
    ProjectService(ProjectRepository projectRepository) {
        projectRepository = projectRepository;
    }

    public List<Project> findAllByMemberName(String memberName) {
        return projectRepository.findAllByMemberName(memberName);
    }
}
