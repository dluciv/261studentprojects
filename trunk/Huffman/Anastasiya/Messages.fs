open System


let statistics (percents : double)=  Convert.ToString percents
                                     |> printf "Сжатие: %s\n"
let testResult result = 
    if result
      then printf "Тест пройден успешно: исходный файл и результат разархивации совпадают\n"
      else printf "Тест завершился неудачей: исходный файл и результат разархивации не совпадают\n"
      
let notEqualsAt =
    printf "Расхождение после %d байта\n"       