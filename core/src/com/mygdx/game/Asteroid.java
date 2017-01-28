package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

/**
 * Created by Gesko927 on 17/12/2016.
 */
public class Asteroid
{

    private static Texture texture;

    private Vector2 position;

    private float speed;

    private Rectangle rect;

    private float angle;

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Asteroid() {
        position = new Vector2((float)Math.random() * 1280, (float) Math.random() * 720 + 720);

        speed = 4.0f + (float) Math.random() * 8.0f;

        rect = new Rectangle(position.x, position.y, 64, 64);

        if(texture == null)
        {
            texture = new Texture("asteroid.png");
        }

        angle = (float) Math.random() * 360;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(texture, position.x, position.y, 32,32, 64, 64, 1.0f, 1.0f,
                angle, 0, 0, 64, 64, false, false);
    }

    public void recreate()
    {
            angle = (float) Math.random() * 360;

            position.x = (float) Math.random() * 1280;

            position.y = 720 + (float) Math.random() * 720;

            speed = 4.0f + (float) Math.random() * 8.0f;
    }


    public void update()
    {
        position.y -= speed;

        angle += speed / 2;

        if(position.y < -60)
        {
            recreate();
        }

        rect.x = (int)position.x;

        rect.y = (int)position.y;

    }
}
