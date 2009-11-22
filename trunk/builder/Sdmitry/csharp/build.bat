set mPATH=c:\261studentproject\Builder\Sdmitry\csharp
set cPATH=C:\Program Files\Microsoft Visual Studio 9.0\Common7\IDE
svn up
cd %cPATH%\
devenv.com /build Release %mPATH%\helloWorld\HelloWorld.sln >> %mPATH%\log.txt
     