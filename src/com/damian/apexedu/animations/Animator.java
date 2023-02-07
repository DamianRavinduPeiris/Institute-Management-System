package com.damian.apexedu.animations;

import animatefx.animation.*;
import javafx.scene.Node;
import rex.utils.S;

public class Animator {
    private static  Animator animator;
    private static Shake shake;
    private static Bounce bounce;
    private static LightSpeedIn lightSpeedIn;
    private static LightSpeedOut lightSpeedOut;
    private  static JackInTheBox jackInTheBox;
    private Animator(){
        shake  = new Shake();
        lightSpeedIn = new LightSpeedIn();
        lightSpeedOut  =new LightSpeedOut();
        jackInTheBox = new JackInTheBox();
        bounce = new Bounce();
    }
    public static  Animator getInstance(){
        if(animator == null){
            animator = new Animator();
        }
        return animator;
    }
    public static void setShake(Node node){
        shake.setNode(node);
        shake.play();
    }
    public static void setLightSpeedIn(Node node){
        lightSpeedIn.setNode(node);
        lightSpeedIn.play();
    }
    public static void setLightSpeedOut(Node node){
        lightSpeedOut.setNode(node);
        lightSpeedOut.play();
    }
    public static void setJackInTheBox(Node node){
        jackInTheBox.setNode(node);
        jackInTheBox.play();
    }
    public static void setBounce(Node node){
        bounce.setNode(node);
        bounce.play();
    }

}
