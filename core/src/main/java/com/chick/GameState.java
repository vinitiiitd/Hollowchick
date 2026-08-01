package com.chick;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    public int currentBirdIndex;
    public ArrayList<Vector2> bird_p;
    public ArrayList<Vector2> pig_p;
    public ArrayList<Boolean> pig_s; // true if alive, false if destroyed
    public ArrayList<Vector2> block_p;
    public ArrayList<Boolean> block_s; // true if intact, false if destroyed
    public int remainingBirds;
}
