package spring5_webmvc_mybatis_study.mapper;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring5_webmvc_mybatis_study.config.ContextRoot;
import spring5_webmvc_mybatis_study.dto.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberMapperTest {
	protected static final Log log = LogFactory.getLog(MemberMapperTest.class);
	
	@Autowired
	private MemberMapper mapper;

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01InsertMember() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Member member = new Member();
		member.setEmail("test4@test.com");
		member.setName("test4");
		member.setPassword("123456");
		
		int res = mapper.insertMember(member);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test02SelectMemberByEmail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
			
		Member selMember = mapper.selectMemberByEmail("test@test.com");
		Assert.assertNotNull(selMember);
		System.out.println(selMember);
	}
}
