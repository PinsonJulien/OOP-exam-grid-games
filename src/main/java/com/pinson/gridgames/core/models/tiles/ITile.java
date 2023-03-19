package com.pinson.gridgames.core.models.tiles;

public interface ITile {
    int getRow();
    ITile setRow(int row);

    int getColumn();
    ITile setColumn(int column);
}