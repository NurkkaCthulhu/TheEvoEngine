package com.anumalm.evoengine;

/**
 * PivotPoint is used when a person wants to set a new pivot point for their object.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1210
 * @since       2.0
 */
public enum PivotPoint {
    LOWERRIGHT(1, 1),
    TOPRIGHT(1, 0),
    CENTER(0.5f, 0.5f),
    TOPLEFT(0, 0),
    LOWERLEFT(0, 1),
    LOWERCENTER(0.5f, 1),
    TOPCENTER(0.5f, 0),
    LEFTCENTER(0, 0.5f),
    RIGHTCENTER(1, 0.5f)
    ;

    private final float xValue;
    private final float yValue;

    /**
     * Constructor for PivotPoint.
     * 
     * @param xValue        x-position value for the pivot point
     * @param yValue        y-position value for the pivot point
     */
    private PivotPoint (float xValue, float yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    /**
     * Returns the pivot point's x-value.
     * 
     * @return              pivot point's x-value
     */
    public float getxValue() {
        return this.xValue;
    }

    /**
     * Returns the pivot point's y-value.
     * 
     * @return              pivot point's y-value
     */
    public float getyValue() {
        return this.yValue;
    }
}