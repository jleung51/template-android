package com.template.application.location;

import android.location.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class stores the number of counts of a unique location and returns them as a single string.
 */
public class LocationRecorder {

    private Map<String, Integer> record;

    public LocationRecorder() {
         record = new HashMap<>();
    }

    @SuppressWarnings("unused")
    public void recordAt(Location location) {
        String key = locationToString(location);
        Integer current = record.get(key);

        if (current == null) {
            record.put(key, 1);
        }
        else {
            record.put(key,  current + 1);
        }
    }

    private String locationToString(Location location) {
        return location.getLatitude() + "," + location.getLongitude();
    }

    @SuppressWarnings("unused")
    String dump() {
        StringBuilder dump = new StringBuilder();

        Set<String> keys = record.keySet();
        for (String key : keys) {
            dump.append("2019-09-08 08:00:00,")
                    .append(record.get(key))
                    .append(",")
                    .append(key)
                    .append(",Sunday\n");
        }

        return dump.toString();
    }

}
