if exist hwpro (del /q hwpro) else (md hwpro)
C:\WINDOWS\Microsoft.NET\Framework\v2.0.50727\csc.exe /out:hwpro/hw.exe hw.cs hwdll.cs
