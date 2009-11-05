@echo off

IF NOT EXIST app GOTO CHECKOUT

echo Delete old folder
rmdir /s /q app

:CHECKOUT
echo CheckOut Application
svn checkout http://261studentprojects.googlecode.com/svn/trunk/builder/Stepan/ app >> svn.log

:BUILD
echo Building Application
cd app
%SystemRoot%\Microsoft.NET\Framework\v3.5\MSBuild.exe >> ../build.log
cd ..