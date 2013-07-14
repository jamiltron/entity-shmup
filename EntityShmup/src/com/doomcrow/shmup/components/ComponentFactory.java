package com.doomcrow.shmup.components;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public abstract class ComponentFactory {
  
  
  private static final Pool<Vector2> vectorPool = new Pool<Vector2>() {
    @Override
    protected Vector2 newObject() {
      return new Vector2();
    }
  };
  
  private static final Pool<Rectangle> rectanglePool = new Pool<Rectangle>() {
    @Override
    protected Rectangle newObject() {
      return new Rectangle();
    }
  };
  
  private static final Pool<Position> positionPool = new Pool<Position>() {
    @Override
    protected Position newObject() {
      return new Position();
    }
  };
  
  private static final Pool<Bounds> boundsPool = new Pool<Bounds>() {
    @Override
    protected Bounds newObject() {
      return new Bounds();
    }
  };
  
  private static final Pool<Image> imagePool = new Pool<Image>() {
    @Override
    protected Image newObject() {
      return new Image();
    }
  };
  
  private static final Pool<Velocity> velocityPool = new Pool<Velocity>() {
    @Override
    protected Velocity newObject() {
      return new Velocity();
    }
  };
  
  private static final Pool<Dimensions> dimensionsPool = new Pool<Dimensions>() {
    @Override
    protected Dimensions newObject() {
      return new Dimensions();
    }
  };

  public static Position createPosition(float x, float y) {
    Position position = positionPool.obtain();
    Vector2 vector = obtainVector(x, y);
    position.pos = vector;
    return position;
  }
  
  public static Bounds createBounds(float x, float y, float width, float height) {
    Bounds bounds = boundsPool.obtain();
    bounds.rect = obtainRectangle(x, y, width, height);
    return bounds;
  }
  
  public static Image createImage(String name) {
    Image image = imagePool.obtain();
    image.name = name;
    return image;
  }
  
  public static Player createPlayer() {
    return new Player();
  }
  
  public static Velocity createVelocity(float x, float y) {
    Velocity velocity = velocityPool.obtain();
    velocity.vel = obtainVector(x, y);
    return velocity;
  }
  
  public static Dimensions createDimensions(float width, float height) {
    Dimensions dimensions = dimensionsPool.obtain();
    dimensions.width = width;
    dimensions.height = height;
    return dimensions;
  }
  
  private static Vector2 obtainVector(float x, float y) {
    Vector2 vector = vectorPool.obtain();
    vector.x = x;
    vector.y = y;
    return vector;
  }
  
  private static Rectangle obtainRectangle(float x, float y, float width, float height) {
    Rectangle rect = rectanglePool.obtain();
    rect.x = x;
    rect.y = y;
    rect.width = width;
    rect.height = height;
    return rect;
  }
  
  // TODO: implement a "free method" to free stuff back into the object pools

}
