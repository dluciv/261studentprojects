open System


let statistics (percents : double)=  Convert.ToString percents
                                     |> printf "Сжатие: %s\n"
let testResult input testResult result = 
    if result
      then printf "Тест пройден успешно: исходный файл %s и результат разархивации %s совпадают\n\n" input testResult
      else printf "Тест завершился неудачей: исходный файл %s и результат разархивации %s не совпадают\n\n" input testResult
      
let notEqualsAt =
    printf "Расхождение после %d байта\n"       
    
let warningNoInputFile u = 
    printf "Предупреждение: не указан файл, который необходимо закодировать. По умолчанию используется input.txt\n"
    u
  
let warningNoOutputFile u = 
    printf "Предупреждение: не указан файл для сохранения архива. По умолчанию используется output.txt\n"
    u
  
let wrongCMDArgs = "Указаны неверные аргументы командной строки. Укажите -help для получения справки.\n"

let wrongActionType = "Указано неверное значение: тип действия может быть encode, decode и test\n"

let wrongAlgorithm = "Указано неверное значение: доступны алгоритмы ar и hf\n"

let errorReadingDictionary = 
    "Нарушена целостность словаря архива."
    
let internalError name = 
    "Внутренняя ошибка в методе" + name + ".\n"  
    
let internalTreeError = 
    "Внутренняя ошибка (метод Хаффмана).\n"     
    
let emptyInput = 
    printf "Файл с для архивации пуст. Пропущено.\n\n"   
    
let inputNotExists name =
    printf "Указанный для архивации файл %s не существует.\n" name    