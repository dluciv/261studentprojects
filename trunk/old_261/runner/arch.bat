@if %1 EQU ar (goto type_defined) 
@if %1 NEQ hf (@echo Не указан тип алгоритма: ar или hf & exit) 
:type_defined
@if not exist %2 (@echo Не найдена директория %2 & exit) 
@if not exist %3 (@mkdir %3)

@FOR %%i IN (%2\*) DO @(
@echo %%~nxi
htnew.exe -algorithm %1 -in "%%i" -out "%3\%%~nxi" -stat -action test

)