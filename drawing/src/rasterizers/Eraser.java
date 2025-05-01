package rasterizers;

import models.Line;
import rasters.Raster;

import java.awt.*;
import java.util.ArrayList;

public class Eraser implements Rasterizer {

    private Raster raster;
    //private int linewidth=10;

    public Eraser(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void rasterize(Line line, int linewidth) {
        linewidth=linewidth*5;
        int x = line.getPoint1().getX();
        int y = line.getPoint1().getY();
        for(int i=(-linewidth/2);i<(linewidth/2)+1;i++)
        {
            for(int o=(-linewidth/2);o<(linewidth/2)+1;o++)
            {
                if((x+o<raster.getWidth())&&(y+i<raster.getHeight())&&(x+o>0)&&(y+i>90)) {
                    raster.setPixel(x+o, y+i, Color.BLACK.getRGB());
                }
            }
        }
    }

    @Override
    public void rasterizeArray(Line line,int linewidths)
    {
        rasterize(line,linewidths);
    }
}