package com.mindsou.dao;

import com.mindsou.IDao.IMindDao;
import com.mindsou.basic.dao.BaseDao;
import com.mindsou.basic.dao.IBaseDao;
import com.mindsou.entity.Mind;
import org.springframework.stereotype.Repository;

/**
 * Created by 竹庆 on 2016/9/20.
 */
@Repository("mindDao")
public class MindDao extends BaseDao<Mind>
        implements IMindDao
{
}
