package com.example.mall.member.dao;

import com.example.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author cmp
 * @email meipengchen6@gmail.com
 * @date 2023-08-11 23:23:27
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
