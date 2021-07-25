#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2

echo "> zip 파일 복사 "

sudo cp -r $REPOSITORY/front-end-zip/dist /var/www/html

echo "> nginx restart"

sudo service nginx restart
