package repository;

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
        String[] res = new String[objects.size()];
        for (int i = 0; i < objects.size(); i++) {
            res[i] = String.valueOf(objects.get(i));
        }
        return res;
    }
}
