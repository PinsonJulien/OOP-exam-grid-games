package com.pinson.gridgames.core.tiles;

import com.pinson.gridgames.core.mvc.models.Model;

import java.util.Objects;

public class Tile extends Model implements ITile {
    private int row;
    private int column;

    public Tile(int row, int column) {
        this.setRow(row);
        this.setColumn(column);
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return row == tile.row && column == tile.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
