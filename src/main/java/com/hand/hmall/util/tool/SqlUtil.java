package com.hand.hmall.util.tool;/**
 * Created by Administrator on 2017/6/13.
 */

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author ChenMingjian
 * @create 2017-06-13 20:03
 **/
public class SqlUtil {

    public static String updatePstmt(String tableName, String[] updateColumn) {
        StringBuffer sql = new StringBuffer("update "+tableName+" set ");
        for(int i = 0; i<updateColumn.length-1; i++){
            sql.append(updateColumn[i] + " = ?, ");
        }
        int lastIndex = sql.lastIndexOf(", ");
        System.out.println(sql.delete(lastIndex,lastIndex+2));
        sql.append(" where " + updateColumn[updateColumn.length -1] + " = ? ");
        return sql.toString();
    }

    public static PreparedStatementSetter updatePstmtSetter(Map<String, Object> map, String[] updateColumnParam) {
        return (ps) ->{for(int i = 0; i<updateColumnParam.length; i++){
            ps.setObject(i+1, map.get(updateColumnParam[i]));
        }};
    }

    public static String insertPstmt(String tableName,String tableId, String[] insertColumn) {
        StringBuffer sql = new StringBuffer("insert into "+tableName+" (");

        //列名
        for (String column : insertColumn) {
            sql.append(" " + column + ", ");
        }
        int lastIndex = sql.lastIndexOf(", ");
        System.out.println(sql.delete(lastIndex,lastIndex+2));

        sql.append(" ) values ( ");

        //获取id
        sql.append("(select max(" + tableId + ") + 1 from " + tableName + "), ");

        //占位符
        for(int i = 1; i<insertColumn.length; i++){
            sql.append(" ?, ");
        }
        lastIndex = sql.lastIndexOf(", ");
        System.out.println(sql.delete(lastIndex,lastIndex+2));
        sql.append(" )");
        return sql.toString();
    }
    public static PreparedStatementSetter insertPstmtSetter(Map<String, Object> map, String[] insertColumnParam) {

        return (ps) ->{for(int i = 1; i<insertColumnParam.length; i++){
            ps.setObject(i, map.get(insertColumnParam[i]));
        }};
    }

    public static String deletePstmt(String tableName, String id) {
        return "DELETE FROM "+tableName+" WHERE " + id + " = ? ";
    }

    public static PreparedStatementSetter deletePstmtSetter(Map<String, Object> map, String paramId) {
        return (ps) ->ps.setObject(1,map.get(paramId));
    }
}
