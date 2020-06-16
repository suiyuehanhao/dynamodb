package com.dynamodb.repository;

import com.dynamodb.model.Project;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangpei
 * @date 2020/6/15 22:19
 * @description
 */
@EnableScan
public interface ProjectRepository  extends CrudRepository<Project,String> {

    List<Project> findAllByMemberName(String memberName);

}
