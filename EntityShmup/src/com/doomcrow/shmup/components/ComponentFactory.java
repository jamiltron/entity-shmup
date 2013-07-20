package com.doomcrow.shmup.components;

import com.artemis.Component;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.graphics.Color;
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
  
  private static final Pool<FireRate> fireRatePool = new Pool<FireRate>() {
	  @Override
	  protected FireRate newObject() {
      return new FireRate();
	  }
  };
  
  private static final Pool<Color> colorPool = new Pool<Color>() {
    @Override
    protected Color newObject() {
      return new Color();
    }
  };
  
  private static final Pool<Text> textPool = new Pool<Text>() {
    @Override
    protected Text newObject() {
      return new Text();
    }
  };

  public static Position createPosition(float x, float y) {
    Position position = positionPool.obtain();
    Vector2 vector = obtainVector(x, y);
    position.pos = vector;
    return position;
  }
  
  public static Flicker createFlicker(float flickerRate) {
    Flicker flicker = new Flicker();
    flicker.flickerRate = flickerRate;
    flicker.currentTime = 1f;
    return flicker;
  }
  
  public static Bounds createBounds(float x, float y, float width, float height) {
    return createBounds(x, y, width, height, 0, 0);
  }
  
  public static Bounds createBounds(float x, float y, float width, float height, float xOffset, float yOffset) {
    Bounds bounds = boundsPool.obtain();
    bounds.rect = obtainRectangle(x, y, width, height);
    bounds.xOffset = xOffset;
    bounds.yOffset = yOffset;
    return bounds;
  }
  
  public static Image createImage(String name) {
    Image image = imagePool.obtain();
    image.name = name;
    return image;
  }
  
  public static Player createPlayer() {
    // TODO: Pool for new games? 
    return new Player();
  }
  
  public static Text createText(String str, String name) {
    Text text =  textPool.obtain();
    text.fontName = name;
    text.str = str;
    text.color = obtainColor(1f, 1f, 1f, 1f);
    return text;
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
  
  private static Color obtainColor(float r, float g, float b, float a) {
    Color color = colorPool.obtain();
    color.set(r, g, b, a);
    return color;
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
  
  public static FireRate createFireRate(float rate) {
    FireRate fireRate = fireRatePool.obtain();
    fireRate.currentTime = 0f;
    fireRate.rate = rate;
    return fireRate;
  }

  public static void freeComponent(Component component) {
    if (component instanceof Velocity) {
      freeVelocity((Velocity) component);
    } else if (component instanceof Image) {
      freeImage((Image) component);
    } else if (component instanceof Dimensions) {
      freeDimensions((Dimensions) component);
    } else if (component instanceof Bounds) {
      freeBounds((Bounds) component);
    } else if (component instanceof Position) {
      freePosition((Position) component);
    } else if (component instanceof FireRate) {
      freeFireRate((FireRate) component);
    } else if (component instanceof Player) {
      // no-op
    } else if (component instanceof Text) {
      // no-op
    } else {
      throw new IllegalArgumentException("Illegal component " + component.toString());
    }
  }
  
  private static void freeFireRate(FireRate fireRate) {
    fireRatePool.free(fireRate);
  }
  
  private static void freeVelocity(Velocity velocity) {
    freeVector(velocity.vel);
    velocityPool.free(velocity);
  }
  
  private static void freeImage(Image image) {
    imagePool.free(image);
  }
  
  private static void freeDimensions(Dimensions dimensions) {
    dimensionsPool.free(dimensions);
  }
  
  private static void freePosition(Position position) {
    freeVector(position.pos);
    positionPool.free(position);
  }
  
  private static void freeBounds(Bounds bounds) {
    freeRectangle(bounds.rect);
    boundsPool.free(bounds);
  }
  
  private static void freeVector(Vector2 vector) {
    vectorPool.free(vector);
  }
  
  private static void freeRectangle(Rectangle rectangle) {
    rectanglePool.free(rectangle);
  }

}
