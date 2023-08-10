package com.example.mall.coupoon.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

public interface TestMapper {
    class Person{
        private String name;
        private String address;
        private int age;
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    @Select("select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table")
    @Options(fetchSize = Integer.MIN_VALUE)
    Cursor<Person> selectAll();

    @Select("select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table union all " +
            "select * from mydatabase.test_table")
    List<Person> selectList();
}
