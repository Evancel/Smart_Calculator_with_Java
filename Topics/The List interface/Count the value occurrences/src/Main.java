import java.util.Collections;
import java.util.List;
import java.util.Objects;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {

        int countList1 = 0;
        int countList2 = 0;
        for (Integer val : list1) {
            if (val.equals(elem)) {
                countList1++;
            }
        }

        for (Integer val : list2) {
            if (val.equals(elem)) {
                countList2++;
            }
        }
        return countList1 == countList2;

//        return Collections.frequency(list1,elem) == Collections.frequency(list2,elem);
    }
}