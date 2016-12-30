package com.antimeta.game.ormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.entity.Enemy;
import com.antimeta.game.ormlite.entity.Identifiable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseManager <T extends Identifiable> {
    private DatabaseHelper databaseHelper;
    private Dao<T, ?> dao;

    public DatabaseManager(Context context, Class<T> tClass){
        databaseHelper = new DatabaseHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_CODE);
        setDao(tClass);
    }

    public T find(Number id){
        T item = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            builder.where().eq("id", id);
            item = dao.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public T find(String field, Number value){
        T item = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            builder.where().eq(field, value);
            item = builder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public T find(String field, String value){
        T item = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            builder.where().eq(field, value);
            item = builder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public T find(String field, Boolean value){
        T item = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            builder.where().eq(field, value);
            item = builder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public T findFirst(){
        T item = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            item = dao.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


    public List<T>  findMultiple(Collection<Integer> ids){
        List<T> resultList = new ArrayList<>();
        for(Integer id: ids){
            resultList.add(find(id));
        }
        return resultList;
    }

    public List<T>  findMultiple(String field, Number value){
        List<T> list = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            builder.where().eq(field, value);
            list = builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T>  findMultiple(String field, String value){
        List<T> list = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            builder.where().eq(field, value);
            list = builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T>  findMultiple(String field, Boolean value){
        List<T> list = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            builder.where().eq(field, value);
            list = builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T> findAll(){
        List<T> list = null;
        try {
            QueryBuilder<T, ?> builder = dao.queryBuilder();
            list = builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add(T item){
        try {
            dao.create(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMultiple(Collection<T> items){
        for (T item : items) {
            add(item);
        }
    }

    public void save(T item){
        try {
            dao.createOrUpdate(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMultiple(Collection<T> items){
        for (T item : items) {
            save(item);
        }
    }

    public void deleteAll(){
        try {
            TableUtils.clearTable(dao.getConnectionSource(), Enemy.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(T item){
        try {
            dao.delete(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Number id){
        try {
            DeleteBuilder<T, ?> builder = dao.deleteBuilder();
            builder.where().eq("id", id);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String field, Number value){
        try {
            DeleteBuilder<T, ?> builder = dao.deleteBuilder();
            builder.where().eq(field, value);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String field, String value){
        try {
            DeleteBuilder<T, ?> builder = dao.deleteBuilder();
            builder.where().eq(field, value);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String field, Boolean value){
        try {
            DeleteBuilder<T, ?> builder = dao.deleteBuilder();
            builder.where().eq(field, value);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMultiple(Collection<T> items){
        for(T item : items){
            delete(item);
        }
    }

    public void refresh(T entity){
        try {
            dao.refresh(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public QueryBuilder<T, ?> getBuilder(){
        return dao.queryBuilder();
    }

    public DeleteBuilder<T, ?> getDeleteBuilder(){
        return dao.deleteBuilder();
    }

    public Dao<T, ?> getDao(){
        return dao;
    }

    private void setDao(Class<T> tClass){
        try {
            dao = databaseHelper.getDao(tClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
