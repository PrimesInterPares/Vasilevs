package com.company;


import java.util.Comparator;

/**
 * Created by Andron on 09.04.2016.
 */
public class TimeStampComparator implements Comparator<Message> {
        @Override
        public int compare(Message o1, Message o2) {
            return (int) (o1.getTimestamp() - o2.getTimestamp());
        }
}
