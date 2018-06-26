package learning.self.collectionDemo.setDemo;

/**
 * Created by qiong.liu on 2018/3/24.
 */
public class Student implements Comparable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student)) return false;
//
//        Student student = (Student) o;
//
//        return this.name.equals(student.getName()) && this.age==student.getAge();
//
//    }
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student)) return false;
//
//        Student student = (Student) o;
//
//        if (age != student.age) return false;
//        return name != null ? name.equals(student.name) : student.name == null;
//
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        return name != null ? name.equals(student.name) : student.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    /**
     * 重写compareTo方法，建立学生的自然排序(对象的默认排序方式)。
     * 按照学生年龄排序。
     */

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Student)){//判断当前对象是否为学生对象,若不是则抛类型转换异常
            throw new ClassCastException();
        }

        Student stu=(Student)o;//将当前对象转化为Student对象
        int temp=this.age-stu.age;//进行年龄比较
        return temp==0?this.name.compareTo(stu.name):temp;//当年龄一致时,比较姓名.
 }
}
