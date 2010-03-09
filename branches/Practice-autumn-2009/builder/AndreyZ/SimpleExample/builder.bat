@echo off

:Checkout
echo Checkout Solution, please wait..
svn checkout http://261studentprojects.googlecode.com/svn/trunk/builder/AndreyZ/SimpleExample/ >> SvnLog.txt

:BUILD
echo Building..
cd SimpleExample
%SystemRoot%\Microsoft.NET\Framework\v3.5\MSBuild.exe >> buildlog.txt
cd

for /f %%f in (builder.txt) do (
   if not exist %%f (
     echo Could't find files
     goto Error
   )
)

goto Success

:Error
echo There're some errors in building
goto End

:Success
echo Building finished

:End
echo Press any key..
Pause >nul