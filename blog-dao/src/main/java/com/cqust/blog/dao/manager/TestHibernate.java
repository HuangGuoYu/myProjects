package com.cqust.blog.dao.manager;

import com.cqust.blog.common.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/3/22.
 */
@Component
public class TestHibernate {

    @Autowired
    SessionFactory factory;

    public User getUser() {
        return (User) factory.openSession().get(User.class, 7);
    }

}
