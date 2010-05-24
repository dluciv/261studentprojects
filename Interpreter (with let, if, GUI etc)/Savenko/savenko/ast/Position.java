/*
 * класс для сохранения позиции текущей лексемы от начала текущей строки,
 * общего текущего индекса в тексте и размера лексемы
 * Savenko Maria(c)
 */
package savenko.ast;

public class Position {

     private int col_num = 0;
     private int row_num = 0;
     private int curr_ind = 0;
     private int range = 0;

     public Position(int col, int row, int ind, int range) {
          col_num = col;
          row_num = row;
          curr_ind = ind;
          this.range = range;
     }

     public Position() {

     }
     
     public int getColNumBeg() {
          return col_num;
     }

     public int getColNumEnd() {
          return col_num + range;
     }

     public int getRowNum() {
          return row_num;
     }

     public int getCurrInd() {
          return curr_ind;
     }

     public int getEndInd() {
          return curr_ind +range;
     }
}
