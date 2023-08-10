package com.example.mall.coupoon;

import com.example.mall.coupoon.mapper.TestMapper;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@SpringBootTest
public class TestCursor {
    @Test
    public void testCursor() throws InterruptedException {
        //等待10秒方便jvisualVM监控
        Thread.sleep(10000);
        long start = System.currentTimeMillis();
        try (SqlSession sqlSession = getSqlSession()) {
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            try(Cursor<TestMapper.Person> cursor = testMapper.selectAll()){

                int total = 0;
                for (TestMapper.Person o : cursor) {
                    total++;
                }
                System.out.println("总数: " + total);
            } catch (IOException ignore) {
            }
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        Thread.sleep(10000);
    }


    @Test
    public void testSelectAll() throws InterruptedException {
        //等待10秒方便jvisualVM监控
        Thread.sleep(10000);
        long start = System.currentTimeMillis();
        try (SqlSession sqlSession = getSqlSession()) {
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            List<TestMapper.Person> people = testMapper.selectList();
            System.out.println(people.size());
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        Thread.sleep(10000);
    }

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

//    @BeforeAll
//    public static void init() {
//        try {
//            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//            reader.close();
//        } catch (IOException ignore) {
//            ignore.printStackTrace();
//        }
//    }

    public SqlSession getSqlSession() {
        sqlSessionFactory.getConfiguration().addMapper(TestMapper.class);
        return sqlSessionFactory.openSession();
    }
}
