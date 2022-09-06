
class Rectangle{
    private double length, width;
    public Rectangle() {
        length = 0;
        width = 0;
    }
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double computeArea(){
        return this.length * this.width;
    }
}


class Circle{
    private double radius;
    public Circle() {
    }
    public Circle(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double computeArea(){
        return Math.PI*radius*radius;
    }
}

class Main{
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(5,4);
        Rectangle r2 = new Rectangle(8.1,5.7);
        System.out.println("Rectangle with sides "+r1.getWidth()+" and "+r1.getLength()+" has area "+r1.computeArea());
        System.out.println("Rectangle with sides "+r2.getWidth()+" and "+r2.getLength()+" has area "+r2.computeArea());
    
        Circle c1 = new Circle(4.2);
        Circle c2 = new Circle(3);
        System.out.println("Circle with radius "+c1.getRadius()+" has area "+c1.computeArea());
        System.out.println("Circle with radius "+c2.getRadius()+" has area "+c2.computeArea());
    }
}