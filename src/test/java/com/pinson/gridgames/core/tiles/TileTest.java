package com.pinson.gridgames.core.tiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TileTest {

    @Test
    public void testConstructor() {
        // Test the constructor and getters of Tile class
        ITile tile = new Tile(1, 2);
        assertEquals(1, tile.getRow());
        assertEquals(2, tile.getColumn());
    }

    @Test
    public void testGettersSetters() {
        // Test the setters of Tile class
        ITile tile = new Tile(1, 2);
        tile.setRow(3);
        tile.setColumn(4);
        assertEquals(3, tile.getRow());
        assertEquals(4, tile.getColumn());
    }

    @Test
    public void testEquals() {
        // Test the equals method of Tile class
        ITile tile1 = new Tile(1, 2);
        ITile tile2 = new Tile(1, 2);
        ITile tile3 = new Tile(2, 2);
        ITile tile4 = new Tile(1, 3);
        assertEquals(tile1, tile2);
        assertNotEquals(tile1, tile3);
        assertNotEquals(tile1, tile4);
    }

    @Test
    public void testHashCode() {
        // Test the hashCode method of Tile class
        ITile tile1 = new Tile(1, 2);
        ITile tile2 = new Tile(1, 2);
        assertEquals(tile1.hashCode(), tile2.hashCode());
    }
}
