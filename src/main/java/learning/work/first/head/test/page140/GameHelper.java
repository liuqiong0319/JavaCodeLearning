package learning.work.first.head.test.page140;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by qiong.liu on 2018/10/9.
 *
 * 用户猜测的坐标
 */
public class GameHelper {
    private static final String alphabet="abcdefg";
    private int gridLength=7;
    private int gridSize=49;
    private int[] grid=new int[gridSize];
    private int comCount=0;

    public String userInput(){
        System.out.print("请输入您猜测的坐标: ");
        Scanner scanner=new Scanner(System.in);
        String userInput=scanner.next();
        return userInput;
    }

    public ArrayList<String> placeDotCom(int comSize){
        ArrayList<String> alphaCells=new ArrayList<String>();
        String [] alphacoords=new String[comSize];
        String temp=null;
        int[] coords=new int[comSize];
        int attempts=0;
        boolean success=false;
        int location=0;
        comCount++;
        int incr=1;
        if ((comCount%2)==1){
            incr=gridLength;
        }

        while (!success&attempts++<200){
            location=(int) (Math.random()*gridSize);
            int x=0;
            success=true;
            while (success && x<comSize){
                if(grid[location]==0) {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {
                        success = false;
                    }
                }
                    else {
                        success=false;
                }
            }
        }

        int x=0;
        int row=0;
        int column=0;
        while (x<comSize){
            grid[coords[x]]=1;
            row=(int) (coords[x]/gridLength);
            column=coords[x]%gridLength;
            temp=String.valueOf(alphabet.charAt(column));
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
            System.out.println(" coord "+x+"="+alphaCells.get(x-1));
        }
        return alphaCells;
    }
}
