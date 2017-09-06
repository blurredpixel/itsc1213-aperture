package BookClasses;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
 
  public static void main(String[] args) 
  {
     String fileName = FileChooser.pickAFile();
     Picture pic = new Picture(fileName);
     pic.show();
  }
  
  public void averagePixelsHorizontally() {
    /* Assignment 3, Part C */
    /* Precondition: assumes picture object created */
    /* Post-condition: picture stored in pixelArray will be slightly blurred, but must call show() or explore() to see */
    /* returns: nothing */
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    Pixel prev_pixel = null;
    Pixel next_pixel = null;
    int index = 0;
    int red;
    int green;
    int blue;
    
    
    while (index < pixelArray.length) {
      if (index == 0) {
        // special case, first pixel, no previous pixel to average
        System.out.println("special case, first pixel");
        
      } else if (index == pixelArray.length - 1) {
        // special case, last pixel, no next_pixel to average
        
        System.out.println("special case, last pixel");
      
      } else {
        //System.out.println("standard case");
        // standard case
        
         prev_pixel = pixelArray[index - 1];
         pixel = pixelArray[index];
         next_pixel = pixelArray[index + 1];
         
         
         // red pixel averaging
         red = (prev_pixel.getRed() + pixel.getRed() + next_pixel.getRed())/3;
         pixel.setRed(red);
         // green pixel averaging
         green = (prev_pixel.getGreen() + pixel.getGreen() + next_pixel.getGreen())/3;
         pixel.setGreen(green);
         // blue pixel averaging
         blue = (prev_pixel.getBlue() + pixel.getBlue() + next_pixel.getBlue())/3;
         pixel.setBlue(blue);
         
      } // end else
      index++;
    } // end while
  }// end method
  
  public void decreaseBlue(double percentage) {
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    int index = 0;
    
    if (percentage < 0.0) {
      System.out.println("Error! Parameter less than zero.");
      return;
    }
    
    if (percentage > 1.0) {
      System.out.println("Error! Parameter greater than one.");
      return;
    }
    
    // loop through all the pixels
    while (index < pixelArray.length) {
      // get the current pixel
      pixel = pixelArray[index];
      
      // get the blue value
      value = pixel.getBlue();
      
      // decrease by the passed in percentage
      value = (int) (value * percentage);
      
      // set the blue value of the current pixel to the new value
      pixel.setBlue(value);
      
      // increment the index
      index = index + 1;
      
    }
  }
  
  public void modifyGreen(double percentage, double portion) {
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    int index = 0;
    
    if (percentage < 0.0) {
      System.out.println("Error! Parameter less than zero.");
      return;
    }
    
    if (percentage > 2.0) {
      System.out.println("Error! Parameter greater than two.");
      return;
    }
    
    if (portion < 0.0) {
      System.out.println("Error! Portion less than zero.");
      return;
    }
    
    if (portion > 1.0) {
      System.out.println("Error! Portion greater than one.");
      return;
    }
    
    // loop through all the pixels
    while (index < pixelArray.length*portion) {
      // get the current pixel
      pixel = pixelArray[index];
      
      // get the blue value
      value = pixel.getGreen();
      
      // modify by the passed in percentage
      value = (int) (value * percentage);
      
      // set the blue value of the current pixel to the new value
      pixel.setGreen(value);
      
      // increment the index
      index = index + 1;
      
    }
  }
  
  public void modifyRed(double percentage, double firstPortion,double secondPortion) {
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    int index = 0;
    /*
    if (percentage < 0.0) {
      System.out.println("Error! Parameter less than zero.");
      return;
    }
    
    if (percentage > 2.0) {
      System.out.println("Error! Parameter greater than two.");
      return;
    }
    
    if (firstPortion < 0.0) {
      System.out.println("Error! First portion less than zero.");
      return;
    }
    
    if (firstPortion > 1.0) {
      System.out.println("Error! First portion greater than one.");
      return;
    }
    
    if (secondPortion < 0.0) {
      System.out.println("Error! Second portion less than zero.");
      return;
    }
    
    if (secondPortion > 1.0) {
      System.out.println("Error! Second portion greater than one.");
      return;
    }
    
    if (firstPortion > secondPortion) {
      System.out.println("Error! first portion comes after second portion.");
      return;
    }
    */
    
    index = (int)(pixelArray.length*firstPortion);
    // loop through all the pixels
    while (index < pixelArray.length*secondPortion) {
      // get the current pixel
      pixel = pixelArray[index];
      
      // get the red value
      value = pixel.getRed();
      
      // modify by the passed in percentage
      value = (int) (value * percentage);
      
      // set the red value of the current pixel to the new value
      pixel.setRed(value);
      
      // increment the index
      index = index + 1;
      
    }
  }
    
    public void reportRGB() {
          Pixel[] pixelArray = this.getPixels();
          Pixel pixel = null;
          int redValue = 0;
          int greenValue = 0;
          int blueValue = 0;
          int redMin = 255;
          int greenMin = 255;
          int blueMin = 255;
          int redMax = 0;
          int greenMax = 0;
          int blueMax = 0;
          int redSum = 0;
          int greenSum = 0; 
          int blueSum = 0;
          int index = 0;
          
          while (index < pixelArray.length) {
            // get the current pixel
            pixel = pixelArray[index];
      
            // get the values
            redValue = pixel.getRed();
            greenValue = pixel.getGreen();
            blueValue = pixel.getBlue();
            
            // add to sums
            redSum += redValue;
            greenSum += greenValue;
            blueSum += blueValue;
            
            // check if max
            if (redValue > redMax) {
              redMax = redValue;
            }
            if (greenValue > greenMax) {
              greenMax = greenValue;
            }
            if (blueValue > blueMax) {
              blueMax = blueValue;
            }
            
            // check if min
            if (redValue < redMin) {
              redMin = redValue;
            }
            if (greenValue < greenMin) {
              greenMin = greenValue;
            }
            if (blueValue < blueMin) {
              blueMin = blueValue;
            }
            
            index++;
          }

          System.out.println("Average red value is " + redSum/pixelArray.length);
          System.out.println("Average green value is " + greenSum/pixelArray.length);
          System.out.println("Average blue value is " + blueSum/pixelArray.length);
          
          System.out.println("Minimum red value is " + redMin);
          System.out.println("Minimum green value is " + greenMin);
          System.out.println("Minimum blue value is " + blueMin);
          
          System.out.println("Maximum red value is " + redMax);
          System.out.println("Maximum green value is " + greenMax);
          System.out.println("Maximum blue value is " + blueMax);
    }
    
    public void reverse() {
      /* this method reverses the pixel array in the image, 
       * effectively causing a 180 degree rotation of the image
       */
          Pixel[] pixelArray = this.getPixels();
          Picture newPic = new Picture(this.getWidth(), this.getHeight());
          Pixel[] tempArray = newPic.getPixels();
          
          int index = 0; 
          while (index < pixelArray.length) {
            tempArray[index].setRed(pixelArray[index].getRed());
            tempArray[index].setGreen(pixelArray[index].getGreen());
            tempArray[index].setBlue(pixelArray[index].getBlue());
            index++;
          }
          
          index = 0;
          while (index < pixelArray.length) {
            pixelArray[index].setRed(tempArray[tempArray.length - 1 - index].getRed());
            pixelArray[index].setGreen(tempArray[tempArray.length - 1 - index].getGreen());
            pixelArray[index].setBlue(tempArray[tempArray.length - 1 - index].getBlue());
            index++;
          }
    } // end reverse()
    
    public void colorBlindSim(char c) {
          Pixel[] pixelArray = this.getPixels();
          Pixel pixel = null;
          int redValue = 0;
          int greenValue = 0;
          int blueValue = 0;
          
          int index = 0;
          while (index < pixelArray.length) {
            // get the current pixel
            pixel = pixelArray[index];
      
            // get the values
            redValue = pixel.getRed();
            greenValue = pixel.getGreen();
            blueValue = pixel.getGreen();
            
            if (c == 'R') {
              // protanopia, remove red, increase green and blue slightly
              redValue = 0;
              greenValue = (int)(greenValue*1.2);
              blueValue = (int)(blueValue*1.2);
            }
            if (c == 'G') {
              // deutanopia
              greenValue = 0;
            }
            if (c == 'B') {
              // tritanopia
              blueValue = 0;
            }
            
            // set the values
            pixel.setRed(redValue);
            pixel.setGreen(greenValue);
            pixel.setBlue(blueValue);
      
            // increment the index
            index = index + 1;
          }
    }
    
    
        public void fade() {
          
          Pixel[] pixelArray = this.getPixels();
          Pixel pixel = null;
          
          for (int i = 0; i < pixelArray.length; i++) {
            // get the current pixel
            pixel = pixelArray[i];
            
            // get, modify and then set the values
            pixel.setRed((int)(pixel.getRed()*0.8));
            pixel.setGreen((int)(pixel.getGreen()*0.8));
            pixel.setBlue((int)(pixel.getBlue()*0.8));
      
          }
        }
      
        
        
        public void averageWithSource(Picture source) {
          // get pixel arrays for this image and the source image
          Pixel[] pixelArray = this.getPixels();
          Pixel[] sourceArray = source.getPixels();
          Pixel pixel1 = null;
          Pixel pixel2 = null;
          int redValue = 0;
          int greenValue = 0;
          int blueValue = 0;
          
          // test to make sure that the source picture is the exact same size as this picture
          if (this.getWidth() != source.getWidth() || this.getHeight() != source.getHeight()) {
            // source is not the same size, give error and return
            System.out.println("Error! Source image size is not the same as this image.");
            return;
          }
          
          for (int i = 0; i < pixelArray.length; i++) {
            // get the current pixel from both this and source
            pixel1 = pixelArray[i];
            pixel2 = sourceArray[i];
      
            // get the average values by adding the components from both and dividing by 2
            redValue = (pixel1.getRed() + pixel2.getRed())/2;
            greenValue = (pixel1.getGreen() + pixel2.getGreen())/2;
            blueValue = (pixel1.getBlue() + pixel2.getBlue())/2;
            
            // set the values back in this pic
            pixel1.setRed(redValue);
            pixel1.setGreen(greenValue);
            pixel1.setBlue(blueValue);
          }
          
        }
        
        public void copyPixelsFromSource(Picture source) {
          // get pixel arrays for this image and the other image
          Pixel[] pixelArray = this.getPixels();
          Pixel[] sourceArray = source.getPixels();
          
          // get pixel objects to store each pixel in as we go through the arrays
          Pixel pixel1 = null;
          Pixel pixel2 = null;
          
          // temp variables for each color component
          int redValue = 0;
          int greenValue = 0;
          int blueValue = 0;
          
          // test to make sure that the source picture is the exact same size as this picture
          if (this.getWidth() != source.getWidth() || this.getHeight() != source.getHeight()) {
            // source is not the same size, give error and return
            System.out.println("Error! Source image size is not the same as this image.");
            return;
          }
          
          for (int i = 0; i < pixelArray.length; i++) {
            // get the current pixel from both this and the source 
            pixel1 = pixelArray[i];
            pixel2 = sourceArray[i];
      
            // get the source value
            redValue = pixel2.getRed();
            greenValue = pixel2.getGreen();
            blueValue = pixel2.getBlue();
            
            // set the values back in the source
            pixel1.setRed(redValue);
            pixel1.setGreen(greenValue);
            pixel1.setBlue(blueValue);
      
          }
          
        } // end copy pixels method
  
        public void weightedAverageWithSource(Picture source, double sourcePercent) {
          // get pixel arrays for this image and the other image
          Pixel[] pixelArray = this.getPixels();
          Pixel[] sourceArray = source.getPixels();
          Pixel pixel1 = null;
          Pixel pixel2 = null;
          int redValue = 0;
          int greenValue = 0;
          int blueValue = 0;
          double thisPercent = 1.0 - sourcePercent;
          
          // test to make sure that the source picture is the exact same size as this picture
          if (this.getWidth() != source.getWidth() || this.getHeight() != source.getHeight()) {
            // source is not the same size, give error and return
            System.out.println("Error! Source image size is not the same as source image.");
            return;
          }
          
          if (sourcePercent < 0.0) { 
            // sourcePercent is not in the right range
            System.out.println("Error! sourcePercent is below 0.0");
            return;
          } 
          
          if (sourcePercent > 1.0) {
            // sourcePercent is not in the right range
            System.out.println("Error! sourcePercent is above 1.0");
            return;
          } 
          
          for (int i = 0; i < pixelArray.length; i++) {
            // get the current pixel from both this pic and source 
            pixel1 = pixelArray[i];
            pixel2 = sourceArray[i];
      
            // get the average values by adding the components from both and dividing by 2
            redValue = (int)(thisPercent*pixel1.getRed() + sourcePercent*pixel2.getRed());
            greenValue = (int)(thisPercent*pixel1.getGreen() + sourcePercent*pixel2.getGreen());
            blueValue = (int)(thisPercent*pixel1.getBlue() + sourcePercent*pixel2.getBlue());
            
            // set the values back in the source
            pixel1.setRed(redValue);
            pixel1.setGreen(greenValue);
            pixel1.setBlue(blueValue);

          }
          
        }
        
        public void mirrorVertical() {
          int width = this.getWidth();
          int mirrorPoint = width / 2;
          Pixel leftPixel = null;
          Pixel rightPixel = null;
          
          // loop through all the rows
          for (int y = 0; y < getHeight(); y++) {
            // loop from the first pixel to the middle pixel
            for (int x = 0; x < mirrorPoint; x++) {
              leftPixel = getPixel(x, y);
              rightPixel = getPixel(width - 1 - x, y);
              rightPixel.setColor(leftPixel.getColor());
            }
          }
        }
        
        public void mirrorHorizontalTopToBottom() {
          int height = this.getHeight();
          int mirrorPoint = height/2;
          Pixel topPixel = null;
          Pixel bottomPixel = null;
          
          // loop through all the columns
          for (int x = 0; x < getWidth(); x++) {
            // loop from top to middle point
            for (int y = 0; y < mirrorPoint; y++) {
              topPixel = getPixel(x, y);
              bottomPixel = getPixel(x, height - 1 - y);
              bottomPixel.setColor(topPixel.getColor());
            }
          }
        }
        
        public void quatrefoil() {
          this.mirrorVertical();
          this.mirrorHorizontalTopToBottom();
        }
        
        public void verticalShutters() {
          Pixel pixel = null; 
          
          // loop through all the columns
          for (int x = 0; x < getWidth(); x++) {
            // check if this column should be black
            if ((x/10)%2 == 0) {
              // if it should be black, loop through each pixel in this column
              for (int y = 0; y < getHeight(); y++) {
                // TODO: change to black
                pixel = getPixel(x,y);
                pixel.setColor(Color.black);
              }
            }  
          }
        }
        
        public void verticalShutters(int swidth) {
          // adds shutters to image
          // swidth must be from 1 to 20
          Pixel pixel = null; 
          
          // loop through all the columns
          for (int x = 0; x < getWidth(); x++) {
            // check if this column should be black
            if (x%20<=swidth) {
              // if it should be black, loop through each pixel in this column
              for (int y = 0; y < getHeight(); y++) {
                // TODO: change to black
                pixel = getPixel(x,y);
                pixel.setColor(Color.black);
              }
            }  
          }
        }
        
        public void checkerboard(int width, Color newColor) {
          // preconditions: width must be less than 1/2 picture width
          if (width > this.getWidth()/2) {
            System.out.println("Error: checkerboard width too large.");
            return;
          }
            Pixel pixel = null;
            for (int x = 0; x< getWidth(); x++) {
              for (int y = 0; y < getHeight(); y++) {
                if (((x/width)%2 == 0) && ((y/width)%2 == 0)) {
                  pixel = getPixel(x,y);
                  pixel.setColor(newColor);
                  
                }
                if (((x/width)%2 == 1) && ((y/width)%2== 1)) {
                  pixel = getPixel(x,y);
                  pixel.setColor(newColor);
                  
                }
                
              }
            }
        }
        
        public void checkerboard(int width, int alpha) {
          // preconditions: width must be less than 1/2 picture width
          if (width > this.getWidth()/2) {
            System.out.println("Error: checkerboard width too large.");
            return;
          }
            Pixel pixel = null;
            for (int x = 0; x< getWidth(); x++) {
              for (int y = 0; y < getHeight(); y++) {
                if (((x/width)%2 == 0) && ((y/width)%2 == 0)) {
                  pixel = getPixel(x,y);
                  pixel.setAlpha(alpha);
                }
                if (((x/width)%2 == 1) && ((y/width)%2== 1)) {
                  pixel = getPixel(x,y);
                  pixel.setAlpha(alpha);
                }
                
              }
            }
        }
                
        public void checkerboard(int width, Picture otherPic) {
          // preconditions: width must be less than 1/2 picture width
          // preconditions: otherPic must be the same size as this image
          if (width > this.getWidth()/2) {
            System.out.println("Error: checkerboard width too large.");
            return;
          }
          if (!(this.isSameSize(otherPic))) {
            System.out.println("Error:underPic is not the same size.");
            return;
          }
            Pixel pixel = null;
            for (int x = 0; x< getWidth(); x++) {
              for (int y = 0; y < getHeight(); y++) {
                if (((x/width)%2 == 0) && ((y/width)%2 == 0)) {
                  //pixel = getPixel(x,y);
                  //pixel.setColor(otherPic.getPixel(x,y).getColor());
                  copyPixel(otherPic, x, y, this, x, y);
                }
                if (((x/width)%2 == 1) && ((y/width)%2== 1)) {
                  //pixel = getPixel(x,y);
                  //pixel.setColor(otherPic.getPixel(x,y).getColor());
                  copyPixel(otherPic, x, y, this, x, y);
                }
              }
            }
        }
        
        public void horizontalShutters() {
          Pixel pixel = null; 
          
          // loop through all the row
          for (int y = 0; y < getHeight(); y++) {
            // check if this row should be black
            if ((y/10)%2 == 0) {
              // if it should be black, loop through each pixel in this column
              for (int x = 0; x < getWidth(); x++) {
                // change to black
                pixel = getPixel(x,y);
                pixel.setColor(Color.black);
              } // end for loop
              
            }  else {
              for (int x = 0; x < getWidth(); x++) {
                // make pixels brighter
                pixel = getPixel(x,y);
                pixel.setColor(pixel.getColor().brighter());
              } // end for loop
            } // end else
          } // end for rows loop
        }
        
        public Picture tile() {  
          // create a new picture, twice as big
          Picture newPic = new Picture(this.getWidth()*2, this.getHeight()*2);
          Pixel srcPixel = null;
          Pixel destPixel = null;
          
          // copy the pixels from source image into the new picture
          for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
               // get this pixel from source
               srcPixel = getPixel(x,y);
               
               // copy pixel into top-left quadrant  
                destPixel = newPic.getPixel(x,y);
                destPixel.setColor(srcPixel.getColor());
                
                // copy pixel into top-right quadrant
                destPixel = newPic.getPixel(x+getWidth(),y);
                destPixel.setColor(srcPixel.getColor());
                
                // copy pixel into bottom-left quadrant
                
                // copy pixel into bottom-right quadrant
                
            } // end for loop
          }
          
          // return the new picture
          return newPic;
        }
        
        public Picture shrink(int factor) {
          // scale down image by 1/2 or 1/3 or 1/4
          Picture scaledPic = null;
          // check pre-conditions that factor must be 2, 3 or 4.
          if (factor < 2) {
            System.out.println("Error! Factor is less than 2.");
            return scaledPic;
          }
          if (factor > 4) {
            System.out.println("Error! Factor is larger than 4.");
            return scaledPic;
          }
          scaledPic = new Picture(getWidth()/factor + 1, getHeight()/factor + 1);
          System.out.println("Copying picture by factor: " + factor);
          for (int srcX = 0, tgtX=0; srcX < getWidth(); srcX+=factor, tgtX++) {
            for (int srcY = 0, tgtY=0; srcY < getHeight(); srcY+=factor, tgtY++) {
              //System.out.println("Src: (" + srcX + "," + srcY + "),  Tgt: (" + tgtX + "," + tgtY + ")");
              copyPixel(this, srcX, srcY, scaledPic, tgtX, tgtY);
            }
          }
          return scaledPic;
        }
        
        public void crop(int xPos, int yPos, int width, int height) {
          // leave everything else white
          Pixel pixel = null;
          
          for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
              if (x < xPos || y < yPos || x > xPos+ width || y > yPos + width) {
                 pixel = getPixel(x, y);
                 pixel.setColor(Color.WHITE);
              }
            }
          }
        }
        
        public void crop(int xPos, int yPos, int width, int height, Picture newPic) {
          // crop this picture into newPic
          // pre-conditions: crop area is inside picture
          // leave everything else white
          // make sure newPic is the right size
          if (newPic.getWidth() != width) {
            return;
          }
          if (newPic.getHeight()!= height) {
            return;
          }
        
          for (int x = 0; x < newPic.getWidth(); x++) {
            for (int y = 0; y < newPic.getHeight(); y++) {           
               if (x + xPos < getWidth() && y + yPos < getHeight()) {
                 copyPixel(this, x + xPos, y + yPos, newPic, x, y);
               }
            }  
          }   
          
        }
        
        
        /*
         for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
              if (!(x < xPos || y < yPos || x >= xPos + width || y >= yPos + height)) {
                 copyPixel(this, x, y, newPic, x-xPos, y-yPos);
              }
            }
          }
        */
        public void reveal(Picture underPic, double percent) {
          // copies underPic to this picture from the top down, until percent down
          // pre-conditions: assumes the two pics are the same size, 
          //                 assumes percent is between 0.0 and 1.0
          if (!(percent >= 0.0 && percent <= 1.0)) {
            System.out.println("Error: percent is not between 0.0 and 1.0");
            return;
          }
          
          if (!(this.isSameSize(underPic))) {
            System.out.println("Error:underPic is not the same size.\n");
            return;
          }
          Pixel pixel = null;
          int divider = (int)(percent*this.getHeight());
          for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
              if (y < divider) {
                // top part copy pixel from under pixel
                pixel = getPixel(x, y);
                pixel.setColor(underPic.getPixel(x, y).getColor());
              }
              if (y == divider) {
                // dividing line, set to black
                pixel = getPixel(x,y);
                pixel.setColor(Color.black);
              }
              // else leave image as is
            }
          }
        }
        
       private boolean isSameSize(Picture target) {
          // checks if target picture is the same size as this picture
          if (target.getWidth() == this.getWidth()) {
            if (target.getHeight() == this.getHeight()) {
              return true;
            }
          }
          return false;
        }
        
        public void addPic(Picture newPic, int xPos, int yPos) {
          // adds newPic to this pic, at pos xPos, yPos
          // preconditions: assumes newPic is smaller than current pic
          // postconditions: newPic is added to this picture, at the indicated position
          
          // first, check the pre-conditions
          if (newPic.getWidth() > this.getWidth() || newPic.getHeight() > this.getHeight()) {
            System.out.println("Error! The passed in picture is larger than this picture");
            return;
          }
          
          int newWidth = newPic.getWidth();
          int newHeight = newPic.getHeight();
          // iterate through each column and row
          for (int x=0; x < newWidth; x++) {
            for (int y=0; y < newHeight; y++) {
              // make sure that we aren't trying to copy beyond either
              // the left side of this image or the bottom of this image
              if (xPos + x < getWidth()  && yPos + y < getHeight()) {
                // call our private copyPixel method
                copyPixel(newPic, x, y, this, x + xPos, y + yPos);
              }
            }
          }
        
        }
        
        public void replaceTransparencyWithColor(Color c) {
          Pixel[] pixelArray = this.getPixels();
          Pixel pixel = null;
          int alpha = 0;
          
          for (int i= 0; i < pixelArray.length; i++) {
            pixel = pixelArray[i];
            alpha = pixel.getAlpha();
            if (alpha < 100) {
               System.out.println("found trans pixel");
               pixel.setColor(c);
               pixel.setAlpha(255);
            }
          }
        }
        
        public void colorfulEdgeDetection(double amount, double level) {
          Pixel topPixel = null;
          Pixel bottomPixel = null;
          double topAverage = 0.0;
          double bottomAverage = 0.0;
          int endY = this.getHeight() - 1;
          
          for (int y = 0; y < endY; y++) {
            for (int x = 0; x < getWidth(); x++) {
              topPixel = this.getPixel(x,y);
              bottomPixel = this.getPixel(x, y+1);
              topAverage = topPixel.getAverage();
              bottomAverage = bottomPixel.getAverage();
              
              if ((topAverage > level) && (Math.abs(topAverage-bottomAverage) < amount)) { 
                topPixel.setColor(Color.WHITE);
              } 
            }
          }
          
        }
        
        private void copyPixel(Picture srcPic, int srcX, int srcY, Picture destPic, int destX, int destY) {
          Pixel destPixel = null;
          Pixel srcPixel = null;
          destPixel = destPic.getPixel(destX, destY);
          srcPixel = srcPic.getPixel(srcX, srcY);
          destPixel.setColor(srcPixel.getColor());
        }
        
        public void makeTrans() {
          Pixel[] pixelArray = this.getPixels();
          Pixel pixel = null;
          
          
          for (int i= 0; i < pixelArray.length; i++) {
            pixel = pixelArray[i];
            pixel.setAlpha(0);
          }
        }
        
        public void simpleCopyFromSource(Picture source) {
          // first make sure images are the same size
          if (!isSameSize(source)) {
            System.out.println("Error! The source picture is not the same size.");
            return;
          } // end if
          // iterate through the rows and columns, and copy each pixel from the source into this picture
          for (int x=0; x<getWidth(); x++) {
            for (int y=0; y<getHeight(); y++) {
              copyPixel(source, x, y, this, x, y);
            } // end inner for loop
          } // end outer for loop
          
        } // end simpleCopyFromSource method
        
        public void grayscale() {
          Pixel[] pixelArray = this.getPixels();
          Pixel pixel = null;
          int intensity = 0;
          
          for (int i= 0; i < pixelArray.length; i++) {
            pixel = pixelArray[i];
            intensity = (int)((pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3);
            pixel.setColor(new Color(intensity, intensity, intensity));
          }
        }
        
        public void blackOut(int startX, int endX, int startY, int endY) {
          Pixel pixel = null;
          for (int x = 0; x < this.getWidth(); x++) {
             for (int y = 0; y < this.getHeight(); y++) {
               if (x >= startX && x <= endX && y >= startY && y <= endY) {
                 pixel = this.getPixel(x,y);
                 pixel.setColor(Color.BLACK);
               }
             }
          }
          
        }
        
        private void blurPixel(int x, int y, int size) {
          // blurs the passed in (x,y) pixel by averaging the colors of the surrounding pixels
          // size is how far out to go, so if size is 2, we will blur based on two columnss to the 
          // left, two to the right, two rows above and two below, in a square
           // calculate blur
          Pixel tempPixel = null;
          Pixel pixel = null; 
          
                 int sumR = 0;
                 int sumG = 0;
                 int sumB = 0;
                 int divisor = 0;
                 // iterate over the size x size pixels in this area to add up the RGBs
                 for (int subx = x-size; subx < x+size + 1; subx++) {
                   for (int suby = y-size; suby < y+size + 1; suby++) {
                     // check if this pixel is in the range of this image
                     if ((subx > 0) && (subx < this.getWidth()) &&
                         (suby > 0) && (suby < this.getHeight())) {
                       // in range, so get this pixel, add it's colors to running average
                       tempPixel = this.getPixel(subx, suby);
                       sumR += tempPixel.getRed();
                       sumG += tempPixel.getGreen();
                       sumB += tempPixel.getBlue();
                       // incease divisor
                       divisor += 1;
                     }
                   }
                 }
                 // done adding up all the colors from surrounding pixels so
                 // get this pixel and then set it's color to the average RGBs
                 // making sure to divide by the divisor (num colors added in)
                 pixel = this.getPixel(x,y);
                 pixel.setRed((int)(sumR/divisor));
                 pixel.setGreen((int)(sumG/divisor));
                 pixel.setBlue((int)(sumB/divisor));
        }
        
        public void blurArea(int startX, int endX, int startY, int endY, int level) {
          // check preconditions that startX < endX and startY < endY
          if ((startX >= endX) || (startY>= endY)) {
            System.out.println("Error! Invalid area specified.");
            return;
          }
          
          int sumR;
          int sumG;
          int sumB;
          for (int x = 0; x < this.getWidth(); x++) {
             for (int y = 0; y < this.getHeight(); y++) {
               if (x >= startX && x <= endX && y >= startY && y <= endY) {
                 // calculate blur
                 blurPixel(x, y, level);
               }
             }
          }
        }
        
        public void blurOutsideArea(int startX, int endX, int startY, int endY, int level) {
          // check preconditions that startX < endX and startY < endY
          if ((startX >= endX) || (startY>= endY)) {
            System.out.println("Error! Invalid area specified.");
            return;
          }
          
          int sumR;
          int sumG;
          int sumB;
          for (int x = 0; x < this.getWidth(); x++) {
             for (int y = 0; y < this.getHeight(); y++) {
               if (!(x >= startX && x <= endX && y >= startY && y <= endY)) {
                 // calculate blur
                 blurPixel(x, y, level);
               }
             }
          }
        }
        
        public void sepiaTint() {
           Pixel pixel = null;
           
           double redValue = 0; 
           double greenValue = 0;
           double blueValue = 0;
           
           this.grayscale();
           
           for (int x = 0; x < this.getWidth(); x++) {
             for (int y = 0; y < this.getHeight(); y++) {
               pixel = this.getPixel(x,y);
               redValue = pixel.getRed();
               greenValue = pixel.getGreen();
               blueValue = pixel.getBlue();
               
               if (redValue < 60) {
                 redValue = redValue *0.9;
                 greenValue = greenValue *0.9;
                 blueValue = blueValue *0.9;
               } else if (redValue < 190) {
                 blueValue = blueValue*0.8; 
               } else {
                 blueValue = blueValue*0.9; 
               }
               
               pixel.setRed((int)redValue);
               pixel.setGreen((int)greenValue);
               pixel.setBlue((int)blueValue);
               
             }
           }
           
        }
        
        public void decreaseRed() 
        {
          Pixel[] pixelArray = this.getPixels();
          Pixel pixel = null;
          int value = 0;
          int index = 0;
          
          while (index < pixelArray.length) 
          {
            // get the current pixel
            pixel = pixelArray[index];
            // get the red value of this pixel
            value = pixel.getRed();
            // decrease the red value by 50% (1/2)
            value = (int)(value * 0.5);
            // set the red value of current pixel to new value
            pixel.setRed(value);
            // increment the index
            index = index + 1;
          }
        }
        public void addBox()
        {
            //get graphics context from picture
            Graphics g = this.getGraphics();
            
            g.setColor(Color.RED);
            
            //draw filled box and rect
            g.fillRect(150,200,50,50);
        }
        
        public void drawFace()
        {
            //graphic obj
            Graphics graphics = this.getGraphics();
            
            //black color
            graphics.setColor(Color.BLACK);
            
            //oval for face
            graphics.drawOval(130, 50, 380, 380);
            
            //ovals for eeys
            graphics.fillOval(225, 155, 40, 40);
            graphics.fillOval(375, 155, 40, 40);
            
            //arc for mouth
            graphics.drawArc(190, 85, 255, 255, -45, -90);
            
            //arcs for eyebrows
            graphics.drawArc(225, 145, 40, 40, 45, 90);
            graphics.drawArc(375, 145, 40, 40, 45, 90);
        }
        
        public void drawHorizontalCenteredString(String text, int y)
        {
            //graphics obj
            Graphics g = this.getGraphics();
            
            //create font
            Font font = new Font("Arial",Font.BOLD,24);
            
            //color
            g.setColor(Color.BLACK);
            
            //set font
            g.setFont(font);
            
            //get font metrics
            FontMetrics fontMetrics = g.getFontMetrics();
            
            //get the width of string
            int strWidth = fontMetrics.stringWidth(text);
            
            //calc center of pic
            int center = (int) (this.getWidth()*0.5);
            
            //draw string centered in x
            g.drawString(text,center-(int)(strWidth*0.5),y);
            
        }
        /**
         * This method should be used to draw the MasterCard logo on the screen
         */
        public void drawMCLogo()
        {
            //initialize graphics object
            Graphics g = this.getGraphics();
            
            
            
            //set color for first circle
            g.setColor(Color.RED);
            
            g.fillOval(100, 10, 100, 100);
            
            g.setColor(Color.YELLOW);
            
            g.fillOval(150, 10, 100, 100);
            
            //set color
            g.setColor(Color.WHITE);
            
            g.setFont(new Font("Arial",Font.BOLD,24));
            g.drawString("MasterCard", 120,75);
        }
            
} // this } is the end of class Picture, put all new methods before this
 