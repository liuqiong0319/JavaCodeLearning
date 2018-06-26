package learning.self.ioDemo.domain;

/**
 * Created by qiong.liu on 2018/5/8.
 */
public class Student implements Comparable<Student> {
    private String name;
    private int cn,en,ma,sum;

    public Student(String name,int cn, int en, int ma) {
        this.cn = cn;
        this.en = en;
        this.ma = ma;
        this.name = name;
        this.sum=cn+en+ma;
    }

    public Student() {
    }

    public int getCn() {
        return cn;
    }

    public void setCn(int cn) {
        this.cn = cn;
    }

    public int getEn() {
        return en;
    }

    public void setEn(int en) {
        this.en = en;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (cn != student.cn) return false;
        if (en != student.en) return false;
        if (ma != student.ma) return false;
        if (sum != student.sum) return false;
        return name != null ? name.equals(student.name) : student.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + cn;
        result = 31 * result + en;
        result = 31 * result + ma;
        result = 31 * result + sum;
        return result;
    }

    @Override
    public int compareTo(Student o) {
       int temp=this.sum-o.sum;
        return temp==0?this.name.compareTo(o.name):temp;
    }
}
