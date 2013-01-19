package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sanrenxing.dao.IUserAttentionDao;
import com.sanrenxing.dao.mybatis.UserAttentionMapper;
import com.sanrenxing.vos.UserAttention;

import junit.framework.TestCase;

public class UnitTest extends TestCase {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext(
		"applicationContext*.xml");
	private IUserAttentionDao<UserAttention> dao = context.getBean(UserAttentionMapper.class);
	
	public static void main(String[] args) {
		UnitTest ut = new UnitTest();
		ut.test();
	}

	@Test
	public void test() {
		List<UserAttention> list = dao.selectListUserAttention();
		System.out.println(list.size());
	}
	
}
