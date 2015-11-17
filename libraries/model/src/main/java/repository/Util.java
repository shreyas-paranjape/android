package repository;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static String makePlaceholders(int len) {
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

    public static String[] listToStringArray(List<?> objects) {
        List<String> res = new ArrayList<>();
        for (Object obj : objects) {
            res.add(String.valueOf(obj));
        }
        return (String[]) res.toArray();
    }
}
