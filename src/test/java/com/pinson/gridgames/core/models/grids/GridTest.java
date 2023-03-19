package com.pinson.gridgames.core.models.grids;

import com.pinson.gridgames.core.exceptions.NonPositiveValueException;
import com.pinson.gridgames.core.models.grids.exceptions.GridIndexOutOfBoundsException;
import com.pinson.gridgames.core.models.tiles.ITile;
import com.pinson.gridgames.core.models.tiles.Tile;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class GridTest {

    @Test
    public void testConstructor_ColumnsRows() throws NonPositiveValueException {
        IGrid grid = new Grid(3, 5);
        assertEquals(5, grid.getColumns());
        assertEquals(3, grid.getRows());

        this.assertValidTilePositions(grid);
    }

    @Test
    public void testConstructor_ColumnsRows_Exceptions() {
        assertThrowsExactly(NonPositiveValueException.class, () -> new Grid(1, -1));
        assertThrowsExactly(NonPositiveValueException.class, () -> new Grid(1, 0));
        assertThrowsExactly(NonPositiveValueException.class, () -> new Grid(-1, 1));
        assertThrowsExactly(NonPositiveValueException.class, () -> new Grid(0, 1));
    }

    @Test
    public void testConstructor_Tiles() {
        ArrayList<ArrayList<ITile>> tiles = new ArrayList<>();

        /*
        *   3x3
        *
        *   *
        *   ***
        *   **
        *
        * */

        ArrayList<ITile> row = new ArrayList<>();
        row.add(0, new Tile(0, 0));
        tiles.add(row);

        row = new ArrayList<>();
        row.add(0, new Tile(1, 0));
        row.add(1, new Tile(1, 1));
        row.add(2, new Tile(1, 2));
        tiles.add(row);

        row = new ArrayList<>();
        row.add(0, new Tile(2, 0));
        row.add(1, new Tile(2, 1));
        tiles.add(row);

        IGrid grid = new Grid(tiles);

        assertEquals(tiles, grid.getTiles());
        assertEquals(3, grid.getColumns());
        assertEquals(3, grid.getRows());

        this.assertValidTilePositions(grid);
    }

    @Test
    public void testGetColumns() throws NonPositiveValueException {
        IGrid grid = new Grid(5, 10);
        assertEquals(10, grid.getColumns());
    }

    @Test
    public void testSetColumns_ShouldGrowGrid() throws NonPositiveValueException {
        IGrid grid = new Grid(2, 2);
        grid.setColumns(4);

        ArrayList<ArrayList<ITile>> tiles = grid.getTiles();

        assertEquals(4, grid.getColumns());
        assertEquals(2, grid.getRows());
        assertEquals(2, tiles.size());
        assertEquals(4, tiles.get(0).size());

        this.assertValidTilePositions(grid);
    }

    @Test
    public void testSetColumns_ShouldShrinkGrid() throws NonPositiveValueException {
        IGrid grid = new Grid(2, 4);
        grid.setColumns(2);

        ArrayList<ArrayList<ITile>> tiles = grid.getTiles();

        assertEquals(2, grid.getColumns());
        assertEquals(2, grid.getRows());
        assertEquals(2, tiles.size());
        assertEquals(2, tiles.get(0).size());

        this.assertValidTilePositions(grid);
    }

    @Test()
    public void testSetColumns_Exceptions() throws NonPositiveValueException {
        IGrid grid = new Grid(2, 2);

        assertThrowsExactly(NonPositiveValueException.class, () -> grid.setColumns(-1));
        assertThrowsExactly(NonPositiveValueException.class, () -> grid.setColumns(0));
    }

    @Test
    public void testGetRows() throws NonPositiveValueException {
        IGrid grid = new Grid(5, 10);
        assertEquals(5, grid.getRows());
    }

    @Test
    public void testSetRows_ShouldGrowGrid() throws NonPositiveValueException {
        IGrid grid = new Grid(2, 2);
        grid.setRows(4);

        ArrayList<ArrayList<ITile>> tiles = grid.getTiles();

        assertEquals(2, grid.getColumns());
        assertEquals(4, grid.getRows());
        assertEquals(4, tiles.size());
        assertEquals(2, tiles.get(0).size());

        this.assertValidTilePositions(grid);
    }

    @Test
    public void testSetRows_ShouldShrinkGrid() throws NonPositiveValueException {
        IGrid grid = new Grid(4, 2);
        grid.setRows(2);

        ArrayList<ArrayList<ITile>> tiles = grid.getTiles();

        assertEquals(2, grid.getColumns());
        assertEquals(2, grid.getRows());
        assertEquals(2, tiles.size());
        assertEquals(2, tiles.get(0).size());

        this.assertValidTilePositions(grid);
    }

    @Test()
    public void testSetRows_Exceptions() throws NonPositiveValueException {
        IGrid grid = new Grid(2, 2);
        assertThrowsExactly(NonPositiveValueException.class, () -> grid.setRows(-1));
        assertThrowsExactly(NonPositiveValueException.class, () -> grid.setRows(0));
    }

    @Test
    public void testGetTileAt() throws NonPositiveValueException, GridIndexOutOfBoundsException {
        Grid grid = new Grid(5, 10);
        ITile tile = grid.getTileAt(3, 2);
        assertNotNull(tile);
        assertEquals(3, tile.getRow());
        assertEquals(2, tile.getColumn());

        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.getTileAt(-1, 9));
        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.getTileAt(5, 9));
        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.getTileAt(0, -1));
        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.getTileAt(0, 10));
    }

    @Test
    public void testSetTileAt() throws NonPositiveValueException {
        // Create a new grid.
        IGrid grid = new Grid(2, 3);

        // Set a tile at (0, 0).
        ITile tile1 = new Tile(0, 0);
        grid.setTileAt(tile1, 0, 0);
        assertEquals(tile1, grid.getTileAt(0, 0));

        // Set a tile at (1, 2).
        ITile tile2 = new Tile(1, 2);
        grid.setTileAt(tile2, 1, 2);
        assertEquals(tile2, grid.getTileAt(1, 2));

        this.assertValidTilePositions(grid);

        // exceptions
        ITile tile3 = new Tile(2, 1);
        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.setTileAt(tile3, -1, 2));
        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.setTileAt(tile3, 2, 2));
        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.setTileAt(tile3, 0, -1));
        assertThrowsExactly(GridIndexOutOfBoundsException.class, () -> grid.setTileAt(tile3, 0, 3));
    }

    /**
     * Validates every tile of a grid.
     *
     * @param grid IGrid
     */
    private void assertValidTilePositions(IGrid grid) {
        ArrayList<ArrayList<ITile>> tiles = grid.getTiles();
        int nbRows = grid.getRows();

        for (int row = 0; row < nbRows; ++row) {
            ArrayList<ITile> rowList = tiles.get(row);
            int nbCols = grid.getColumns();

            for (int col = 0; col < nbCols; ++col) {
                ITile tile = rowList.get(col);
                assertNotNull(tile);
                assertEquals(col, tile.getColumn());
                assertEquals(row, tile.getRow());
            }
        }
    }
}
