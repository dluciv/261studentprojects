set mPATH=w:\261studentprojects\Builder\Sdmitry\csharp
if exist %mPATH%\hwpro (del /q hwpro) else (md %mPATH%\hwpro)
cd %mPATH% 
svn up
C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727\csc.exe /out:%mPATH%\hw.exe %mPATH%\hw.cs %mPATH%\hwdll.cs
