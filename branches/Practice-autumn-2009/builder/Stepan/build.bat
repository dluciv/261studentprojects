@echo off

IF NOT EXIST app GOTO CHECKOUT

echo Updating Application

:UPDATE
svn update app >> svn.log
goto BUILD

:CHECKOUT
echo CheckOut Application
svn checkout http://261studentprojects.googlecode.com/svn/trunk/builder/Stepan/ app >> svn.log

:BUILD
echo Building Application
cd app
%SystemRoot%\Microsoft.NET\Framework\v3.5\MSBuild.exe >> ../build.log
cd ..

:CHECK

for /f %%f in (build.ini) do (
   if NOT EXIST app\%%f (
     echo File Not Exists - app\%%f
     goto FAIL
   )
)

goto SUCCESS

:FAIL
echo Build Failed!
goto END

:SUCCESS
echo Build Success!

:END