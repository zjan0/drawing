package rasterizers;

import models.Line;
import rasters.Raster;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TrivialLineRasterizer implements Rasterizer {

    private Raster raster;
    //private int linewidth;

    public TrivialLineRasterizer(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void rasterize(Line line,int linewidth) {
        int x1 = line.getPoint1().getX();
        int y1 = line.getPoint1().getY();
        int x2 = line.getPoint2().getX();
        int y2 = line.getPoint2().getY();

        float k = (float) (y2 - y1) / (x2 - x1);
        float q = y1 - (k * x1);

        if (Math.abs(k) < 1) {
            if (x1 > x2) {
                int x = x1;
                x1 = x2;
                x2 = x;
            }

            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                for(int i=(-linewidth/2);i<(linewidth/2)+1;i++)
                {
                    for(int o=(-linewidth/2);o<(linewidth/2)+1;o++)
                    {
                        if((x+o<raster.getWidth())&&(y+i<raster.getHeight())&&(x+o>0)&&(y+i>90)) {
                            raster.setPixel(x+o, y+i, line.getColor().getRGB());
                        }
                    }
                }
            }
        } else {
            if (y1 > y2) {
                int y = y1;
                y1 = y2;
                y2 = y;
            }

            for (int y = y1; y < y2; y++) {
                int x = Math.round((y - q) / k);
                for(int i=(-linewidth/2);i<(linewidth/2)+1;i++)
                {
                    for(int o=(-linewidth/2);o<(linewidth/2)+1;o++)
                    {
                        if((x+o<raster.getWidth())&&(y+i<raster.getHeight())&&(x+o>0)&&(y+i>90)) {
                            raster.setPixel(x+o, y+i, line.getColor().getRGB());
                        }
                    }
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