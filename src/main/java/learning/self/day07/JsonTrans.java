package java.learning.self.day07;// package learn.java.learning.self.workLearning;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
//
// import net.sf.json.JSONArray;
// import net.sf.json.JSONObject;
//
// import org.testng.annotations.DataProvider;
// import org.testng.annotations.Test;
//
/// **
// * Created by joan.liu on 2017/9/11.
// */
// public class JsonTrans {
//
// /*
// * Bean是符合一定规范编写的Java类. 1、所有属性为private 2、提供默认构造方法 3、提供getter和setter
// * 4、实现serializable接口
// */
// @Test(groups = { "test1" })
// // 设置分组
// @DataProvider(name = "hadoopTest", parallel = true)
// // 数据驱动
// public static Object[][] beanToJson() {
// Person person = new Person();
// person.setName("joan");
// person.setScores(98.5);
// person.setAge(22);
// JSONObject ob = JSONObject.fromObject(person);// bean转为Json
// String name = ob.get("name").toString();
// System.out.println("ob-------" + ob);
// System.out.println("ob.get(\"name\")-------" + ob.get("name"));
// System.out.println("name--------" + name);
// return new Object[0][];
// }
//
// @Test(groups = { "test1", "test2" })
// public static void listToJson() {
// Person person1 = new Person();
// person1.setName("joan.liu");
// person1.setScores(99);
// person1.setAge(21);
// Person person2 = new Person();
// person2.setName("joan.liu");
// person2.setScores(99);
// person2.setAge(21);
//
// List<Person> list = new ArrayList<Person>();
// list.add(person1);
// list.add(person2);
// JSONArray json = JSONArray.fromObject(list);// List转为Json
// System.out.println(json);
// }
//
// @Test(groups = { "test2" })
// public static void mapToJson() {
// HashMap<String, String> map = new HashMap<String, String>();
// map.put("name", "liuqiong");
// map.put("age", "22");
// JSONObject json = JSONObject.fromObject(map);// Map转为Json
// System.out.println(json);
// }
//
// public static void main(String[] args) {
// beanToJson();
// listToJson();
// mapToJson();
// }
// }
