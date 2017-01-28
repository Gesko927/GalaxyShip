package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Gesko927 on 17/12/2016.
 */
public class BackGround {

    class Star
    {
        private Vector2 position;

        private float speed;

        public Star()
        {
            position = new Vector2((float) Math.random() * 1280, (float) Math.random() * 720);

            speed = 2.0f + (float) Math.random() * 7.0f;
        }

        public void update()
        {
            position.y -= speed;

            if(position.y < -12)
            {
                position.x = (float) Math.random() * 1280;

                position.y = 720;
            }
        }
    }

    private Texture texture;

    private Texture starTexture;

    private Star[] stars;

    private final int STARS_COUNT = 1000;

    public BackGround()
    {
        texture = new Texture("stars.jpg");

        starTexture = new Texture("star12.tga");

        stars = new Star[STARS_COUNT];

        for(int i = 0; i < STARS_COUNT; ++i)
        {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(texture, 0 , 0);

        for(int i = 0; i < STARS_COUNT; ++i)
        {
            batch.draw(starTexture, stars[i].position.x, stars[i].position.y);
        }
    }

    public void update()
    {
        for(int i = 0; i < STARS_COUNT; ++i)
        {
            stars[i].update();
        }
    }
}
