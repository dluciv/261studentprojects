set sPATH=c:\261studentproject\Builder\Sdmitry\csharp
set cPATH=C:\Program Files\Microsoft Visual Studio 9.0\Common7\IDE
set tPATH=C:\Testarea

svn up
xcopy /y /d /s %sPATH%\helloworld %tPATH%


"%cPATH%"\devenv.com /build Release %tPATH%\helloWorld\HelloWorld.sln >> %tPATH%\log.txt
"%cPATH%"\devenv.com /build Debug /out %tPATH%\log.txt %tPATH%\helloWorld\HelloWorld.sln