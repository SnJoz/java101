package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.nio.file.attribute.UserPrincipal;

public class Hammer extends AbstractActor {
    private int usages;  //členská premenná každý objekt má vlastnú

    public Hammer(){
        this.usages = 1;
        Animation animation = new Animation("sprites/hammer.png");
        setAnimation(animation);
    }
    public void use(){
        this.usages--;
        if(this.usages == 0){
            getScene().removeActor(this);
        }
    }

    public int getUsages(){
        return this.usages;
    }

}
