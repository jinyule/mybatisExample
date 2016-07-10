package com.huawei.neteco.pm.model;

import com.huawei.neteco.pm.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by terminator on 2016/5/20.
 * User: terminator
 * Date: 2016/5/20
 * Time: 22:54
 * Copyright
 */
public class UserTest
{

    private static SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception
    {
        Properties properties = Resources.getResourceAsProperties("jdbc.properties");
        // create a SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
        reader.close();

        // populate in-memory database
        SqlSession session = sqlSessionFactory.openSession();
        Connection conn = session.getConnection();

        reader = Resources.getResourceAsReader("hqldb.sql");
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(null);
        runner.runScript(reader);
        reader.close();
        session.close();
    }

    @Test
    public void shouldGetAUser()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try
        {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(1);
            assertEquals("User1", user.getName());
        } finally
        {
            sqlSession.close();
        }
    }

    @After
    public void tearDown() throws Exception
    {

    }
}