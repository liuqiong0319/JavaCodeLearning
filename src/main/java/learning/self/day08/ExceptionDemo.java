package java.learning.self.day08;

/**
 * Created by qiong.liu on 2017/12/18.
 */
public class ExceptionDemo {
    public void method(int[] arra,int index) {
        if(arra == null)
        {
//            Throwable throwable=new Throwable();
             throw  new NullPointExceptionDemo(Throwable.class.getSimpleName());
//            throw  new NullPointExceptionDemo("数组是空的");
        }
        if(index<0||index>=arra.length)
        {
            throw new ArrayOutOfBoundException("角标不存在");
        }
    }

    public static void main(String[] args) {
//        ExceptionDemo exceptionDemo1 =new ExceptionDemo();
//        int[] arra1=new int[]{};
//        exceptionDemo1.method(arra1,-1);
//        Animal animal=new Animal("111");
//        System.out.println(animal.getName());
//        NoteBook noteBook=new NoteBook(1);
//        Teacher teacher=new Teacher();
//        teacher.teach("李");
//        new Person("john",-5);//自定义的异常继承运行时异常,无需声明和捕获,自动抛出给调用者,由调用者处理
//        System.out.println("程序over");

        try {
            Teacher teacher=new Teacher();
            NoteBook book=new NoteBook(1);
            teacher.teach("毕",book);
        }catch ( NoplanException e )
        {
            System.out.println(e.toString());
            System.out.println("换老师");
        }
    }
}
