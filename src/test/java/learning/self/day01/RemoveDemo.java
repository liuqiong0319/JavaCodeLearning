package learning.self.day01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joan.liu on 2017/9/4.
 */
public class RemoveDemo {

	public static void main(String[] args) {
		remove();
	}

	public static void remove() {
		String[] str = { "001", "002", "003", "004", "001", "002", "005" };
		// List<String> lt=Arrays.asList(str);
		// lt.add("008"); //Arrays.asList返回的List不可修改
		// Set<String> st=new HashSet<>(lt);
		// System.out.println(st);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			if (!list.contains(str[i])) {// 如果数组 list 不包含当前项，则增加该项到数组中
				list.add(str[i]);
			}
		}

		System.out.println(list.toArray());
		// 输出
		// System.out.println(list);
		// String[] newStr = list.toArray(new String[1]);
		// System.out.println(newStr);
		// System.out.println(newStr.length);
		// for (String element:newStr ) {
		// System.out.print(element + " ");
		// }
	}
}
