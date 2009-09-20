open System.Collections.Generic
open System.IO

let cmdArgs = System.Environment.GetCommandLineArgs()
              |> List.of_array 


    
let secondOutput output =  output + ".test";    
    
let archive input output (alg : CMLArgManager.ArchiveType) (typ : CMLArgManager.ActionType)= 
    match alg, typ with
    | CMLArgManager.Alrifmetic, CMLArgManager.Encode -> ArifmeticReading.encodeArchive input output
    | CMLArgManager.Alrifmetic, CMLArgManager.Decode -> ArifmeticReading.decodeArchive input output
    | CMLArgManager.Alrifmetic, CMLArgManager.Test -> ArifmeticReading.encodeArchive input output
                                                      ArifmeticReading.decodeArchive output (secondOutput output)
                                                      LangUtils.fileEquals input (secondOutput output)
                                                      |> Messages.testResult input (secondOutput output)
    | CMLArgManager.Huffman, CMLArgManager.Encode  -> HuffmanReading.makeArchive input output
    | CMLArgManager.Huffman, CMLArgManager.Decode -> HuffmanReading.decodeArchive input output
    | CMLArgManager.Huffman, CMLArgManager.Test  -> HuffmanReading.makeArchive input output
                                                    HuffmanReading.decodeArchive output (secondOutput output)
                                                    LangUtils.fileEquals input (secondOutput output)
                                                    |> Messages.testResult input (secondOutput output)
                                                    
let analize input output = ((double)(new FileInfo(output)).Length / (double)(new FileInfo(input)).Length)
                           |> Messages.statistics

let testInputNotEmpty input = 
    if (new FileInfo(input)).Length = 0L
      then
        Messages.emptyInput
        exit(0)
      else
        ()

let testInputExists input = 
    if (new FileInfo(input)).Exists 
      then 
        ()
      else
        Messages.inputNotExists input
        exit(1)
                                                        
let showStat input output (show : CMLArgManager.ShowStats) (action : CMLArgManager.ActionType) = 
    match show, action with
    |CMLArgManager.Show, CMLArgManager.Encode -> analize input output
    |CMLArgManager.Show, CMLArgManager.Decode -> analize output input
    |CMLArgManager.Show, CMLArgManager.Test -> analize input output
    | _ -> ()

let  main = 
    let param = CMLArgManager.parse cmdArgs
    testInputExists param.inputFile
    testInputNotEmpty param.inputFile
    archive param.inputFile param.outputFile param.archiveType param.actionType
    showStat param.inputFile param.outputFile param.showStat param.actionType
      
      
main      