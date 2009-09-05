open System.Collections.Generic
open System.IO

let cmdArgs = System.Environment.GetCommandLineArgs()
              |> List.of_array 


    
let secondOutput output =  output + "test";    
    
let archive input output (alg : CMLArgManager.ArchiveType) (typ : CMLArgManager.ActionType)= 
    match alg, typ with
    | CMLArgManager.Alrifmetic, CMLArgManager.Encode -> ()
    | CMLArgManager.Alrifmetic, CMLArgManager.Decode -> ()
    | CMLArgManager.Alrifmetic, CMLArgManager.Test -> ()
    | CMLArgManager.Huffman, CMLArgManager.Encode  -> Main.makeArchive input output
    | CMLArgManager.Huffman, CMLArgManager.Decode -> Main.decodeArchive input output
    | CMLArgManager.Huffman, CMLArgManager.Test  -> Main.makeArchive input output
                                                    Main.decodeArchive output (secondOutput output)
                                                    Main.fileEquals input (secondOutput output)
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