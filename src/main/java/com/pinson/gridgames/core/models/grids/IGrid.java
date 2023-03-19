package com.pinson.gridgames.core.models.grids;

import com.pinson.gridgames.core.exceptions.NonPositiveValueException;
import com.pinson.gridgames.core.models.grids.exceptions.GridIndexOutOfBoundsException;
import com.pinson.gridgames.core.models.tiles.ITile;

import java.util.ArrayList;

public interface IGrid {
    ArrayList<ArrayList<ITile>> getTiles();
    IGrid setTiles(ArrayList<ArrayList<ITile>> tiles);

    int getColumns();
    IGrid setColumns(int columns) throws NonPositiveValueException;

    int getRows();
    IGrid setRows(int rows) throws NonPositiveValueException;

    IGrid resize (int rows, int columns) throws NonPositiveValueException;

    ITile getTileAt(int row, int column) throws GridIndexOutOfBoundsException;

    IGrid setTileAt(ITile tile, int row, int column) throws GridIndexOutOfBoundsException;
}
