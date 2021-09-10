package de.bentolor.java9to13samples;

import jdk.jfr.*;

@Name("de.bentolor.ButtonPressed")
@Label("Button Pressed")
@StackTrace(false)
public class ButtonEvent extends Event {
    @Label("Button name")
    public String name;

    @Label("Source")
    public String trigger;

    @Label("Number of Bounces")
    @DataAmount
    public int bounces;

    @Label("Has timeouted")
    public boolean timeouted;
}