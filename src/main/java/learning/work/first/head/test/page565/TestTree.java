package learning.work.first.head.test.page565;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class TestTree {
    public static void main(String[] args) {
        new TestTree().go();
    }

    private void go() {
        Book book1=new Book("how cats work");
        Book book2=new Book("Family life");
        Book book3=new Book("war and peace");

        TreeSet<Book> tree=new TreeSet<Book>();//book实现接口comparable进行比较
        tree.add(book1);
        tree.add(book2);
        tree.add(book3);
        System.out.println(tree);

        TitleSort titleSort=new TitleSort();//单独方法实现接口comparator进行比较
        TreeSet<Book> tree2=new TreeSet<Book>(titleSort);//使用构造函数public TreeSet(Comparator<? super E> comparator)
        tree2.addAll(tree);
        System.out.println(tree2);
    }

    class Book implements Comparable<Book>{
        private  String title;
        private int num;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Book(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "title='" + title + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Book o1) {
            return title.compareTo(o1.getTitle());
        }
    }

    class TitleSort implements Comparator<Book>{

        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }
}
