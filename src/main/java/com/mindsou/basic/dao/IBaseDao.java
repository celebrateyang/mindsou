package com.mindsou.basic.dao;

import model.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by 竹庆 on 2016/9/20.
 */
public interface IBaseDao<T> {

    public T add(T t);
    public void update(T t);
    public void delete(int id);
    public T load(int id);

    /**
     * 不分页列表对象
     * @param hql
     * @param args
     * @return
     */
    public List<T> list(String hql,Object[] args);
    public List<T> list(String hql, Object arg);
    public List<T> list(String hql);
    public List<T> list(String hql,Object[] args,Map<String,Object> alias);
    public List<T> list(String hql,Map<String,Object> alias);

    /**
     * 分页列表对象
     */
    public Pager<T> find(String hql, Object[] args);
    public Pager<T> find(String hql,Object arg);
    public Pager<T> find(String hql);
    public Pager<T> find(String hql,Object[] args,Map<String,Object> alias);
    public Pager<T> find(String hql,Map<String,Object> alias);

    /**
     * 根据hql查询一组对象
     * @param hql
     * @param args
     * @return
     */
    public Object queryObject(String hql,Object[] args);
    public Object queryObject(String hql,Object arg);
    public Object queryObject(String hql);
    public Object queryObject(String hql,Object[] args,Map<String,Object> alias);
    public Object queryObject(String hql,Map<String,Object> alias);


    /**
     * 根据hql更新对象
     * @param hql
     * @param args
     */
    public void updateByHql(String hql,Object[] args);
    public void updateByHql(String hql,Object arg);
    public void updateByHql(String hql);

    /**
     * 根据sql查询对象，不包含关联对象 不分页
     * @param sql
     * @param args
     * @param clazz 查询的实体对象
     * @param hasEntity 该对象是否是一个Hibernate管理的实体对象，如果不是需要使用setResutltTransform查询
     * @return 一组对象
     */
    public <N extends Object>List<N> listBySql(String sql,Object[] args,Class<?> clazz,boolean hasEntity);
    public <N extends Object>List<N> listBySql(String sql,Object arg,Class<?> clazz,boolean hasEntity);
    public <N extends Object>List<N> listBySql(String sql,Class<?> clazz,boolean hasEntity);
    public <N extends Object>List<N> listBySql(String sql,Object[] args,Class<?> clazz,Map<String,Object> alias,boolean hasEntity);
    public <N extends Object>List<N> listBySql(String sql,Class<?> clazz,Map<String,Object> alias,boolean hasEntity);

    /**
     * 根据sql查询对象，不包含关联对象 分页
     * @param sql
     * @param args
     * @param clazz 查询的实体对象
     * @param hasEntity 该对象是否是一个Hibernate管理的实体对象，如果不是需要使用setResutltTransform查询
     * @return 一组对象
     */
    public <N extends Object>Pager<N> findBySql(String sql,Object[] args,Class<?> clazz,boolean hasEntity);
    public <N extends Object>Pager<N> findBySql(String sql,Object arg,Class<?> clazz,boolean hasEntity);
    public <N extends Object>Pager<N> findBySql(String sql,Class<?> clazz,boolean hasEntity);
    public <N extends Object>Pager<N> findBySql(String sql,Object[] args,Class<?> clazz,Map<String,Object> alias,boolean hasEntity);
    public <N extends Object>Pager<N> findBySql(String sql,Class<?> clazz,Map<String,Object> alias,boolean hasEntity);
}
