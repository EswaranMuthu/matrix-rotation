



import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {




    /**
     Uploaded. Waiting for results...
     the IDE is frozen with above statement.
     I'm not able to execute the program.
     40 mins is just wasted -> I cannot able to test with different test case nor able to compile.
     **/

    // Complete the RotateMatrix function below.
    static List<List<Integer>> RotateMatrix(List<List<Integer>> sourceMatrix, int rotationDegree) {

        if(sourceMatrix == null || (sourceMatrix.size() == 0) || sourceMatrix.get(0) == null)
            return new ArrayList<>();

        AtomicInteger rowSize = new AtomicInteger(sourceMatrix.size() - 1) ;
        AtomicInteger columnSize = new AtomicInteger(sourceMatrix.get(1).size() - 1);
        AtomicInteger row = new AtomicInteger();
        AtomicInteger  column = new AtomicInteger();
        AtomicBoolean isRowTraversal = new AtomicBoolean();
        AtomicBoolean isASC = new AtomicBoolean();

        List<List<Integer>> result = new ArrayList<>();

        init(rotationDegree, rowSize, columnSize, row, column, isRowTraversal, isASC);
        for(;;) {
            List<Integer> newRow = new ArrayList<Integer>();
            if(breakLoop(row, column,rowSize, columnSize))
                break;
            int rowStart = row.intValue();
            int columnStart = column.intValue();
            for(;;){
                if(breakLoop(row, column,rowSize, columnSize))
                    break;
                newRow.add(sourceMatrix.get(row.get()).get(column.get()));
                updateRowColumn(isRowTraversal, isASC, row, column);
            }
            result.add(newRow);
            row.set(rowStart); column.set(columnStart);
            updateRowColumnAfterInnerLoop(isRowTraversal, isASC, row, column, rowSize, columnSize);

        }
        return result;
    }


    /**
     This method will udapte row and column value during start of the outer loop.

     **/
    private static  void updateRowColumnAfterInnerLoop(AtomicBoolean isRowTraversal, AtomicBoolean isASC, AtomicInteger row, AtomicInteger column, AtomicInteger rowSize, AtomicInteger columnSize){
        if(isRowTraversal.get()){
            if(isASC.get()) {
                column.set(column.intValue() - 1);
            }
            else  {
                column.set(column.intValue() + 1);
            }
        } else {
            if(isASC.get()){
                row.set(row.get() + 1) ;
            } else {
                row.set(row.intValue() - 1) ;
            }
        }
    }

    /**
     This method is used to update row and column of during traversal of inner loop

     **/
    static private void updateRowColumn(AtomicBoolean isRowTraversal, AtomicBoolean isASC, AtomicInteger row, AtomicInteger column){
        if(isRowTraversal.get()) {
            if(isASC.get())
                row.set(row.intValue() + 1);
            else
                row.set(row.intValue() -1 );
        } else {
            if(isASC.get())
                column.set(column.intValue() + 1);
            else
                column.set(column.intValue() -1 );
        }
    }


    /**

     This method will init row, column and to trversal pettern based on rotation degree.

     **/

    static private void init(int rotationDegree, AtomicInteger rowSize, AtomicInteger columnSize, AtomicInteger row, AtomicInteger column, AtomicBoolean isRowTraversal, AtomicBoolean isASC) {

        if((rotationDegree % 360) == 0) {
            row.set(0);
            column.set(0);
            isRowTraversal.set(false);
            isASC.set(true);
            return;
        }

        if((rotationDegree % 270) == 0) {
            row.set(rowSize.intValue());
            column.set(0);
            isRowTraversal.set(true);
            isASC.set(false);
            return;
        }

        if((rotationDegree % 180) == 0)  {
            row.set(rowSize.intValue());
            column.set(columnSize.intValue());
            isRowTraversal.set(false);
            isASC.set(false);
            return;
        }

        if((rotationDegree % 90) == 0) {
            column.set(columnSize.intValue());
            row.set(0);
            isRowTraversal.set(true);
            isASC.set(true);
            return;
        }
    }


    /**

     This method will check if we need to break the loop or not.

     **/

    static private boolean breakLoop(AtomicInteger row, AtomicInteger column, AtomicInteger rowSize, AtomicInteger columnSize){

        if(row.intValue() > rowSize.intValue() || row.intValue() < 0 || column.intValue() > columnSize.intValue() || column.intValue() < 0)
            return true;
        return false;
    }


    public static void main(String[] args) throws IOException {

        List<List<Integer>> test = new ArrayList<>();
        List<Integer> testing1 = new ArrayList<>();
        List<Integer> testing2 = new ArrayList<>();

        testing1.add(1); testing1.add(2);
        testing2.add(3); testing2.add(4);
        test.add(testing1);
        test.add(testing2);

        List<List<Integer>> tt = RotateMatrix(test,90);
        System.out.println("************ 90 degree *****************");
        for(int i=0;i< tt.size();i++){
            for(int j=0;j< testing1.size(); j++)
            {
                System.out.print(tt.get(i).get(j) + " - ");
            }
            System.out.println();
        }

        tt = RotateMatrix(test,180);
        System.out.println("************ 180 degree *****************");
        for(int i=0;i< tt.size();i++){
            for(int j=0;j< testing1.size(); j++)
            {
                System.out.print(tt.get(i).get(j) + " - ");
            }
            System.out.println();
        }

        tt = RotateMatrix(test,270);
        System.out.println("************ 270 degree *****************");
        for(int i=0;i< tt.size();i++){
            for(int j=0;j< testing1.size(); j++)
            {
                System.out.print(tt.get(i).get(j) + " - ");
            }
            System.out.println();
        }

        tt = RotateMatrix(test,360);
        System.out.println("************ 360 degree *****************");
        for(int i=0;i< tt.size();i++){
            for(int j=0;j< testing1.size(); j++)
            {
                System.out.print(tt.get(i).get(j) + " - ");
            }
            System.out.println();
        }


    }
}
