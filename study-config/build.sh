#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true -U
docker build -t hub.c.163.com/springcloud/study-config .
docker push hub.c.163.com/springcloud/study-config