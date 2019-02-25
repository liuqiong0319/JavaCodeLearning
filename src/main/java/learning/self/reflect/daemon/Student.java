package java.learning.self.reflect.daemon;

/**
 * Created by qiong.liu on 2018/8/29.
 */
public class Student {
    public String name;
    protected  int age;
    char sex;
    private String phone;

    public Student() {
        System.out.println("无参的构造方法");
    }

    public Student(String name) {
        this.name = name;
        System.out.println("有一个参数的构造方法");

    }

    public Student(int age, String name, char sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        System.out.println("有多个参数的构造方法");
    }

    protected Student(boolean sex){
        System.out.println("受保护的构造方法");
    }

    private Student(int age){
        System.out.println("私有的构造方法");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void show1(String s){
        System.out.println("调用了：公有的，String参数的show1(): s = " + s);
    }
    protected void show2(){
        System.out.println("调用了：受保护的，无参的show2()");
    }
    void show3() {
        System.out.println("调用了：默认的，无参的show3()");
    }
    private String show4(int Id){
        System.out.println("调用了，私有的，并且有返回值的，int参数的show4(): age = " + Id);
        return "show4";
    }
}
