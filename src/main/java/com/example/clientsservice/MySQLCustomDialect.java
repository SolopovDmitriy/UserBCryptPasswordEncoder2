package com.example.clientsservice;

import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.stereotype.Component;

//@Component
public class MySQLCustomDialect extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci";
    }
}
