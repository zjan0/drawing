package rasterizers;

import models.Line;
import models.Point;
import rasters.Raster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Bucket implements Rasterizer {

    private Raster raster;
    //private int linewidth=10;

    public Bucket(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void rasterize(Line line, int linewidth)
    {
        int x = line.getPoint1().getX();
        int y = line.getPoint1().getY();
        int bucketcolor=line.getColor().getRGB();
        int oldcolor= raster.getPixel(x,y);
        //int oldcolor= raster.getPixel(x,y);
        flood(x,y,oldcolor,bucketcolor);
    }
    public void flood(int x,int y,int oldcolor,int bucketcolor)
    {
        if((x<raster.getWidth())&&(y<raster.getHeight())&&(x>0)&&(y>90)) {}
        else{return;}
        if((raster.getPixel(x,y)==bucketcolor)||raster.getPixel(x,y)!=oldcolor){return;}
        raster.setPixel(x, y, bucketcolor);
        flood(x-1,y,oldcolor,bucketcolor);
        flood(x+1,y,oldcolor,bucketcolor);
        flood(x,y-1,oldcolor,bucketcolor);
        flood(x,y+1,oldcolor,bucketcolor);
    }

    @Override
    public void rasterizeArray(Line line,int linewidths)
    {
        rasterize(line,linewidths);
    }
}