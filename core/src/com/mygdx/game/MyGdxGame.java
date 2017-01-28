package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.soap.Text;
import java.nio.BufferUnderflowException;

public class MyGdxGame extends ApplicationAdapter
{
	private SpriteBatch batch;

	private BackGround background;

	private Hero hero;

	private final int ASTER_COUNT = 20;

	private Asteroid[] asteroids;

	private final int BULLET_COUNT = 10;

	public static Bullet[] bullets;

	private Texture textureBullet;
	
	@Override
	public void create ()
	{

		batch = new SpriteBatch();

		background = new BackGround();

		hero = new Hero();

		asteroids = new Asteroid[ASTER_COUNT];

		for(int i = 0; i <  ASTER_COUNT; ++i)
		{
			asteroids[i] = new Asteroid();
		}

		bullets = new Bullet[BULLET_COUNT];

		for(int i = 0; i < BULLET_COUNT; ++i)
		{
			bullets[i] = new Bullet();
		}

		textureBullet = new Texture("bullet.png");
	}

	@Override
	public void render ()
	{
		update();

		Gdx.gl.glClearColor(1, 1, 1, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		background.render(batch);

		hero.render(batch);

		for(int i = 0; i <  ASTER_COUNT; ++i)
		{
			asteroids[i].render(batch);
		}

		for (int i = 0; i < BULLET_COUNT; i++)
		{
			if(bullets[i].isActive())
			{
				batch.draw(textureBullet, bullets[i].getPosition().x, bullets[i].getPosition().y);
			}
		}

		batch.end();
	}

	public void update()
	{
		background.update();

		hero.update();

		for(int i = 0; i <  ASTER_COUNT; ++i)
		{
			asteroids[i].update();
		}

		for (int i = 0; i < BULLET_COUNT; i++)
		{
			if(bullets[i].isActive())
			{
				bullets[i].update();

				for (int j = 0; j < ASTER_COUNT; j++) {
                    if (asteroids[j].getRect().contains(bullets[i].getPosition()))
                    {
                        asteroids[j].recreate();

						bullets[i].destroy();

                        break;
                    }
                }
			}
		}

		for(int j = 0; j < ASTER_COUNT; j++) {
			if (asteroids[j].getRect().contains(hero.getPosition())) {

				hero.recreate();

				break;
			}
		}
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
}
