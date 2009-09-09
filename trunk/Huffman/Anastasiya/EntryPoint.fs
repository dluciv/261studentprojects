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
                                                      |> Messages.testResult
    | CMLArgManager.Huffman, CMLArgManager.Encode  -> HuffmanReading.makeArchive input output
    | CMLArgManager.Huffman, CMLArgManager.Decode -> HuffmanReading.decodeArchive input output
    | CMLArgManager.Huffman, CMLArgManager.Test  -> HuffmanReading.makeArchive input output
                                                    HuffmanReading.decodeArchive output (secondOutput output)
                                                    LangUtils.fileEquals input (secondOutput output)
                                                    |> Messages.testResult
                                                    
let analize input output = ((double)(new FileInfo(output)).Length / (double)(new FileInfo(input)).Length)
                           |> Messages.statistics
                                                        
let showStat input output (show : CMLArgManager.ShowStats) (action : CMLArgManager.ActionType) = 
    match show, action with
    |CMLArgManager.Show, CMLArgManager.Encode -> analize input output
    |CMLArgManager.Show, CMLArgManager.Decode -> analize output input
    |CMLArgManager.Show, CMLArgManager.Test -> analize input output
    | _ -> ()

let  main = 
    let param = CMLArgManager.parse cmdArgs
    archive (CMLArgManager.getInputFileName param) (CMLArgManager.getOutputFileName param)
      (CMLArgManager.getArchiveType param) (CMLArgManager.getActionType param)
    showStat (CMLArgManager.getInputFileName param) (CMLArgManager.getOutputFileName param)
      (CMLArgManager.isShowStats param) (CMLArgManager.getActionType param)
      
      
main      