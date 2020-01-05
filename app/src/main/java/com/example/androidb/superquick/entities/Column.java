package com.example.androidb.superquick.entities;

import com.example.androidb.superquick.General.UserSessionData;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

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

   //columnQueries
   public static List<Row> getColumnRows(int columnId){

       List<Row> parsedRows=new ArrayList<>();
       ParseQuery<Row> queryRows = ParseQuery.getQuery("Row");
       queryRows.whereEqualTo("row_columnId", columnId);
       queryRows.orderByAscending("rowId");
       try {
           parsedRows = queryRows.find();
       } catch (
               ParseException e) {
           e.printStackTrace();
       }
       return parsedRows;


   }


}
