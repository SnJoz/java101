package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import javax.swing.*;

public class Reactor extends AbstractActor {
    private int temperature;
    private boolean state;
    private int damage;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;

    public Reactor(){
        this.temperature = 0;
        this.state = false;
        this.damage = 0;
        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80,80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        this.hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80,80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        this.brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80,80,
            0.1F,
            Animation.PlayMode.LOOP_PINGPONG
        );
        // set init reactor animation
        setAnimation(normalAnimation);
    }

    public int getTemperature() {
        return this.temperature;
    }
    public int getDamage() {
        return this.damage;
    }
    public void increaseTemperature(int increment) {
        this.temperature = this.temperature + increment;

        // update animation
        // if temperature is >= 6000, then broken show reactor
        if (this.temperature >= 6000) {
            setAnimation(this.brokenAnimation);
            this.damage = 100;

            // if (4000 <= temperature < 6000), then show hot reactor
        } else if (this.temperature >= 4000) {
            setAnimation(this.hotAnimation);
            int damage = this.temperature / 40 - 50;

            // otherwise show normal reactor
        } else {
            setAnimation(this.normalAnimation);
        }
        // update damage
        if (this.temperature > 2000 && this.temperature < 6000) {
            int damage = this.temperature / 40 - 50;
            if (this.damage < damage) {
                this.damage = damage;
            }
        }
    }
}
