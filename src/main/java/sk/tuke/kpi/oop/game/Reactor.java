package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import javax.swing.*;

public class Reactor extends AbstractActor {   //toto je trieda
    private int temperature;  //toto su atributy
    private boolean state;
    private int damage;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;

    public Reactor() {   // toto je metoda (konstruktor)- špecialna metoda a tato nič nevracia, služi na inicialkizáciu triedy
        this.temperature = 0;
        this.state = false;  //členské permenneé patria objektu ktory sme vytvorili
        this.damage = 0;
        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        this.hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        this.brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80, 80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );

        this.offAnimation = new Animation("sprites/reactor.png");

        // set init reactor animation
        setAnimation(offAnimation);
    }

    public int getTemperature() {
        return this.temperature;
    }  //metody = to su funkcie

    public int getDamage() {
        return this.damage;
    }

    public void increaseTemperature(int increment) {
        if (increment < 0) {
            return;
        }

        this.temperature = this.temperature + increment;

        if (this.damage == 100) {
            return;
        }

        updateAnimation();

        // update damage
        if (this.temperature >= 2000) {
            if (this.temperature >= 6000) {
                this.damage = 100;
            } else {
                int damage = (this.temperature / 40) - 50;
                if (this.damage < damage) {
                    this.damage = damage;
                }
            }
        }
    }

    public void decreaseTemperature(int decrement) {
        if (decrement < 0) {
            return;
        }

        this.temperature = this.temperature - decrement;

        if (this.damage == 100) {
            return;
        }

        updateAnimation();
    }

    private void updateAnimation() {
        // if temperature is >= 6000, then broken show reactor
        if (this.temperature >= 6000) {
            setAnimation(this.brokenAnimation);

            // if (4000 <= temperature < 6000), then show hot reactor
        } else if (this.temperature >= 4000) {
            setAnimation(this.hotAnimation);

            // otherwise show normal reactor
        } else {
            setAnimation(this.normalAnimation);
        }
    }

    public void repairWith(Hammer hammer) {
        if (hammer == null) {
            return;
        }

        // repair only if damage >0
        if (this.damage == 0 || this.damage == 100) {
            return;
        }
        // use hamer
        hammer.use();
        //
        this.damage = this.damage - 50;
        if (this.damage < 0) {
            this.damage = 0;
        }
    }
}
