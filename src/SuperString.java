import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by martin on 12/8/16.
 */
public class SuperString {
    private LinkedList<String> strings;
    private LinkedList<Boolean> helpList;

    public SuperString() {
        strings = new LinkedList<>();
        helpList = new LinkedList<>();
    }

    public void append(String s) {
        strings.addLast(s);
        helpList.add(true);
    }

    public void insert(String s) {
        strings.addFirst(s);
        helpList.add(false);

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String s : strings)
            builder.append(s);
        return builder.toString();
    }

    public void removeLast(int k) {
        List<Boolean> tmp = helpList.subList(helpList.size()-k, helpList.size());
        for (int i = 0; i < k; i++) {
            if (tmp.get(i) == true)
                strings.removeLast();
            else
                strings.removeFirst();
        }
    }

    public void reverse() {
        LinkedList<String> res = new LinkedList<>();
        ListIterator<String> it = strings.listIterator();
        while (it.hasNext()) {
            String s = it.next();
            s = new StringBuilder(s).reverse().toString();
            res.add(s);
        }
        Collections.reverse(res);
        strings = res;
    }

    public boolean contains(String s) {
        Pattern key = Pattern.compile(s);
        Matcher matcher = key.matcher(this.toString());
        return matcher.find();
    }
}
