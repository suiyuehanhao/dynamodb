**1. 创建Table: Project_NAME, 分区键为projectName,排序键为projectType** 
```js
aws dynamodb create-table \
    --table-name Project_pei \
    --attribute-definitions \
        AttributeName=projectName,AttributeType=S \
        AttributeName=projectType,AttributeType=S \
    --key-schema \
        AttributeName=projectName,KeyType=HASH \
        AttributeName=projectType,KeyType=RANGE \
    --provisioned-throughput \
        ReadCapacityUnits=10,WriteCapacityUnits=5

aws dynamodb describe-table --table-name Project_pei | grep TableStatus

```


**2. 更新Table: 更新上表,添加Attributes: memberName, startDate** 
```js
aws dynamodb update-item \
    --table-name Project_pei \
    --key '{ "projectName": {"S": "project1"}, "projectType": {"S": "test02"}}' \
    --update-expression "SET memberName = :q1" \
    --expression-attribute-values '{":q1":{"S":"zhangsan"}}' \
    --return-values ALL_NEW

aws dynamodb update-item \
    --table-name Project_pei \
    --key '{ "projectName": {"S": "productName3”}, "projectType": {"S": "test3”}}' \
    --update-expression "SET startDate = :q2" \
    --expression-attribute-values '{":q2":{"S":"2020-09-099”}}' \
    --return-values ALL_NEW
```

**3. 写入数据: 插入5-10条数据,包含DynamoDB所有数据类型** 
```js
aws dynamodb put-item \
    --table-name Project_pei \
    --item '{
        "projectName": {"S": "productName3"},
        "projectType": {"S": "test3"} ,
        "memberName": {"S": "xiaozhang3"} ,
       "startDate": {"S": "2020-09-09"}
      }' \
    --return-consumed-capacity TOTAL
```

**4. 更新数据: 尝试更新已存在的数据**
```js
aws dynamodb update-item \
    --table-name Project_pei \
    --key '{ "projectName": {"S": "productName3"}, "projectType": {"S": "test3"}}' \
    --update-expression "SET startDate = :q2" \
    --expression-attribute-values '{":q2":{"S":"2020-09-099"}}' \
    --return-values ALL_NEW
```

**5. 读取数据: 根据主键读取数据** 
```js
aws dynamodb get-item --consistent-read \
    --table-name Project_pei \
--key '{ "projectName": {"S": "project2”}, "projectType": {"S": "test2"}}'
```
**6. 查询数据: 根据分区键和排序键查询数据, 尝试通过memberName查询数据**

```js
aws dynamodb query \
    --table-name Project_pei \
    --key-condition-expression "projectName = :name" \
    --expression-attribute-values  '{":name”:{"S”:”productName3”}}'
```

 
**7. 扫描数据: 全表扫描并按一定规则过滤数据(比如memberName)** 
```js
aws dynamodb scan \
     --table-name Project_pei \
     --filter-expression "memberName = :name" \
     --expression-attribute-values '{":name":{"S":"xiaozhang3"}}'
```
**8. 创建GSI: 对memberName创建全局二级索引** 
```js
aws dynamodb update-table \
    --table-name Project_pei \
 --attribute-definitions AttributeName=memberName,AttributeType=S \
    --global-secondary-index-updates \
    "[{\"Create\":{\"IndexName\": \"memberName-index\",\"KeySchema\":[{\"AttributeName\":\"memberName\",\"KeyType\":\"HASH\"}], \
    \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"

aws dynamodb describe-table --table-name Project_pei | grep IndexStatus
```
**9. 查询GSI: 根据memberName查询数据** 
```js
aws dynamodb query \
    --table-name Project_pei \
    --index-name memberName-index \
    --key-condition-expression "memberName = :name" \
    --expression-attribute-values  '{":name":{"S":"xiaozhang3"}}'
```

**10.删除表** 
```js
aws dynamodb delete-table --table-name Project_pei
```