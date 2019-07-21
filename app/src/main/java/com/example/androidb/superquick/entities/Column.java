package com.example.androidb.superquick.entities;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Column")
public class Column extends ParseObject {
private int columnId;
private int column_superId;

    public Column(){}

    public Column(int columnId, int column_superId) {
        setColumnId(columnId);
        setColumn_superId(column_superId);
    }

    public int getColumnId() {
        return getInt("columnId");
    }

    public void setColumnId(int columnId) {
        put("columnId", columnId);    }

    public int getColumn_superId() {
        return getInt("column_superId");
    }

    public void setColumn_superId(int column_superId) {
        put("column_superId", column_superId);
    }
}
