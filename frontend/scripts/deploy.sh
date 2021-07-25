#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2

echo "> zip 파일 복사 "

cp -r $REPOSITORY/frontend-zip/dist /var/www/html

echo "> nginx restart"

sudo service nginx restart
