package com.springapp.mvc.dao;


import java.util.List;

import com.springapp.mvc.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordDao extends CommonDAO<Password> {
    public PasswordDao() {
        super(Password.class);
    }

    public List<Password> getAll(){
        return doGetAll(Password.class);
    }
}
