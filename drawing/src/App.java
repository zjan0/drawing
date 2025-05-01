import models.Line;
import models.LineCanvas;
import models.Point;
import rasterizers.*;
import rasters.Raster;
import rasters.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.ArrayList;

import static java.lang.Math.*;

public class App {
    //ButtonGroup lineOption = new ButtonGroup();//makes options to add buttons as subgroups
    private final JPanel panel;
    private final Raster raster;
    private MouseAdapter mouseAdapter;
    private KeyAdapter keyAdapter;
    private Point point;
    private Rasterizer rasterizer;
    private Rasterizer circles;
    private Rasterizer dotrasterizer;
    private Rasterizer partlyrasterizer;
    private LineCanvas canvas;
    private String typeofline="normal";
    private boolean straightline = false;
    private String shapes="line";
    private String mode="drawing";
    private int width=1000;
    private int height=750;
    private int widthofline=1;
    private Color color=Color.RED;
    private ArrayList<Point> polygonpoints=new ArrayList<>();
    private Rasterizer polygonrasterizer;
    private Rasterizer eraserasterizer;
    private Rasterizer bucketrasterizer;
    private Rasterizer brushrasterizer;
    private int addcount=0;
    private int countnow=0;
    private boolean newpolygon=false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().start());
    }

    public void clear(int color) {
        raster.setClearColor(color);
        raster.clear();
    }

    public void present(Graphics graphics) {
        raster.repaint(graphics);
    }

    public void start() {
        clear(0xaaaaaa);
        panel.repaint();
    }
    public App() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        Integer[] widths = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        JComboBox<Integer> linewidth=new JComboBox<>(widths);
        Button choosecolor=new Button("other colors");
        Button eraser=new Button("eraser");
        Button colornow=new Button();
        Button red=new Button();
        Button green=new Button();
        Button blue=new Button();
        Button yellow=new Button();
        Button cyan=new Button();
        Button magenta=new Button();
        Button white=new Button();
        Button black=new Button();
        Button gray=new Button();
        Button lightgray=new Button();
        Button darkgray=new Button();
        Button orange=new Button();
        Button pink=new Button();
        Button brush=new Button("brush");
        Button normalline=new Button("normal line");
        Button dottedline=new Button("dotted line");
        Button partialline=new Button("partialline");
        Button square=new Button("square");
        Button circle=new Button("circle");
        Button triangle=new Button("triangle");
        Button polygon=new Button("polygon");
        Button bucket=new Button("bucket");
        Button filler=new Button();
        eraser.setFocusable(false);
        normalline.setFocusable(false);
        dottedline.setFocusable(false);
        partialline.setFocusable(false);
        square.setFocusable(false);
        circle.setFocusable(false);
        triangle.setFocusable(false);
        polygon.setFocusable(false);
        bucket.setFocusable(false);
        red.setFocusable(false);
        green.setFocusable(false);
        blue.setFocusable(false);
        yellow.setFocusable(false);
        cyan.setFocusable(false);
        magenta.setFocusable(false);
        white.setFocusable(false);
        black.setFocusable(false);
        gray.setFocusable(false);
        lightgray.setFocusable(false);
        darkgray.setFocusable(false);
        orange.setFocusable(false);
        pink.setFocusable(false);
        brush.setFocusable(false);
        filler.setFocusable(false);
        linewidth.setFocusable(false);
        colornow.setFocusable(false);
        choosecolor.setFocusable(false);
        colornow.setBackground(color);
        filler.setBackground(Color.LIGHT_GRAY);
        red.setBackground(Color.red);
        green.setBackground(Color.green);
        blue.setBackground(Color.blue);
        yellow.setBackground(Color.yellow);
        cyan.setBackground(Color.cyan);
        magenta.setBackground(Color.magenta);
        white.setBackground(Color.white);
        black.setBackground(Color.black);
        gray.setBackground(Color.gray);
        lightgray.setBackground(Color.lightGray);
        darkgray.setBackground(Color.darkGray);
        orange.setBackground(Color.orange);
        pink.setBackground(Color.pink);
        choosecolor.addActionListener(e->{
            Color choosen=JColorChooser.showDialog(null,"Choose color",Color.BLACK);
            if(choosen!=null){color=choosen;colornow.setBackground(choosen);}
        });
        red.addActionListener(e->{color=Color.red;colornow.setBackground(Color.red);});
        green.addActionListener(e->{color=Color.green;colornow.setBackground(Color.green);});
        blue.addActionListener(e->{color=Color.blue;colornow.setBackground(Color.blue);});
        yellow.addActionListener(e->{color=Color.yellow;colornow.setBackground(Color.yellow);});
        cyan.addActionListener(e->{color=Color.cyan;colornow.setBackground(Color.cyan);});
        magenta.addActionListener(e->{color=Color.magenta;colornow.setBackground(Color.magenta);});
        white.addActionListener(e->{color=Color.white;colornow.setBackground(Color.white);});
        black.addActionListener(e->{color=Color.black;colornow.setBackground(Color.black);});
        gray.addActionListener(e->{color=Color.gray;colornow.setBackground(Color.gray);});
        lightgray.addActionListener(e->{color=Color.lightGray;colornow.setBackground(Color.lightGray);});
        darkgray.addActionListener(e->{color=Color.darkGray;colornow.setBackground(Color.darkGray);});
        orange.addActionListener(e->{color=Color.orange;colornow.setBackground(Color.orange);});
        pink.addActionListener(e->{color=Color.pink;colornow.setBackground(Color.pink);});
        linewidth.addItemListener(e -> {
            if(e.getStateChange()==ItemEvent.SELECTED) {
                widthofline=linewidth.getSelectedIndex();}});
        brush.addActionListener(e->mode="brush");
        polygon.addActionListener(e->{mode="polygon";newpolygon=true;});
        triangle.addActionListener(e -> {shapes="triangle";mode="drawing";newpolygon=true;});
        circle.addActionListener(e -> {shapes="circle";mode="drawing";newpolygon=true;});
        square.addActionListener(e -> {shapes="square";mode="drawing";newpolygon=true;});
        normalline.addActionListener(e -> {shapes="line";typeofline="normal";mode="drawing";newpolygon=true;});
        dottedline.addActionListener(e -> {shapes="line";typeofline="dotted";mode="drawing";newpolygon=true;});
        partialline.addActionListener(e -> {shapes="line";typeofline="partial";mode="drawing";newpolygon=true;});
        eraser.addActionListener(e->mode="eraser");
        bucket.addActionListener(e->mode="bucket");
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferedImage(width, height);
        panel = new JPanel() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(null);
        eraser.setBounds(30,10,60,20);
        bucket.setBounds(100,10,60,20);
        normalline.setBounds(170,10,60,20);
        dottedline.setBounds(170,35,60,20);
        partialline.setBounds(170,60,60,20);
        circle.setBounds(240,10,60,20);
        triangle.setBounds(310,10,60,20);
        square.setBounds(380,10,60,20);
        polygon.setBounds(450,10,60,20);
        colornow.setBounds(630,10,40,40);
        red.setBounds(680,10,20,20);
        green.setBounds(680,35,20,20);
        blue.setBounds(680,60,20,20);
        yellow.setBounds(710,10,20,20);
        cyan.setBounds(710,35,20,20);
        magenta.setBounds(710,60,20,20);
        white.setBounds(740,10,20,20);
        black.setBounds(740,35,20,20);
        gray.setBounds(740,60,20,20);
        lightgray.setBounds(770,10,20,20);
        darkgray.setBounds(770,35,20,20);
        orange.setBounds(770,60,20,20);
        pink.setBounds(800,10,20,20);
        brush.setBounds(30,35,60,20);
        choosecolor.setBounds(800,35,70,20);
        filler.setBounds(0,0,raster.getWidth(),90);
        linewidth.setBounds(520,10,100,20);
        panel.add(choosecolor);
        panel.add(colornow);
        panel.add(red);
        panel.add(green);
        panel.add(blue);
        panel.add(yellow);
        panel.add(cyan);
        panel.add(magenta);
        panel.add(white);
        panel.add(black);
        panel.add(gray);
        panel.add(lightgray);
        panel.add(darkgray);
        panel.add(orange);
        panel.add(pink);
        panel.add(eraser);
        panel.add(normalline);
        panel.add(dottedline);
        panel.add(partialline);
        panel.add(square);
        panel.add(circle);
        panel.add(triangle);
        panel.add(polygon);
        panel.add(bucket);
        panel.add(brush);
        panel.add(linewidth);
        panel.add(filler);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        rasterizer = new TrivialLineRasterizer(raster);
        dotrasterizer=new DottedLineRasterizer(raster);
        partlyrasterizer=new PartlyLineRasterizer(raster);
        circles=new Circle(raster);
        polygonrasterizer=new PolygonRasterizer(raster);
        eraserasterizer=new Eraser(raster);
        bucketrasterizer=new Bucket(raster);
        brushrasterizer=new Brush(raster);

        canvas = new LineCanvas();

        createAdapters();

        panel.addMouseListener(mouseAdapter);
        panel.addKeyListener(keyAdapter);
        panel.addMouseMotionListener(mouseAdapter);

        panel.requestFocus();
        panel.requestFocusInWindow();
    }

    private void createAdapters() {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    point = new Point(e.getX(), e.getY());
                    if(newpolygon==true)
                    {
                        if(polygonpoints.size()>1) {
                            for(int i=0;i<polygonpoints.size()-1;i++) {
                                Line line = new Line(polygonpoints.get(i), polygonpoints.get(i+1), color);
                                addcount++;
                                canvas.addPolygons(line,widthofline,addcount);
                            }
                            Line line = new Line(polygonpoints.get(0), polygonpoints.get(polygonpoints.size()-1), color);
                            addcount++;
                            canvas.addPolygons(line,widthofline,addcount);
                        }
                        polygonpoints.clear();
                        newpolygon=false;
                    }
                    if(mode=="polygon")
                    {
                        raster.clear();
                        polygonpoints.add(point);
                    }
                    else if(mode=="eraser")
                    {
                        Line line = new Line(point,point, color);
                        addcount++;
                        canvas.addEraser(line,widthofline,addcount);
                    }
                    else if(mode=="bucket")
                    {
                        Line line = new Line(point,point, color);
                        addcount++;
                        canvas.addBucket(line,widthofline,addcount);
                    }
                    else if(mode=="brush")
                    {
                        Line line=new Line(point,point,color);
                        addcount++;
                        canvas.addBrush(line,widthofline,addcount);
                    }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(mode=="drawing") {
                    Point point2 = new Point(e.getX(), e.getY());
                    int x=e.getX();
                    int y=e.getY();
                    if((straightline==true)||(shapes=="circle"))
                    {
                        if (((point.getX() - e.getX()) * (point.getY() - e.getY())) < 0) {
                            if ((abs(e.getX() - point.getX())) > (abs(e.getY() - point.getY()))) {
                                x=e.getX();
                                y=point.getX() - e.getX() + point.getY();
                                point2 = new Point(e.getX(), point.getX() - e.getX() + point.getY());

                            } else {
                                x=point.getY() - e.getY() + point.getX();
                                y=e.getY();
                                point2 = new Point(point.getY() - e.getY() + point.getX(), e.getY());
                            }
                        } else {
                            if ((abs(e.getX() - point.getX())) > (abs(e.getY() - point.getY()))) {
                                x=e.getX();
                                y=e.getX() - point.getX() + point.getY();
                                point2 = new Point(e.getX(), e.getX() - point.getX() + point.getY());

                            } else {
                                x=e.getY() - point.getY() + point.getX();
                                y=e.getY();
                                point2 = new Point(e.getY() - point.getY() + point.getX(), e.getY());
                            }
                        }
                    }
                    raster.clear();
                    if (shapes == "square") {
                        point2 = new Point(x, point.getY());
                        Line line = new Line(point, point2, color);
                        addcount++;
                        canvas.addLine(line,widthofline,addcount);
                        point2 = new Point(point.getX() + 1, y);
                        line = new Line(new Point(point.getX(), point.getY()), point2, color);
                        addcount++;
                        canvas.addLine(line,widthofline,addcount);

                        point2 = new Point(x, y);
                        line = new Line(new Point(point.getX(), y), point2, color);
                        addcount++;
                        canvas.addLine(line,widthofline,addcount);
                        point2 = new Point(x + 1, y);
                        line = new Line(new Point(x, point.getY()), point2, color);
                        addcount++;
                        canvas.addLine(line,widthofline,addcount);
                    } else if (shapes == "triangle") {
                        point2 = new Point(e.getX(), e.getY());
                        Line line = new Line(new Point(point.getX(), e.getY()), point2, color);
                        if(e.getY()<point.getY())
                        {
                            line = new Line(point, new Point(x, point.getY()), color);
                        }
                        addcount++;
                        canvas.addLine(line,widthofline,addcount);
                        point2 = new Point(((e.getX() - point.getX()) / 2) + point.getX(), point.getY());
                        line = new Line(new Point(point.getX(), e.getY()), point2, color);
                        if(e.getY()<point.getY())
                        {
                            point2 = new Point(((x - point.getX()) / 2) + point.getX(), y);
                            line = new Line(point, point2, color);
                        }
                        addcount++;
                        canvas.addLine(line,widthofline,addcount);
                        point2 = new Point(((e.getX() - point.getX()) / 2) + point.getX(), point.getY());
                        line = new Line(new Point(e.getX(), e.getY()), point2, color);
                        if(e.getY()<point.getY())
                        {
                            point2 = new Point(((x - point.getX()) / 2) + point.getX(), y);
                            line = new Line(new Point(x, point.getY()), point2, color);
                        }
                        addcount++;
                        canvas.addLine(line,widthofline,addcount);
                    } else if (shapes == "circle") {
                        point2 = new Point(x, y);
                        Line line = new Line(point, point2, color);
                        addcount++;
                        canvas.addCircles(line,widthofline,addcount);
                    } else {
                        if (straightline == true) {
                            int dx = abs(point.getX() - e.getX());
                            int dy = abs(point.getY() - e.getY());
                            double degrees = 0;
                            if ((dx != 0) && (dx < dy)) {
                                degrees = Math.toDegrees(Math.atan(dy / dx));
                            } else if ((dy != 0) && (dx > dy)) {
                                degrees = Math.toDegrees(Math.atan(dx / dy));
                            }
                            if ((dx > dy) && (degrees != 45)) {
                                point2 = new Point(e.getX(), point.getY());
                            } else if ((dx < dy) && (degrees != 45)) {
                                point2 = new Point(point.getX() + 1, e.getY());
                            }
                        }
                        Line line = new Line(point, point2, color);
                        if (typeofline == "dotted") {
                            addcount++;
                            canvas.adddottedLine(line,widthofline,addcount);
                        } else if (typeofline == "partial") {
                            addcount++;
                            canvas.addpartlyLine(line,widthofline,addcount);
                        } else {
                            addcount++;
                            canvas.addLine(line,widthofline,addcount);
                        }
                    }
                }
                if(canvas.isallnull()==false) {
                    countnow = 0;
                    canvas.resetcount();
                    canvas.nullcheck();
                    while (countnow < canvas.getTotalCount()) {
                        if ((canvas.getLines() != null)) {
                            canvas.linereducer();
                            countnow++;
                            rasterizer.rasterizeArray(canvas.getLines(), canvas.getLineswidth());
                        }
                        if ((canvas.getDottedLines() != null)) {
                            canvas.dottedlinereducer();
                            countnow++;
                            dotrasterizer.rasterizeArray(canvas.getDottedLines(), canvas.getDottedLineswidth());
                        }
                        if ((canvas.getPartlyLines() != null)) {
                            canvas.partiallinereducer();
                            countnow++;
                            partlyrasterizer.rasterizeArray(canvas.getPartlyLines(), canvas.getPartlyLineswidth());
                        }
                        if ((canvas.getCircles() != null)) {
                            canvas.circlelinereducer();
                            countnow++;
                            circles.rasterizeArray(canvas.getCircles(), canvas.getCircleswidth());
                        }
                        if ((canvas.getPolygons() != null)) {
                            canvas.polygonlinereducer();
                            countnow++;
                            polygonrasterizer.rasterizeArray(canvas.getPolygons(), canvas.getPolygonswidth());
                        }
                        if ((canvas.getErase() != null)) {
                            canvas.eraserlinereducer();
                            countnow++;
                            eraserasterizer.rasterizeArray(canvas.getErase(), canvas.getErasewidth());
                        }
                        if ((canvas.getBuckets() != null)) {
                            canvas.bucketlinereducer();
                            countnow++;
                            bucketrasterizer.rasterizeArray(canvas.getBuckets(), canvas.getBucketwidth());
                        }
                        if ((canvas.getBrush() != null)) {
                            canvas.brushlinereducer();
                            countnow++;
                            brushrasterizer.rasterizeArray(canvas.getBrush(), canvas.getBrushwidth());
                        }
                    }
                    canvas.remover();
                }
                if(polygonpoints.size()>1) {
                    for(int i=0;i<polygonpoints.size()-1;i++) {
                        Line line = new Line(polygonpoints.get(i), polygonpoints.get(i+1), color);
                        polygonrasterizer.rasterize(line, widthofline);
                    }
                    Line line = new Line(polygonpoints.get(0), polygonpoints.get(polygonpoints.size()-1), color);
                    polygonrasterizer.rasterize(line, widthofline);
                }
                panel.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                raster.clear();
                if(canvas.isallnull()==false) {
                    countnow = 0;
                    canvas.resetcount();
                    canvas.nullcheck();
                    while (countnow < canvas.getTotalCount()) {
                        if ((canvas.getLines() != null)) {
                            canvas.linereducer();
                            countnow++;
                            rasterizer.rasterizeArray(canvas.getLines(), canvas.getLineswidth());
                        }
                        if ((canvas.getDottedLines() != null)) {
                            canvas.dottedlinereducer();
                            countnow++;
                            dotrasterizer.rasterizeArray(canvas.getDottedLines(), canvas.getDottedLineswidth());
                        }
                        if ((canvas.getPartlyLines() != null)) {
                            canvas.partiallinereducer();
                            countnow++;
                            partlyrasterizer.rasterizeArray(canvas.getPartlyLines(), canvas.getPartlyLineswidth());
                        }
                        if ((canvas.getCircles() != null)) {
                            canvas.circlelinereducer();
                            countnow++;
                            circles.rasterizeArray(canvas.getCircles(), canvas.getCircleswidth());
                        }
                        if ((canvas.getPolygons() != null)) {
                            canvas.polygonlinereducer();
                            countnow++;
                            polygonrasterizer.rasterizeArray(canvas.getPolygons(), canvas.getPolygonswidth());
                        }
                        if ((canvas.getErase() != null)) {
                            canvas.eraserlinereducer();
                            countnow++;
                            eraserasterizer.rasterizeArray(canvas.getErase(), canvas.getErasewidth());
                        }
                        if ((canvas.getBuckets() != null)) {
                            canvas.bucketlinereducer();
                            countnow++;
                            bucketrasterizer.rasterizeArray(canvas.getBuckets(), canvas.getBucketwidth());
                        }
                        if ((canvas.getBrush() != null)) {
                            canvas.brushlinereducer();
                            countnow++;
                            brushrasterizer.rasterizeArray(canvas.getBrush(), canvas.getBrushwidth());
                        }
                    }
                    canvas.remover();
                }
                if(mode=="eraser")
                {
                    Point point2 = new Point(e.getX(), e.getY());
                    Line line = new Line(point2,point2, color);
                    addcount++;
                    canvas.addEraser(line,widthofline,addcount);
                }
                if(mode=="brush")
                {
                    Point point2 = new Point(e.getX(), e.getY());
                    Line line = new Line(point2,point2, color);
                    addcount++;
                    canvas.addBrush(line,widthofline,addcount);
                }
                else if(mode=="drawing"){
                    Point point2 = new Point(e.getX(), e.getY());
                    int x=e.getX();
                    int y=e.getY();
                    if((straightline==true)||(shapes=="circle"))
                    {
                        if (((point.getX() - e.getX()) * (point.getY() - e.getY())) < 0) {
                            if ((abs(e.getX() - point.getX())) > (abs(e.getY() - point.getY()))) {
                                x=e.getX();
                                y=point.getX() - e.getX() + point.getY();
                                point2 = new Point(e.getX(), point.getX() - e.getX() + point.getY());

                            } else {
                                x=point.getY() - e.getY() + point.getX();
                                y=e.getY();
                                point2 = new Point(point.getY() - e.getY() + point.getX(), e.getY());
                            }
                        } else {
                            if ((abs(e.getX() - point.getX())) > (abs(e.getY() - point.getY()))) {
                                x=e.getX();
                                y=e.getX() - point.getX() + point.getY();
                                point2 = new Point(e.getX(), e.getX() - point.getX() + point.getY());

                            } else {
                                x=e.getY() - point.getY() + point.getX();
                                y=e.getY();
                                point2 = new Point(e.getY() - point.getY() + point.getX(), e.getY());
                            }
                        }
                    }

                    if (shapes == "square") {
                        point2 = new Point(x, point.getY());
                        Line line = new Line(point, point2, color);
                        rasterizer.rasterize(line, widthofline);
                        point2 = new Point(point.getX() + 1, y);
                        line = new Line(new Point(point.getX(), point.getY()), point2, color);
                        rasterizer.rasterize(line, widthofline);

                        point2 = new Point(x, y);
                        line = new Line(new Point(point.getX(), y), point2, color);
                        rasterizer.rasterize(line, widthofline);
                        point2 = new Point(x + 1, y);
                        line = new Line(new Point(x, point.getY()), point2, color);
                        rasterizer.rasterize(line, widthofline);
                    } else if (shapes == "triangle") {
                        point2 = new Point(x, y);
                        Line line = new Line(new Point(point.getX(), y), point2, color);
                        if(e.getY()<point.getY())
                        {
                            line = new Line(point, new Point(x, point.getY()), color);
                        }
                        rasterizer.rasterize(line, widthofline);
                        point2 = new Point(((x - point.getX()) / 2) + point.getX(), point.getY());
                        line = new Line(new Point(point.getX(), y), point2, color);
                        if(e.getY()<point.getY())
                        {
                            point2 = new Point(((x - point.getX()) / 2) + point.getX(), y);
                            line = new Line(point, point2, color);
                        }
                        rasterizer.rasterize(line, widthofline);
                        point2 = new Point(((x - point.getX()) / 2) + point.getX(), point.getY());
                        line = new Line(new Point(x, y), point2, color);
                        if(e.getY()<point.getY())
                        {
                            point2 = new Point(((x - point.getX()) / 2) + point.getX(), y);
                            line = new Line(new Point(x, point.getY()), point2, color);
                        }
                        rasterizer.rasterize(line, widthofline);
                    } else if (shapes == "circle") {
                        point2 = new Point(x, y);
                        Line line = new Line(point, point2, color);
                        circles.rasterize(line, widthofline);
                    } else {
                        if (straightline == true) {
                            int dx = abs(point.getX() - e.getX());
                            int dy = abs(point.getY() - e.getY());
                            double degrees = 0;
                            if ((dx != 0) && (dx < dy)) {
                                degrees = Math.toDegrees(Math.atan(dy / dx));
                            } else if ((dy != 0) && (dx > dy)) {
                                degrees = Math.toDegrees(Math.atan(dx / dy));
                            }
                            if ((dx > dy) && (degrees != 45)) {
                                point2 = new Point(e.getX(), point.getY());
                            } else if ((dx < dy) && (degrees != 45)) {
                                point2 = new Point(point.getX() + 1, e.getY());
                            }
                        }
                        Line line = new Line(point, point2, color);
                        if (typeofline == "dotted") {
                            dotrasterizer.rasterize(line, widthofline);
                        } else if (typeofline == "partial") {
                            partlyrasterizer.rasterize(line, widthofline);
                        } else {
                            rasterizer.rasterize(line, widthofline);
                        }
                    }
                }

                panel.repaint();
            }
        };
        keyAdapter = new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_E)
                {
                    polygonpoints.clear();
                    canvas.clearLines();
                    raster.clear();
                    panel.repaint();
                    addcount=0;
                }
                if(e.getKeyCode()==KeyEvent.VK_SHIFT)
                {
                    straightline=true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_SHIFT)
                {
                    straightline=false;
                }
            }
        };
    }

}
