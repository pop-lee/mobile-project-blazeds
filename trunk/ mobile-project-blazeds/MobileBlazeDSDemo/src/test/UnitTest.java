package test;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.sanrenxing.dao.IBackyardUserDao;
import com.sanrenxing.dao.IUserAttentionDao;
import com.sanrenxing.services.PushNotificationService;
import com.sanrenxing.vos.BackyardUser;
import com.sanrenxing.vos.UserAttention;
@RunWith(SpringJUnit4ClassRunner.class)//specify the test container
@ContextConfiguration(locations = { "/applicationContext-Dao.xml" ,"/applicationContext-Service.xml"})
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class,
DirtiesContextTestExecutionListener.class,
TransactionalTestExecutionListener.class })//register listeners
public class UnitTest {
	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private IUserAttentionDao<UserAttention> userAttentionDao;
	@Autowired
	private IBackyardUserDao<BackyardUser> backyardUserDao;
	
	@Test
	public void pushNotification() {
		pushNotificationService.pushNotification();
	}
	
	@Test
	public void selectListUserAttention() {
		List<UserAttention> list = userAttentionDao.selectListUserAttention();
		System.out.println(list.size());
	}
	
	@Test
	public void selectBackyardUser() {
		List<BackyardUser> list = backyardUserDao.selectUserByName("aa");
		System.out.println(list.size());
	}
	
//	@Autowired
//	private IUserAttentionDao<UserAttention> userAttentionDao;
//	@Autowired
//	private IUserDao<User> userDao;
	
//	@BeforeClass
//	public static void beforeClass() {
//		System.out.println("@BeforeClass");
//	}
//	@Before
//	public void before() {
//		System.out.println("@Before");
//	}
//	// e.g @Test(timeout = milliseconds, expected= Exception class)
//	@Test(timeout = 1)
//	public void testGetTradableCcy() {
//		System.out.println("OK"); 
//	}
//	@Ignore
//	@Test
//	public void testIgnore() {
//		System.out.println("@Ignore");
//	}
//	@After
//	public void after() {
//		System.out.println("@After"); 
//	}
//	@AfterClass
//	public static void afterClass() {
//		System.out.println("@AfterClass");
//	}
	
}
