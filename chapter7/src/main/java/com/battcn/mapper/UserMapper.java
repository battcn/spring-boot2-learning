package com.battcn.mapper;

import com.battcn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * t_user 操作
 *
 * @author Levin
 * @since 2018/5/7 0007
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名统计（TODO 假设它是一个很复杂的SQL）
     *
     * @param username 用户名
     * @return 统计结果
     */
    int countByUsername(String username);
}
