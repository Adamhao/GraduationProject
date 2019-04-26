package cn.edu.qdu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {

	@Resource
    private DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
