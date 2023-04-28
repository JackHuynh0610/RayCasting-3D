package com;

public abstract class CollisionObject implements Drawable {
    private double posnX;
    private double posnY;

    public CollisionObject(double posnX, double posnY){
        this.posnX = posnX;
        this.posnY = posnY;
    }

    //Get coordinate
    public double getPosnX(){
        return posnX;
    }

    public double getPosnY() {
        return posnY;
    }

    //set Coordinate
    public void setPosnX(double posnX) {
        this.posnX = posnX;
    }

    public void setPosnY(double posnY) {
        this.posnY = posnY;
    }


}
