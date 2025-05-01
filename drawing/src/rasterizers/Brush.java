package rasterizers;

import models.Line;
import rasters.Raster;

import java.awt.*;
import java.util.ArrayList;

public class Brush implements Rasterizer {

    private Raster raster;
    //private int linewidth=10;

    public Brush(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void rasterize(Line line, int linewidth) {
        int x1 = line.getPoint1().getX();
        int y1 = line.getPoint1().getY();
        int x2 = line.getPoint2().getX();
        int y2 = line.getPoint2().getY();
        double radius=(-linewidth / 2);
        for (int i = (-linewidth / 2); i < (linewidth / 2) + 1; i++)
        {
            radius+=1;
            int cx = ((x2 - x1) / 2) + x1;
            int cy = ((y2 - y1) / 2) + y1;
            int x = 0;
            int y = (int) -radius;
            int p = (int) -radius;
            while (x < -y) {
                if (p > 0) {
                    y++;
                    p += 2 * (x + y) + 1;
                } else {
                    p += 2 * x + 1;
                }
                if ((cx + x < raster.getWidth()) && (cy + y < raster.getHeight()) && (cx + x > 0) && (cy + y > 90)) {
                    raster.setPixel(cx + x, cy + y, line.getColor().getRGB());
                }
                if ((cx + x < raster.getWidth()) && (cy - y < raster.getHeight()) && (cx + x > 0) && (cy - y > 90)) {
                    raster.setPixel(cx + x, cy - y, line.getColor().getRGB());
                }
                if ((cx - x < raster.getWidth()) && (cy + y < raster.getHeight()) && (cx - x > 0) && (cy + y > 90)) {
                    raster.setPixel(cx - x, cy + y, line.getColor().getRGB());
                }
                if ((cx - x < raster.getWidth()) && (cy - y < raster.getHeight()) && (cx - x > 0) && (cy - y > 90)) {
                    raster.setPixel(cx - x, cy - y, line.getColor().getRGB());
                }
                if ((cx + y < raster.getWidth()) && (cy + x < raster.getHeight()) && (cx + y > 0) && (cy + x > 90)) {
                    raster.setPixel(cx + y, cy + x, line.getColor().getRGB());
                }
                if ((cx + y < raster.getWidth()) && (cy - x < raster.getHeight()) && (cx + y > 0) && (cy - x > 90)) {
                    raster.setPixel(cx + y, cy - x, line.getColor().getRGB());
                }
                if ((cx - y < raster.getWidth()) && (cy + x < raster.getHeight()) && (cx - y > 0) && (cy + x > 90)) {
                    raster.setPixel(cx - y, cy + x, line.getColor().getRGB());
                }
                if ((cx - y < raster.getWidth()) && (cy - x < raster.getHeight()) && (cx - y > 0) && (cy - x > 90)) {
                    raster.setPixel(cx - y, cy - x, line.getColor().getRGB());
                }
                x++;
            }
        }
    }

    @Override
    public void rasterizeArray(Line line,int linewidths)
    {
        rasterize(line,linewidths);
    }
}