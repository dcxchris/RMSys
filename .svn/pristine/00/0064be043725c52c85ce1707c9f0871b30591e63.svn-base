package com.ctgu.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.ProcParameter;


public interface DAO
{

    public abstract void evict(Object obj);

    public abstract void flush();

    public abstract boolean exists(Class<?> class1, Serializable serializable);

    public abstract int max(Class<?> class1, String s);

    public abstract void delete(Object obj);

    public abstract void delete(Class<?> class1, Serializable serializable);

    public abstract Serializable save(Object obj);

    public abstract void update(Object obj);

    public abstract void savrOrUpdate(Object obj);

    public abstract void merge(Object obj);

    public abstract int updateBulk(String s);

    public abstract int updateBulk(String s, Object obj);

    public abstract int updateBulk(String s, List<?> list);

    public abstract int updateBulk(String s, Object aobj[]);

    public abstract <T> T find(Class<?> class1, Serializable serializable);

    public abstract <T> T findFirst(String s);

    public abstract <T> T findFirst(String s, Object obj);

    public abstract <T> T findFirst(String s, List<?> list);

    public abstract <T> T findFirst(String s, Object aobj[]);

    public abstract <T> List<T> find(Class<?> class1);

    public abstract <T> List<T> find(String s);

    public abstract <T> List<T> find(String s, Object obj);

    public abstract <T> List<T> find(String s, List<?> list);

    public abstract <T> List<T> find(String s, Object aobj[]);

    public abstract <T> List<T> find(String s, int i);

    public abstract <T> List<T> find(String s, int i, Object obj);

    public abstract <T> List<T> find(String s, int i, List<?> list);

    public abstract <T> List<T> find(String s, int i, Object aobj[]);

    public abstract <T> List<T> find(String s, int i, int j);

    public abstract <T> List<T> find(String s, int i, int j, Object obj);

    public abstract <T> List<T> find(String s, int i, int j, List<?> list);

    public abstract <T> List<T> find(String s, int i, int j, Object aobj[]);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, Object obj);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, List<?> list);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, Object aobj[]);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, int i);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, Object obj, int i);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, List<?> list, int i);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, Object aobj[], int i);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, String s1);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, String s1, Object obj);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, String s1, List list);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, String s1, Object aobj[]);

    public abstract <T> FlipPageInfo<T> findWithPaging(FlipPageInfo<T> flippageinfo, String s, Object aobj[], String s1, Object aobj1[]);

    public abstract <T>FlipPageInfo<T> findWithPagingBySql(FlipPageInfo<T> fpi, String sql,Class<T> clazz);
    
    public abstract int findInt(String s);

    public abstract int findInt(String s, Object obj);

    public abstract int findInt(String s, List<?> list);

    public abstract int findInt(String s, Object aobj[]);

    public abstract <T> List<T> findBySql(String s,Class<T> clazz);

    public abstract <T> List<T> findBySql(String s, Object aobj[],Class<T> clazz);
    
    public abstract void excuteBySql(String as[]);
    
    public abstract ResultSet callProc(String sql , List<ProcParameter> param);
}
