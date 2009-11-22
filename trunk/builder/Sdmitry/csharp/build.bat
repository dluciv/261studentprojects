set mPATH=c:\261studentproject\Builder\Sdmitry\csharp
set cPATH=C:\Program Files\Microsoft Visual Studio 9.0\Common7\IDE
cd %cPATH%\
devenv.com /build Debug %mPATH%\helloWorld\HelloWorld.sln >> %mPATH%\log.txt
     