package java.learning.work;

import java.io.*;
import java.util.Scanner;

/**
 * Created by qiong.liu on 2018/8/28.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        //第一次作业
//        task1();
        //第二次作业
//        task2();
        //第三次作业
//        task3();
        //第四次作业
//        task4();
        //第五次作业
//        task5();
        //第六次作业
//        task6();
//        task7();


//        Date d = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(d);
//        cal.add(Calendar.YEAR, 1);
//        cal.add(Calendar.MONTH,1);
//        SimpleDateFormat dateFormat0 = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat0.format(cal.getTime());
//        System.out.println(dateFormat0.format(cal.getTime()));


        long time=System.currentTimeMillis();
        System.out.println(time);

    }

    private static void task7() throws IOException {
        //需求:将多个文件的内容读取后写入另一个新文件中

        File file1 = new File("/Users/qiong.liu/Documents/shell.txt");
        File file2 = new File("/Users/qiong.liu/Documents/student.txt");
        File file3 = new File("/Users/qiong.liu/Documents/merge.txt");

        if (file3.exists()) {//判断文件是否存在,若存在则删除重建
            file3.delete();
            file3.createNewFile();
        }
        //读取文件1和2至字节流中,并以字节形式写入
        FileInputStream fileInputStream1 = new FileInputStream(file1);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        FileOutputStream fileOutputStream = new FileOutputStream(file3, true);//将文件写入内容进行追加

        //读取文件1和2至字符流中,并以字符形式写入
        InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1);
        InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

        //带有缓冲区的高效读取,并以缓冲区形式高效写入
        BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
        BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        //字节流文件的读取与写入
        int length = 0;
        byte[] buf = new byte[1024];
        while ((length = fileInputStream1.read(buf)) != -1) {
            System.out.println(new String(buf, 0, length));//读取并显示到屏幕上
            fileOutputStream.write(buf, 0, length);//写入文件3
            fileOutputStream.flush();//实时刷新
        }
        while ((length = fileInputStream2.read(buf)) != -1) {
            System.out.println(new String(buf, 0, length));//读取并显示到屏幕上
            fileOutputStream.write(buf, 0, length);//写入文件3
            fileOutputStream.flush();//实时刷新
        }
        //字符流文件的读取与写入
        char[] buf2 = new char[1024];
        while ((length = inputStreamReader1.read(buf2)) != -1) {
            System.out.println(new String(buf2, 0, length));//读取并显示到屏幕上
            outputStreamWriter.write(buf2, 0, length);
            outputStreamWriter.flush();//实时刷新
        }
        while ((length = inputStreamReader2.read(buf2)) != -1) {
            System.out.println(new String(buf2, 0, length));//读取并显示到屏幕上
            outputStreamWriter.write(buf2, 0, length);//写入文件3
            outputStreamWriter.flush();//实时刷新
        }

        //带有缓冲区的读取与写入
        while (bufferedReader1.ready()) {
            String str1 = bufferedReader1.readLine();
            System.out.println(str1);//读取并显示到屏幕上
            bufferedWriter.write(str1);//写入文件3
            bufferedWriter.flush();//实时刷新
        }

        while (bufferedReader2.ready()) {
            String str2 = bufferedReader2.readLine();
            System.out.println(str2);//读取并显示到屏幕上
            bufferedWriter.write(str2);//写入文件3
            bufferedWriter.flush();//实时刷新
        }

        bufferedReader1.close();
        bufferedReader2.close();
        bufferedWriter.close();
    }

    private static void task6() {
        //需求:获取指定字符串位置,并将其进行分割,转换成int,并进行运算
        String str="100+200";
        String str1="100";
        String str2="200";
        int index1=str.indexOf(str1);
        int index2=str.indexOf(str2);//获取指定字符串位置
//        String str3=str.substring(index1,index1+str1.length());
//        String str4=str.substring(index2,index2+str2.length());
        int index3=str.indexOf("+");
        String str3=str.substring(0,index3);//获取指定位置的字符串
        String str4=str.substring(index3+1);
        int num1=Integer.valueOf(str3);//将指定字符串转化为整型
        int num2=Integer.valueOf(str4);
        int result=num1+num2;
        System.out.println("相加结果是:"+result);
    }

    private static void task5() {
        //使用for循环和while循环计算1到100的和
        int sum_for=0,sum_while=0;
        int max=100;
        for(int i=1;i<=max;i++){
            sum_for=sum_for+i;
        }
        while (true){
            if(max<1)
                break;
            sum_while=sum_while+max;
            max--;
        }
        System.out.println("sum_while="+sum_while+";sum_for="+sum_for);
    }

    private static void task4() {
        //需求:注册系统,要求验证用户名\密码均大于6位,且两次密码必须相等,若不满足条件,须有相应的提示信息
        String username;
        String passwd;
        String repeatPasswd;
        System.out.println("欢迎注册本系统");
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入用户名: ");
        username=scanner.nextLine();
        if(username.length()<=6){
            System.out.println("用户名长度必须大于6位");
        }
        else{
            System.out.print("请输入密码: ");
            passwd=scanner.nextLine();
            if(passwd.length()<=6){
                System.out.println("密码长度必须大于6位");
            }
            else {
                System.out.print("请再次输入密码: ");
                repeatPasswd = scanner.nextLine() ;
                if(passwd.equals(repeatPasswd))
                {
                    System.out.println("注册成功!");
                }
                else{
                    System.out.println("两次输入密码必须一致");
                }
            }
        }

    }

    private static void task3() {
        //需求:判断一个给定的变量一下数字逻辑是否成立
        int x=15;
        boolean result1=(x>0 && x<20)?true:false;
        boolean result2=(x<=10 || x>=20)?true:false;
        boolean result3=(!(x>10 && x<20))?true:false;
        System.out.println("0<x<20?   "+result1);
        System.out.println("x<=10或x>=20?   "+result2);
        System.out.println("x不在10~20之间?   "+result3);
    }

    private static void task2() {
        int x=010,y=15,z=19,sum;
        sum=x+y+z;
        System.out.println("sum="+sum);
    }

    private static void task1() {
        System.out.println("hello world");
    }
}