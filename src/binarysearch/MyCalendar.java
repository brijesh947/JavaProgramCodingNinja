package binarysearch;

import java.util.ArrayList;

public class MyCalendar {

    ArrayList<int[]> arr;

    public MyCalendar() {
        arr = new ArrayList();
    }

    public boolean book(int start, int end) {
        for (int[] item : arr) {
            if ((start>=item[0] && start < item[1]) || (item[0]>=start && item[0]<end))
                return false;
        }
        arr.add(new int[]{start, end});
        return true;
    }

}
