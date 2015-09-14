package com.madhu.jdbi;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import com.madhu.core.Person;
import com.madhu.core.PersonMapper;

/**
 * @author Madhukar Reddy
 *
 */
@RegisterMapper(PersonMapper.class)
public interface PersonDAO {
    
    @SqlUpdate("create table if not exists PERSON (ID int primary key, NAME varchar(100))")
    void createPersonTable();
    
    @SqlQuery("select * from PERSON")
    List<Person> getAll();

    @SqlQuery("select * from PERSON where ID = :id")
    Person findById(@Bind("id") int id);

    @SqlUpdate("delete from PERSON where ID = :id")
    int deleteById(@Bind("id") int id);

    @SqlUpdate("update PERSON set NAME = :name where ID = :id")
    int update(@BindBean Person person);

    @SqlUpdate("insert into PERSON (ID, NAME) values (:id, :name)")
    int insert(@BindBean Person person);

}
