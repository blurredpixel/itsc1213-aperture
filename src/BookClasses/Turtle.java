package BookClasses;

import java.awt.*;

/**
 * Class that represents a turtle which is similar to a Logo turtle.
 * This class inherits from SimpleTurtle and is for students
 * to add methods to.
 *
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class Turtle extends SimpleTurtle
{
  ////////////////// constructors ///////////////////////
  
  /** Constructor that takes the x and y and a picture to
   * draw on
   * @param x the starting x position
   * @param y the starting y position
   * @param picture the picture to draw on
   */
  private World w;
  private Picture pic;
  private Picture original;
  
  
  public Turtle (int x, int y, Picture picture) 
  {
    // let the parent constructor handle it
    super(x,y,picture);
    pic = picture;
  }
  
  /** Constructor that takes the x and y and a model
   * display to draw it on
   * @param x the starting x position
   * @param y the starting y position
   * @param modelDisplayer the thing that displays the model
   */
  public Turtle (int x, int y, 
                 ModelDisplay modelDisplayer) 
  {
    // let the parent constructor handle it
    super(x,y,modelDisplayer);
    w = (World)modelDisplayer;
  }
  
  /** Constructor that takes the model display
   * @param modelDisplay the thing that displays the model
   */
  public Turtle (ModelDisplay modelDisplay) 
  {
    // let the parent constructor handle it
    super(modelDisplay);
    //w = (World)modelDisplayer;
  }
  
  /**
   * Constructor that takes a picture to draw on
   * @param p the picture to draw on
   */
  public Turtle (Picture p)
  {
    // let the parent constructor handle it
    super(p);
    pic = p;
    
  }  
  

  
  /////////////////// methods //////////////////////
  public void drawT(Color c) { 
    // assumes we begin facing up (North) with penUp, 
    // and that we are at the bottom-left of where the letter is supposed to appear
    
    // first move over while pen is up
    this.turnRight();
    this.forward(40);
    // now put pen down, set color
    this.setPenColor(c);
    this.penDown();
    // draw vertical part of T
    this.turnLeft();
    this.forward(100); 
    
    this.turnLeft(); 
    this.penUp(); 
    this.forward(40); 
    this.turn(180); 
    this.penDown(); 
    this.forward(80); 
    this.penUp();
    
    // move to bottom righ corner
    this.turnRight();
    this.forward(100);
    this.turnLeft();
    
    // move to next letter position`
    this.forward(10);
    this.turnLeft();  
  }
  
   public void drawA(Color c) {
    // assumes we begin facing up (North) with penUp, 
    // and that we are at the bottom-left of where the letter is supposed to appear
    this.penDown();
    this.setPenColor(c);
    this.turn(30);
    this.forward(100);
    this.turn(125);
    this.forward(100);
    this.turn(180);
    this.forward(40);
    this.turn(-65);
    this.forward(62);
    // ends by picking the penUp
    this.penUp();
    // move to bottom-left of character
    this.turn(180);
    this.forward(60);
    this.turn(90);
    this.forward(40);
    this.turn(-90);
    
    // move to next letter position
    this.forward(10);
    this.turnLeft();
  }
   
  /////////////////// methods //////////////////////
  public void drawQuestionMark(Color c) { 
    // assumes we begin facing up (North) with penUp, 
    // and that we are at the bottom-left of where the letter is supposed to appear
    
    // first move over while pen is up
    this.turnRight();
    this.forward(38);
    // now put pen down, set color
    this.setPenColor(c);
    this.penDown();
    // draw vertical part of bottom of question mark
    this.forward(4);
    this.turnLeft();
    this.forward(4);
    this.turnLeft();
    this.forward(4);
    this.turnLeft();
    this.forward(4);
    this.turn(180);
    
    // move up a bit
    this.penUp(); 
    this.forward(10); 

    // draw stick below curve
    this.penDown(); 
    this.forward(20); 
    
    // draw round part of question mark
    int angle = -40;
    int dist = 15;
    this.turnRight();
    this.forward(5);
    this.turn(angle);
    this.forward(dist);
    this.turn(angle);
    this.forward(dist);
    this.turn(angle);
    this.forward(dist);
    this.turn(angle);
    this.forward(dist);
    this.turn(angle);
    this.forward(dist);
   

    // move to bottom right of letter area
    this.penUp();
    this.turnLeft();
    this.forward(40);
    this.turn(20);
    this.forward(30);
    
    // move to next letter position`
    this.turnLeft();
    this.forward(35);
    this.turnLeft();  
    
  } // end of question mark method
  
  
  public void drawExclamationMark(Color c) { 
    // assumes we begin facing up (North) with penUp, 
    // and that we are at the bottom-left of where the letter is supposed to appear
    
    // first move over while pen is up
    this.turnRight();
    this.forward(38);
    // now put pen down, set color
    this.setPenColor(c);
    this.penDown();
    // draw bottom of exclamation mark
    this.forward(4);
    this.turnLeft();
    this.forward(4);
    this.turnLeft();
    this.forward(4);
    this.turnLeft();
    this.forward(4);
    this.turn(180);
    
    // move up a bit
    this.penUp(); 
    this.forward(10); 
    // move right a bit
    this.turnRight();
    this.forward(2);
    this.turnLeft();

    // draw top of exclamation mrk
    this.penDown(); 
    this.forward(65); 
    
    // move to bottom right of letter area
    this.penUp();
    this.turn(180);
    this.forward(75);
    this.turnLeft();
    this.forward(25);
    
    // move to next letter position`
    //this.turnLeft();
    this.forward(35);
    this.turnLeft();  
    
  } // end of exclamation mark method
  
  public void drawTicTacToeGrid() {
    // assume turtle is facting up
    // draw from bottom left, up
    // vertical lines first
    this.forward(100);
    this.turn(90);
    this.penUp();
    this.forward(33);
    this.turn(90);
    this.penDown();
    this.forward(100);
    
    // move to middle, right
    this.penUp();
    this.turn(-90);
    this.forward(33);
    this.turn(-90);
    this.forward(33);
    this.turn(-90);
    this.penDown();
    
    // horizontal lines
    this.forward(100);
    this.penUp();
    this.turn(90);
    this.forward(33);
    this.turn(90);
    this.penDown();
    this.forward(100);
    
  }
  
  public boolean isTurtleClose(Turtle otherTurtle, int threshold) {
   // checks if otherTurtle is within threshold pixels 
    int otherX;
    int otherY;
    double dist;
    
    if (threshold < 0) {
      System.out.println("Error: threshold is negative.");
      return false;
    }
    
    otherX = otherTurtle.getXPos();
    otherY = otherTurtle.getYPos();
    
    dist = Math.sqrt(((this.getXPos() - otherX)*(this.getXPos() - otherX)) + 
                     ((this.getYPos() - otherY)*(this.getYPos() - otherY)));
    
    if ((int)dist < threshold) {
      return true;
    } else {
      return false;
    }
  }
  
  public int getDistance() {
    double returnVal;
    returnVal = Math.sqrt((this.getXPos()*this.getXPos()) + (this.getYPos()*this.getYPos()));
    return (int)returnVal;
  }
  
  public boolean isOverTargetArea(int x, int y, int width, int height) {
    if (this.getXPos() > x && this.getXPos() < x + width && this.getYPos() > y && this.getYPos() < y + height) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isTurtleNamed() {
    if (this.getName() == "No name") {
      return false;
    } else {
      return true;
    }
  } 
  
  
  public static String helpGetters() {
    return "Common getters: isPenDown(), getPenColor(), getXPos(), getYPos()";
  }
  
  public static String helpSetters() {
    return "Common setters: setPenColor(Color c), penDown(), penUp(), setHeading(int direction)";
  }
  
  public static void help() {
    System.out.println(helpGetters());
    System.out.println(helpSetters());
  }
  
  public void initInteraction(World world, Picture p) {
    w = world;
    pic = p;
    // create new pic to store this pic, for changes
    original = new Picture(p);
      
  }
 /* 
  public void increase(int amount) {
    this.forward(amount);
    double percent = this.getYPos()/w.getHeight();
    pic.decreaseBlue(percent);
    w.modelChanged();
  }
  */
  public void drawHexagon(int sideLength) {
    if (sideLength < 0) {
     System.out.println("Error: negative sideLength passed into drawHexagon");
     return;
    }
    // drawHexagon
    this.forward(sideLength);
    this.turn(60);
    this.forward(sideLength);
    this.turn(60);
    this.forward(sideLength);
    this.turn(60);
    this.forward(sideLength);
    this.turn(60);
    this.forward(sideLength);
    this.turn(60);
    this.forward(sideLength);
    this.turn(60);
  }
  
  public void dance(Color c) {
    this.penDown();
    this.setPenColor(c);
    for (int i= 0; i < 7; i++) {
      this.turn(i*15);
      this.forward((int)(Math.random()*50));
    } 
    this.penUp();
  }
  
  
    // define a method to draw a hexagon of variable line lengths
    public void drawHexagonOfLength(int length)
    {
      if (  length <= 0 ) {
         // length is not a positive int, so print error and return
         System.out.println("Error: length passed in is not a positive integer.");
         return;
      }
      
      /* Make turtle draw a hexagon */
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
    }
  
      
    // define a method to draw a hexagon of variable line length, 
    // and variable pen width
    public void drawHexagonOfLengthPen(int length, int penWidth)
    {
      /* Make turtle draw a hexagon */
      setPenWidth(penWidth);
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
      turn(60);
      forward(length);
    }
    
    public void drawSquare(int width) {
      // assumes in the right place and pen down
      for (int i = 0; i < 4; i++) {
        forward(width);
        turnRight();
      }
    }
    
    /**
     * Method that causes turtle to draw a thickly outlined square 
     * @param width integer that specifies the length of each side
     * @param orientation integer that specifies starting heading of turtle
     */
    public void drawBoldSquare(int width, int orientation, int thickness) {
        this.setPenWidth(thickness);
        this.setHeading(orientation);
        for (int i = 0; i < 4; i++) {
            this.forward(width);
            this.turnRight();
        }
    }
    
    
    /**
     * 
     * @param width integer that specifies length of the sides
     * @param orientation integer that specifies starting heading of the turtle
     */
      public void drawBoldSquare(int width, int orientation){
           
       
       
       this.setPenWidth(5);
       this.setHeading(orientation);
       for (int i=0; i < 4; i++){
           this.forward(width);
           this.turnRight();
       }
   }
} // this } is the end of class Turtle, put all new methods before this