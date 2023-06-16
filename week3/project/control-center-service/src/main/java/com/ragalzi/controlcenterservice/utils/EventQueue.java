package com.ragalzi.controlcenterservice.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ragalzi.controlcenterservice.models.FireSensorData;

@Component
public class EventQueue {

    private List<FireSensorData> eventList;

    public EventQueue() {
        eventList = new ArrayList<>();
    }

    public void addEvent(FireSensorData event) {
        eventList.add(event);
    }

    public FireSensorData removeEvent() {
        if (!eventList.isEmpty()) {
            return eventList.remove(0);
        }
        return null;
    }

    public FireSensorData peekEvent() {
        if (!eventList.isEmpty()) {
            return eventList.get(0);
        }
        return null;
    }

    public boolean containsEvent(FireSensorData event) {
        return eventList.contains(event);
    }

    public boolean isEmpty() {
        return eventList.isEmpty();
    }

    public int size() {
        return eventList.size();
    }

    public void clearEvents() {
        eventList.clear();
    }

    public List<FireSensorData> getAllEvents() {
        return new ArrayList<>(eventList);
    }
}
