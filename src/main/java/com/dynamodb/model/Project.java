package com.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

/**
 * @author wangpei
 * @date 2020/6/15 21:50
 * @description
 */
@DynamoDBTable(tableName = "Project_pei")
public class Project {
    private String projectName;
    private String projectType;
    private String startDate;
    private String memberName;

    public Project(){

    }
    public Project(String projectName,String projectType,String startDate,String memberName){
        this.projectName=projectName;
        this.projectType=projectType;
        this.startDate=startDate;
        this.memberName=memberName;
    }

    @DynamoDBHashKey
    public String getProjectName() {
        return projectName;
    }

    @DynamoDBRangeKey
    public String getProjectType() {
        return projectType;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "memberName-index",attributeName = "memberName")
    public String getMemberName() {
        return memberName;
    }
    @DynamoDBAttribute
    public String getStartDate() {
        return startDate;
    }


}
