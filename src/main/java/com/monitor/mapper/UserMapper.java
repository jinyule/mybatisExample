package com.monitor.mapper;

import com.monitor.model.User;

/**
 * Created by terminator on 2016/5/20.
 * User: terminator
 * Date: 2016/5/20
 * Time: 22:27
 * Copyright
 */
public interface UserMapper {

    User getUser(Integer id);

    int updateOrSaveUser(User user);
}
