package com.madhu.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author Madhukar Reddy
 *
 */
public class PersonMapper implements ResultSetMapper<Person> {

    public Person map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Person person = new Person();
        person.setId(r.getLong("ID"));
        person.setName(r.getString("NAME"));
        return person;
    }

}
