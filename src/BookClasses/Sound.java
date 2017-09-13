package BookClasses;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a sound. This class is used by the students to extend
 * the capabilities of SimpleSound.
 *
 * Copyright Georgia Institute of Technology 2004
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Sound extends SimpleSound {

    /////////////// consructors ////////////////////////////////////
    /**
     * Constructor that takes a file name
     *
     * @param fileName the name of the file to read the sound from
     */
    public Sound(String fileName) {
        // let the parent class handle setting the file name
        super(fileName);
    }

    /**
     * Constructor that takes the number of samples in the sound
     *
     * @param numSamples the number of samples desired
     */
    public Sound(int numSamples) {
        // let the parent class handle this
        super(numSamples);
    }

    /**
     * Constructor that takes the number of samples that this sound will have
     * and the sample rate
     *
     * @param numSamples the number of samples desired
     * @param sampleRate the number of samples per second
     */
    public Sound(int numSamples, int sampleRate) {
        // let the parent class handle this
        super(numSamples, sampleRate);
    }

    /**
     * Constructor that takes a sound to copy
     */
    public Sound(Sound copySound) {
        // let the parent class handle this
        super(copySound);
    }

    ////////////////// methods ////////////////////////////////////
    /**
     * Method to return the string representation of this sound
     *
     * @return a string with information about this sound
     */
    public String toString() {
        String output = "Sound";
        String fileName = getFileName();

        // if there is a file name then add that to the output
        if (fileName != null) {
            output = output + " file: " + fileName;
        }

        // add the length in frames
        output = output + " number of samples: " + getLengthInFrames();

        return output;
    }

    public static void main(String[] args) {
        Sound sound1 = new Sound(FileChooser.pickAFile());
        sound1.explore();
    }

    public void increaseVolume() {
        SoundSample[] sampleArray = this.getSamples();
        SoundSample sample = null;
        int value = 0;
        int index = 0;

        //loop all samples in the array
        while (index < sampleArray.length) {
            sample = sampleArray[index];
            value = sample.getValue();
            sample.setValue(value * 2);
            index++;
        }
    }

    /**
     * Method to change volume of the sound by multiplying the current values in
     * the sound by the passed factor
     *
     * @param factor the factor to multiply by
     */
    public void changeVolume(double factor) {
        SoundSample[] sampleArray = this.getSamples();
        SoundSample sample = null;
        int value = 0;
        //loop through all samples
        for (int i = 0; i < sampleArray.length; i++) {
            sample = sampleArray[i];
            value = sample.getValue();
            sample.setValue((int) (value * factor));
        }
    }

    public void normalize() {
        int largest = 0;
        int maxIndex = 0;
        SoundSample[] sampleArray = this.getSamples();
        SoundSample sample = null;
        int value = 0;

        //loop coparing abs value of current to current largest
        for (int i = 0; i < sampleArray.length; i++) {
            sample = sampleArray[i];
            value = Math.abs(sample.getValue());
            if (value > largest) {
                largest = value;
                maxIndex = i;
            }
        }
        //calc multiplyer
        double multiplier = 32767.0 / largest;

        //print largest value and multiplier
        System.out.println("Largeset value was " + largest + " at index " + maxIndex);
        System.out.println("Multiplier is: " + multiplier);

        /*
      *loop through all samples and multiply by multiplier
         */
        for (int i = 0; i < sampleArray.length; i++) {
            sample = sampleArray[i];
            sample.setValue((int) (sample.getValue() * multiplier));
        }

    }

    /**
     * method to set all sample values to max positive value
     */
    public void forceToExtremes() {
        SoundSample[] sampleArray = this.getSamples();
        SoundSample sample = null;

        //loop through sample values
        for (int i = 0; i < sampleArray.length; i++) {
            sample = sampleArray[i];
            if (sample.getValue() >= 0) {
                sample.setValue(32767);
            } else {
                sample.setValue(-32767);
            }
        }

    }
    //new method for clipping low frequencies

    /**
     * this method clips low frequencies
     *
     * @return this is an integer that tell you how many samples were clipped
     */
    public int clipLows() {

        SoundSample[] sampleArray = this.getSamples();
        SoundSample sample = null;
        int index = 0;
        //loop through samples
        for (int i = 0; i < sampleArray.length; i++) {
            sample = sampleArray[i];

            if (sample.getValue() < 0) {
                index++;
                sample.setValue(0);

            }

        }//end of for loop
        return index;

    }

    public void blackOutRange(int startIndex, int endIndex) {
        SoundSample[] sampleArray = this.getSamples();
        SoundSample sample = null;
      
        for(int x = startIndex; x <= endIndex; x++){
            
            sample = sampleArray[x];
            sample.setValue(0);
            
        }
        
    }
    /**
     * 
     * @param size how large of a chunk to replace
     * @param value what value to replace each chunk with
     */
    public void checkerBoardValue(int size, int value)
    {
        SoundSample[] sampleArray = this.getSamples();
        SoundSample sample = null;
        
        for(int i =0;i<sampleArray.length;i++)
        {
            sample=sampleArray[i];
            if((i/size)%2==1)
            {
                sample.setValue(value);
                
            }
        }
    }
    /**
     * method to splice two sounds together with some silence between them
     */
    public void splice()
    {
        Sound sound1=
                new Sound(FileChooser.getMediaPath("guzdial.wav"));
        Sound sound2=
                new Sound(FileChooser.getMediaPath("is.wav"));
        int targetIndex =0; //starting value
        int value =0;
        
        //copy sound1 to current sound
        for (int i=0; i< sound1.getLength();i++,targetIndex++)
        {
            value=sound1.getSampleValueAt(i);
            this.setSampleValueAt(targetIndex, value);
        }    
            //creat silence between words
            for (int i=0;i< (int) (this.getSamplingRate()*0.1);i++,targetIndex++);
            {
                this.setSampleValueAt(targetIndex, 0);
            }
        
            //copy all of sound2 into current sound target
            for (int i =0;i<sound2.getLength();i++,targetIndex++)
            {
                value = sound2.getSampleValueAt(i);
                this.setSampleValueAt(targetIndex, value);
            }
    }
    /**
     * this method copies part of a sound into another sound given a start index
     * @param source the source sound to copy from
     * @param sourceStart starting index from the source
     * @param sourceStop the ending index
     * @param targetStart index to start copying sound into
     */
    public void splice(Sound source,int sourceStart,int sourceStop, int targetStart)
    {
        
        //loop copying source to target
        for (int sourceIndex = sourceStart,targetIndex=targetStart;
                sourceIndex<sourceStop && targetIndex<this.getLength();
                sourceIndex++,targetIndex++)
        {
            this.setSampleValueAt(targetIndex, source.getSampleValueAt(sourceIndex));
        }
              
                
    }
    /**
     * method to reverse current sound
     */
    public void reverse()
    {
        Sound orig = new Sound(this.getFileName());
        int length = this.getLength();
        
        //loop through samples
        for (int targetIndex =0,sourceIndex = length -1;
                targetIndex<length && sourceIndex > 0;
                targetIndex++,sourceIndex--)
        {
            this.setSampleValueAt(targetIndex, orig.getSampleValueAt(sourceIndex));
        }
    }
    
    public Sound zeroAlternatingSamples()
    {
        
        Sound sound = new Sound(this.getLength());
        SoundSample[] sampleArray = this.getSamples();
        SoundSample[] sampleArray2 = sound.getSamples();
        
        for (int i = 0; i < sampleArray.length; i++){
            if (i % 2 == 1){
                sampleArray2[i].setValue(0);
            }
            
            else
                sampleArray2[i].setValue(sampleArray[i].getValue());
        }
        
        return sound;
        
    }
    
    public Sound repeatAlternatingSamples()
    {
        
        Sound sound = new Sound(this.getLength());
        SoundSample[] sampleArray = this.getSamples();
        SoundSample[] sampleArray2 = sound.getSamples();
        
        for (int i = 0; i < sampleArray.length; i++){
            if (i % 2 == 0){
                sampleArray2[i].setValue(sampleArray[i].getValue());
            }
            
            else
                sampleArray2[i].setValue(sampleArray[i-1].getValue());
        }
        
        return sound;
        
    }
    
    public Sound skipSamples()
    {
        Sound sound = new Sound(this.getLength()/2);
        SoundSample[] sampleArray = this.getSamples();
        SoundSample[] sampleArray2 = sound.getSamples();
        
        for (int i = 0; i < sampleArray2.length; i++){
            sampleArray2[i].setValue(sampleArray[2*i].getValue());
        }
        return sound;
    }
    
    public Sound repeatAllSamples()
    {
        Sound sound = new Sound(this.getLength()*2);
        SoundSample[] sampleArray = this.getSamples();
        SoundSample[] sampleArray2 = sound.getSamples();
        
        for (int i = 0; i < sampleArray.length; i++){
            sampleArray2[2*i].setValue(sampleArray[i].getValue());
            sampleArray2[2*i+1].setValue(sampleArray[i].getValue());
        }
        return sound;
    }
    
    public Sound skipSamplesBonus(int size)
    {
        Sound sound = new Sound(this.getLength() - this.getLength()/size);
        SoundSample[] sampleArray = this.getSamples();
        SoundSample[] sampleArray2 = sound.getSamples();
        
        for (int i = 0; i < sampleArray2.length; i++){
            if (i % size != 0) 
            {
                sampleArray2[i].setValue(sampleArray[i].getValue());
            }
            sampleArray2[i].setValue(sampleArray[size*i].getValue());
            
        }
        return sound;
    }
    
    
} // this } is the end of class Sound, put all new methods before this
