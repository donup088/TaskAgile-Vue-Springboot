#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2

echo "> 기존 build 폴더 삭제"

rm -rf $REPOSITORY/frontend/dist

echo "> zip 파일 복사 "

cp -r $REPOSITORY/frontend-zip/dist $REPOSITORY/frontend/dist

echo "> nginx restart"

sudo service nginx restart
