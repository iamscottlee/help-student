package com.project.help.dao;

import com.project.help.pojo.Data;
import com.project.help.pojo.DataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    long countByExample(DataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int deleteByExample(DataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int deleteByPrimaryKey(Integer dataId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int insert(Data record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int insertSelective(Data record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    List<Data> selectByExample(DataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    Data selectByPrimaryKey(Integer dataId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int updateByExampleSelective(@Param("record") Data record, @Param("example") DataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int updateByExample(@Param("record") Data record, @Param("example") DataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int updateByPrimaryKeySelective(Data record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data
     *
     * @mbg.generated Wed May 23 21:40:52 CST 2018
     */
    int updateByPrimaryKey(Data record);
}