package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.javafx.geom.*;

import java.awt.*;
import java.awt.Rectangle;

/**
 * Created by Gesko927 on 17/12/2016.
 */
public class Hero {

    private Texture texture;

    private Vector2 position;

    private float speed;

    private int fireRate;

    private int fireCounter;

    private Rectangle rect;

    public Rectangle getRect(){ return rect;}

    public Vector2 getPosition() {
        return position;
    }

    public Hero() {
        texture = new Texture("ship.png");
        position = new Vector2(1280/2, 50);
        speed = 10.0f;
        fireRate = 5;
        rect = new Rectangle((int)position.x, (int)position.y, 64,64);
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            fireCounter++;

            if(fireCounter > fireRate) {
                fireCounter = 0;
                fire(position.x, position.y);
                fire(position.x + 35, position.y);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {

            position.y += speed;

            if(position.y > 656) position.y = 656;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed;

            if(position.y < 0) position.y = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed;

            if(position.x < 0) position.x = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed;

            if(position.x > 1220) position.x = 1220;
        }

        rect.x = (int)position.x;

        rect.y = (int)position.y;
    }

    private void fire(float x, float y) {

        for (int i = 0; i < MyGdxGame.bullets.length; i++) {

            if(!MyGdxGame.bullets[i].isActive()) {

                MyGdxGame.bullets[i].setup(x, y);
                break;
            }
        }
    }

    public void recreate(){

        position.x = 0;

        position.y = 0;

    }
}
