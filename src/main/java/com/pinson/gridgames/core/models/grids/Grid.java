package com.pinson.gridgames.core.models.grids;

import com.pinson.gridgames.core.exceptions.NonPositiveValueException;
import com.pinson.gridgames.core.models.grids.exceptions.GridIndexOutOfBoundsException;
import com.pinson.gridgames.core.mvc.models.Model;
import com.pinson.gridgames.core.models.tiles.ITile;
import com.pinson.gridgames.core.models.tiles.Tile;

import java.util.ArrayList;

/**
 * The `Grid` class represents a rectangular grid of tiles.
 */
public class Grid extends Model implements IGrid {
    private ArrayList<ArrayList<ITile>> tiles;
    private int columns = 0; // x
    private int rows = 0; // y

    /**
     * Constructs a new grid with the specified list of tiles.
     *
     * @param tiles the array list of tiles to use for the grid
     */
    public Grid (ArrayList<ArrayList<ITile>> tiles) {
        this.setTiles(tiles);
    }

    /**
     * Constructs a new grid with the specified number of columns and rows.
     * Each tile in the grid is initialized with a default value.
     *
     * @param rows the number of rows in the grid.
     * @param columns the number of columns in the grid.
     * @throws NonPositiveValueException if the columns or rows parameter is not positive.
     */
    public Grid (int rows, int columns) throws NonPositiveValueException {
        if (columns <= 0)
            throw new NonPositiveValueException("Columns must be positive.");
        if (rows <= 0)
            throw new NonPositiveValueException("Rows must be positive.");

        final ArrayList<ArrayList<ITile>> tiles = new ArrayList<>();

        for (int row = 0; row < rows; ++row) {
            ArrayList<ITile> rowList = new ArrayList<>();

            for (int col = 0; col < columns; ++col) {
                rowList.add(new Tile(row, col));
            }

            tiles.add(rowList);
        }

        this.columns = columns;
        this.rows = rows;
        this.tiles = tiles;
    }

    /**
     * Returns the list of tiles in the grid.
     *
     * @return the list of tiles in the grid
     */
    @Override
    public ArrayList<ArrayList<ITile>> getTiles() {
        return this.tiles;
    }

    /**
     * Sets the list of tiles in the grid.
     * Sets the new rows and columns.
     * the missing tiles are filled with default values.
     *
     * @param tiles the new list of tiles to use for the grid
     * @return This grid instance, to allow method chaining.
     */
    @Override
    public IGrid setTiles(ArrayList<ArrayList<ITile>> tiles) {
        this.tiles = tiles;

        this.rows = tiles.size();

        // find bigger column size.
        this.columns = 0;
        for (ArrayList<ITile> row: tiles) {
            int size = row.size();
            if (size > this.columns)
                this.columns = size;
        }

        // Fill the missing tiles
        for (int row = 0; row < this.rows; ++row) {
            ArrayList<ITile> rowList = tiles.get(row);
            int size = rowList.size();
            for (int col = size; col < this.columns; ++col) {
                rowList.add(new Tile(row, col));
            }
        }

        this.tiles = tiles;

        return this;
    }

    /**
     * Returns the number of columns in this grid.
     *
     * @return The number of columns in this grid.
     */
    @Override
    public int getColumns() {
        return this.columns;
    }

    /**
     * Sets the number of columns in this grid.
     * If the column size increased, create columns with basic tiles to the existing rows.
     * If the column size decreased, remove columns from the existing rows.
     *
     * @param columns The new number of columns for this grid.
     * @return This grid instance, to allow method chaining.
     * @throws NonPositiveValueException If the given number of columns is not positive.
     */
    @Override
    public IGrid setColumns (int columns) throws NonPositiveValueException {
        return this.resize(this.rows, columns);
    }

    /**
     * Returns the number of rows in this grid.
     *
     * @return The number of rows in this grid.
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * Sets the number of rows in this grid.
     * If the row size increased, create all columns with basic tiles.
     * If the row size decreased, remove the excess rows.
     *
     * @param rows The new number of rows for this grid.
     * @return This grid instance, to allow method chaining.
     * @throws NonPositiveValueException If the given number of rows is not positive.
     */
    @Override
    public IGrid setRows (int rows) throws NonPositiveValueException {
        return this.resize(rows, this.columns);
    }

    /**
     * Resizes the grid to the specified number of rows and columns.
     * @param rows The number of rows to resize the grid to
     * @param columns The number of columns to resize the grid to
     * @return This grid instance, to allow method chaining.
     * @throws NonPositiveValueException If either the number of rows or columns is less than or equal to zero
     */
    @Override
    public IGrid resize (int rows, int columns) throws NonPositiveValueException {
        if (rows <= 0)
            throw new NonPositiveValueException("Number of rows must be positive.");
        if (columns <= 0)
            throw new NonPositiveValueException("Number of columns must be positive.");

        this.rows = rows;
        this.columns = columns;

        // Add or remove rows as necessary
        int rowSize = this.tiles.size();

        if (rowSize < this.rows) {
            for (int row = rowSize; row < this.rows; ++row) {
                this.tiles.add(new ArrayList<>());
            }
        }
        else if (rowSize > this.rows) {
            this.tiles.subList(this.rows, rowSize).clear();
        }

        // Add or remove columns from rows.
        for (int row = 0; row < this.rows; ++row) {
            ArrayList<ITile> rowList = this.tiles.get(row);
            rowSize = rowList.size();

            if (rowSize < this.columns) {
                for (int col = rowSize; col < this.columns; ++col) {
                    rowList.add(new Tile(row, col));
                }
            }
            else if (rowSize > this.columns) {
                rowList.subList(this.columns, rowSize).clear();
            }
        }

        return this;
    }


    /**
     * Returns the tile at the given row and column coordinates.
     *
     * @param row The row coordinate of the tile to return.
     * @param column The column coordinate of the tile to return.
     * @return The tile at the given row and column coordinates.
     * @throws GridIndexOutOfBoundsException If the given row or column index is out of bounds. (less than 0 or higher than the rows / columns max)
     */
    @Override
    public ITile getTileAt(int row, int column) throws GridIndexOutOfBoundsException {
        if (row < 0 || row >= this.rows)
            throw new GridIndexOutOfBoundsException("Row must be within grid bounds.");
        if (column < 0 || column >= this.columns)
            throw new GridIndexOutOfBoundsException("Column must be within grid bounds.");

        return this.tiles.get(row).get(column);
    }

    /**
     * Sets the tile at the given row and column coordinates to the given tile.
     *
     * @param tile The new tile to set at the given row and column coordinates.
     * @param row The row coordinate of the tile to set.
     * @param column The column coordinate of the tile to set.
     * @return This grid instance, to allow method chaining.
     * @throws GridIndexOutOfBoundsException If the given row or column index is out of bounds. (less than 0 or higher than the rows / columns max)
     */
    @Override
    public IGrid setTileAt(ITile tile, int row, int column) throws GridIndexOutOfBoundsException {
        if (row < 0 || row >= this.rows)
            throw new GridIndexOutOfBoundsException("Row must be within grid bounds.");
        if (column < 0 || column >= this.columns)
            throw new GridIndexOutOfBoundsException("Column must be within grid bounds.");

        // Change the tile row / column.
        tile.setRow(row);
        tile.setColumn(column);

        this.tiles.get(row).set(column, tile);

        return this;
    }
}
