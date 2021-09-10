package de.bentolor.java9to13samples;

public class Test {
    public static void main(String[] args) {
        ButtonEvent evt = new ButtonEvent();
        if(evt.isEnabled()) {
            evt.name = "Button 1";
            evt.trigger = "Keyboard";
            evt.begin();
        }
        if(evt.isEnabled()) {
            evt.end();
            evt.timeouted = false;
            evt.bounces = 3;
            evt.commit();
        }
    }
}
