package models;

import java.util.ArrayList;

public class LineCanvas {
    private ArrayList<Line> lines;
    private ArrayList<Line> dottedlines;
    private ArrayList<Line> partlylines;
    private ArrayList<Line> circles;
    private ArrayList<Line> polygons;
    private ArrayList<Line> erase;
    private ArrayList<Line> bucket;
    private ArrayList<Line> brush;
    private ArrayList<Integer> lineswidth;
    private ArrayList<Integer> dottedlineswidth;
    private ArrayList<Integer> partlylineswidth;
    private ArrayList<Integer> circleswidth;
    private ArrayList<Integer> polygonswidth;
    private ArrayList<Integer> erasewidth;
    private ArrayList<Integer> bucketwidth;
    private ArrayList<Integer> brushwidth;
    private ArrayList<Integer> linesorder;
    private ArrayList<Integer> dottedlinesorder;
    private ArrayList<Integer> partlylinesorder;
    private ArrayList<Integer> circlesorder;
    private ArrayList<Integer> polygonsorder;
    private ArrayList<Integer> eraseorder;
    private ArrayList<Integer> bucketorder;
    private ArrayList<Integer> brushorder;
    private int linescount=0;
    private int dottedlinescount=0;
    private int partlylinescount=0;
    private int circlescount=0;
    private int polygonscount=0;
    private int erasecount=0;
    private int bucketcount=0;
    private int brushcount=0;
    private int totalcount=0;
    private int increasevalue=0;


    public LineCanvas() {
        this.lines = new ArrayList<>();
        this.dottedlines=new ArrayList<>();
        this.partlylines=new ArrayList<>();
        this.circles=new ArrayList<>();
        this.polygons=new ArrayList<>();
        this.erase=new ArrayList<>();
        this.bucket=new ArrayList<>();
        this.brush=new ArrayList<>();
        this.lineswidth = new ArrayList<>();
        this.dottedlineswidth=new ArrayList<>();
        this.partlylineswidth=new ArrayList<>();
        this.circleswidth=new ArrayList<>();
        this.polygonswidth=new ArrayList<>();
        this.erasewidth=new ArrayList<>();
        this.bucketwidth=new ArrayList<>();
        this.brushwidth=new ArrayList<>();
        this.linesorder=new ArrayList<>();
        this.dottedlinesorder=new ArrayList<>();
        this.partlylinesorder=new ArrayList<>();
        this.circlesorder=new ArrayList<>();
        this.polygonsorder=new ArrayList<>();
        this.eraseorder=new ArrayList<>();
        this.bucketorder=new ArrayList<>();
        this.brushorder=new ArrayList<>();
    }

    public void addLine(Line line,int width,int counter) {
        totalcount++;
        this.lines.add(line);
        this.lineswidth.add(width);
        this.linesorder.add(counter);
    }
    public void adddottedLine(Line line,int width,int counter)
    {
        totalcount++;
        this.dottedlines.add(line);
        this.dottedlineswidth.add(width);
        this.dottedlinesorder.add(counter);
    }
    public void addpartlyLine(Line line,int width,int counter)
    {
        totalcount++;
        this.partlylines.add(line);
        this.partlylineswidth.add(width);
        this.partlylinesorder.add(counter);
    }
    public void addCircles(Line line,int width,int counter)
    {
        totalcount++;
        this.circles.add(line);
        this.circleswidth.add(width);
        this.circlesorder.add(counter);
    }
    public void addPolygons(Line line,int width,int counter)
    {
        totalcount++;
        this.polygons.add(line);
        this.polygonswidth.add(width);
        this.polygonsorder.add(counter);
    }
    public void addEraser(Line line,int width,int counter)
    {
        totalcount++;
        this.erase.add(line);
        this.erasewidth.add(width);
        this.eraseorder.add(counter);
    }
    public void addBucket(Line line,int width,int counter)
    {
        totalcount++;
        this.bucket.add(line);
        this.bucketwidth.add(width);
        this.bucketorder.add(counter);
    }
    public void addBrush(Line line,int width,int counter)
    {
        totalcount++;
        this.brush.add(line);
        this.brushwidth.add(width);
        this.brushorder.add(counter);
    }
    public void clearLines() {
        this.lines.clear();
        this.dottedlines.clear();
        this.partlylines.clear();
        this.circles.clear();
        this.polygons.clear();
        this.erase.clear();
        this.bucket.clear();
        this.brush.clear();
        this.lineswidth.clear();
        this.dottedlineswidth.clear();
        this.partlylineswidth.clear();
        this.circleswidth.clear();
        this.polygonswidth.clear();
        this.erasewidth.clear();
        this.bucketwidth.clear();
        this.brushwidth.clear();
        this.linesorder.clear();
        this.dottedlinesorder.clear();
        this.partlylinesorder.clear();
        this.circlesorder.clear();
        this.polygonsorder.clear();
        this.eraseorder.clear();
        this.bucketorder.clear();
        this.brushorder.clear();
        increasevalue=0;
        totalcount=0;
        resetcount();
    }

    public Line getLines() {
        increasevalue=0;
        if((linesorder.get(linescount)<
                (dottedlinesorder.get(dottedlinescount)))
                &&(linesorder.get(linescount)<(partlylinesorder.get(partlylinescount)))
                &&(linesorder.get(linescount)<(circlesorder.get(circlescount)))&&
                (linesorder.get(linescount)<(polygonsorder.get(polygonscount)))&&
                (linesorder.get(linescount)<(eraseorder.get(erasecount)))
                &&(linesorder.get(linescount)<(bucketorder.get(bucketcount)))
                &&(linesorder.get(linescount)<(brushorder.get(brushcount))))
        {
            if(linescount+1<linesorder.size()) {
                linescount++;
                increasevalue++;
            }
            return (lines.get(linescount-increasevalue));
        }

        else{return null;}
    }
    public Line getDottedLines()
    {
        increasevalue=0;
        if((linesorder.get(linescount)>
                (dottedlinesorder.get(dottedlinescount)))
                &&(dottedlinesorder.get(dottedlinescount)<(partlylinesorder.get(partlylinescount)))&&
                (dottedlinesorder.get(dottedlinescount)<(circlesorder.get(circlescount)))&&
                (dottedlinesorder.get(dottedlinescount)<(polygonsorder.get(polygonscount)))&&
                (dottedlinesorder.get(dottedlinescount)<(eraseorder.get(erasecount)))
                &&(dottedlinesorder.get(dottedlinescount)<(bucketorder.get(bucketcount)))
                &&(dottedlinesorder.get(dottedlinescount)<(brushorder.get(brushcount))))
        {
            if(dottedlinescount+1<dottedlinesorder.size()) {
                dottedlinescount++;
                increasevalue++;
            }
            return (dottedlines.get(dottedlinescount-increasevalue));
        }
        else{return null;}
    }
    public Line getPartlyLines()
    {
        increasevalue=0;
        if((partlylinesorder.get(partlylinescount)<(dottedlinesorder.get(dottedlinescount)))&&
                (linesorder.get(linescount)>(partlylinesorder.get(partlylinescount)))&&
                (partlylinesorder.get(partlylinescount)<(circlesorder.get(circlescount)))&&
                (partlylinesorder.get(partlylinescount)<(polygonsorder.get(polygonscount)))&&
                (partlylinesorder.get(partlylinescount)<(eraseorder.get(erasecount)))
                &&(partlylinesorder.get(partlylinescount)<(bucketorder.get(bucketcount)))
                &&(partlylinesorder.get(partlylinescount)<(brushorder.get(brushcount))))
        {
            if(partlylinescount+1<partlylinesorder.size()) {
                partlylinescount++;
                increasevalue++;
            }
            return (partlylines.get(partlylinescount-increasevalue));
        }
        else{return null;}
    }
    public Line getCircles() {
        increasevalue=0;
        if((circlesorder.get(circlescount)<(dottedlinesorder.get(dottedlinescount)))&&
                (circlesorder.get(circlescount)<(partlylinesorder.get(partlylinescount)))&&
                (linesorder.get(linescount)>(circlesorder.get(circlescount)))&&
                (circlesorder.get(circlescount)<(polygonsorder.get(polygonscount)))&&
                (circlesorder.get(circlescount)<(eraseorder.get(erasecount)))
                &&(circlesorder.get(circlescount)<(bucketorder.get(bucketcount)))
                &&(circlesorder.get(circlescount)<(brushorder.get(brushcount))))
        {
            if(circlescount+1<circlesorder.size()) {
                circlescount++;
                increasevalue++;
            }
            return (circles.get(circlescount-increasevalue));
        }
        else{return null;}
    }
    public Line getPolygons() {
        increasevalue=0;
        if((polygonsorder.get(polygonscount)<(dottedlinesorder.get(dottedlinescount)))&&
                (polygonsorder.get(polygonscount)<(partlylinesorder.get(partlylinescount)))&&
                (polygonsorder.get(polygonscount)<(circlesorder.get(circlescount)))&&
                (linesorder.get(linescount)>(polygonsorder.get(polygonscount)))&&
                (polygonsorder.get(polygonscount)<(eraseorder.get(erasecount)))
                &&(polygonsorder.get(polygonscount)<(bucketorder.get(bucketcount)))
                &&(polygonsorder.get(polygonscount)<(brushorder.get(brushcount))))
        {
            if(polygonscount+1<polygonsorder.size()) {
                polygonscount++;
                increasevalue++;
            }
            return (polygons.get(polygonscount-increasevalue));
        }
        else{return null;}
    }
    public Line getErase()
    {
        increasevalue=0;
        if((eraseorder.get(erasecount)<(dottedlinesorder.get(dottedlinescount)))&&
                (eraseorder.get(erasecount)<(partlylinesorder.get(partlylinescount)))&&
                (eraseorder.get(erasecount)<(circlesorder.get(circlescount)))&&
                (eraseorder.get(erasecount)<(polygonsorder.get(polygonscount)))&&
                (linesorder.get(linescount)>(eraseorder.get(erasecount)))
                &&(eraseorder.get(erasecount)<(bucketorder.get(bucketcount)))
                &&(eraseorder.get(erasecount)<(brushorder.get(brushcount))))
        {
            if(erasecount+1<eraseorder.size()) {
                erasecount++;
                increasevalue++;
            }
            return (erase.get(erasecount-increasevalue));
        }
        else{return null;}
    }
    public Line getBuckets() {
        increasevalue=0;
        if((bucketorder.get(bucketcount)<(dottedlinesorder.get(dottedlinescount)))
                &&(bucketorder.get(bucketcount)<(partlylinesorder.get(partlylinescount)))
                &&(bucketorder.get(bucketcount)<(circlesorder.get(circlescount)))&&
                (bucketorder.get(bucketcount)<(polygonsorder.get(polygonscount)))&&
                (bucketorder.get(bucketcount)<(eraseorder.get(erasecount)))
                &&(linesorder.get(linescount)>(bucketorder.get(bucketcount)))
                &&(bucketorder.get(bucketcount)<(brushorder.get(brushcount))))
        {
            if(bucketcount+1<bucketorder.size()) {
                bucketcount++;
                increasevalue++;
            }
            return (bucket.get(bucketcount-increasevalue));
        }

        else{return null;}
    }
    public Line getBrush() {
        increasevalue=0;
        if((brushorder.get(brushcount)<(dottedlinesorder.get(dottedlinescount)))
                &&(brushorder.get(brushcount)<(partlylinesorder.get(partlylinescount)))
                &&(brushorder.get(brushcount)<(circlesorder.get(circlescount)))&&
                (brushorder.get(brushcount)<(polygonsorder.get(polygonscount)))&&
                (brushorder.get(brushcount)<(eraseorder.get(erasecount)))
                &&(brushorder.get(brushcount)<(bucketorder.get(bucketcount)))
                &&(linesorder.get(linescount)>(brushorder.get(brushcount))))
        {
            if(brushcount+1<brushorder.size()) {
                brushcount++;
                increasevalue++;
            }
            return (brush.get(brushcount-increasevalue));
        }

        else{return null;}
    }
    public int getLineswidth() {
        return (lineswidth.get(linescount-increasevalue));
    }
    public int getDottedLineswidth()
    {
        return (dottedlineswidth.get(dottedlinescount-increasevalue));
    }
    public int getPartlyLineswidth()
    {
        return (partlylineswidth.get(partlylinescount-increasevalue));
    }
    public int getCircleswidth() {
        return (circleswidth.get(circlescount-increasevalue));
    }
    public int getPolygonswidth() {
        return (polygonswidth.get(polygonscount-increasevalue));
    }
    public int getErasewidth()
    {
        return (erasewidth.get(erasecount-increasevalue));
    }
    public int getBucketwidth()
    {
        return (bucketwidth.get(bucketcount-increasevalue));
    }
    public int getBrushwidth() {
        return (brushwidth.get(brushcount-increasevalue));
    }
    public int getTotalCount()
    {
        return totalcount;
    }
    public void resetcount()
    {
        linescount=0;
        dottedlinescount=0;
        partlylinescount=0;
        circlescount=0;
        polygonscount=0;
        erasecount=0;
        bucketcount=0;
        brushcount=0;
    }
    public void nullcheck()
    {
        int counter=1000000000;
        linesorder.addLast(counter);
        dottedlinesorder.addLast(counter);
        partlylinesorder.addLast(counter);
        circlesorder.addLast(counter);
        polygonsorder.addLast(counter);
        eraseorder.addLast(counter);
        bucketorder.addLast(counter);
        brushorder.addLast(counter);
    }
    public void remover()
    {
        linesorder.removeLast();
        dottedlinesorder.removeLast();
        partlylinesorder.removeLast();
        circlesorder.removeLast();
        polygonsorder.removeLast();
        eraseorder.removeLast();
        bucketorder.removeLast();
        brushorder.removeLast();
    }
    public void linereducer()
    {
        linescount-=increasevalue;
    }
    public void dottedlinereducer()
    {
        dottedlinescount-=increasevalue;
    }
    public void partiallinereducer()
    {
        partlylinescount-=increasevalue;
    }
    public void polygonlinereducer()
    {
        polygonscount-=increasevalue;
    }
    public void circlelinereducer()
    {
        circlescount-=increasevalue;
    }
    public void eraserlinereducer()
    {
        erasecount-=increasevalue;
    }
    public void bucketlinereducer()
    {
        bucketcount-=increasevalue;
    }
    public void brushlinereducer()
    {
        brushcount-=increasevalue;
    }
    public boolean isallnull()
    {
        if((linesorder.isEmpty())&&dottedlinesorder.isEmpty()&&partlylinesorder.isEmpty()&& circlesorder.isEmpty()&&
                polygonsorder.isEmpty()&&eraseorder.isEmpty()&&bucketorder.isEmpty()&&brushorder.isEmpty())
        {
            return true;
        }
        return false;
    }
}