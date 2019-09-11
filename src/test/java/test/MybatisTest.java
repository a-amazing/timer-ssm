package test;

import java.util.List;

import com.how2java.mapper.WxTimetaskMapper;
import com.how2java.pojo.WxTimetask;
import com.how2java.pojo.WxTimetaskExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

	@Autowired
	private CategoryMapper categoryMapper;
		@Autowired
private WxTimetaskMapper timetaskMapper;
	@Test
	public void testList() {
		PageHelper.offsetPage(0, 5);
		List<Category> cs=categoryMapper.list();
		System.out.println(cs.getClass());
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		System.out.println(new PageInfo(cs).getTotal());
	}

	@Test
	public void testSelect(){
		WxTimetaskExample example = new WxTimetaskExample();
		WxTimetaskExample.Criteria criteria = example.createCriteria();
		criteria.andJobStatusEqualTo("1");
		List<WxTimetask> timetasks = timetaskMapper.selectByExample(example);

		System.out.println(timetasks);
	}

}
