package rasterizers;

import models.Line;

import java.awt.*;
import java.util.ArrayList;

public interface Rasterizer {

    void rasterize(Line line,int linewidth);

    void rasterizeArray(Line lines,int linewidth);

}