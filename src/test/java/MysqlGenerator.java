/**
 * Created by Administrator on 2017/7/10.
 */

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author lierl
 * @create 2017-07-10 9:57
 **/
public class MysqlGenerator {
	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("E:\\intellJWork\\scf-bms\\src\\main\\java\\");
//		gc.setOutputDir("E:\\mp\\");
		gc.setFileOverride(true);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(false);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("lierl");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！

		gc.setMapperName("%sMapper");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				// 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
				return super.processTypeConvert(fieldType);
			}
		});

		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/elastic?characterEncoding=utf8");
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();

		strategy.setTablePrefix(new String[]{"scf"});// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		//,"scf_role","scf_role_menu","scf_user_role","scf_resource"
		strategy.setInclude(new String[]{"scf_resource"}); // 需要生成的表
//        strategy.setExclude(new String[]{"test"}); // 排除生成的表
		strategy.setEntityBuilderModel(false);
		// 自定义 mapper 父类
		strategy.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper");
		// 自定义 service 父类
		strategy.setSuperServiceClass("com.lierl.api.service.IBaseService");
		// 自定义 service 实现类父类
		strategy.setDbColumnUnderline(true);
		mpg.setStrategy(strategy);
		strategy.setSuperServiceImplClass("com.lierl.api.service.impl.BaseServiceImpl");

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.lierl");
		pc.setController("controller");
//		pc.setEntity("entity");
//		pc.setService("service");
//		pc.setServiceImpl("service.impl");
		pc.setModuleName("api");
		mpg.setPackageInfo(pc);

		// 关闭默认 xml 生成，调整生成 至 根目录
		TemplateConfig tc = new TemplateConfig();

		tc.setController("/template/controller.java.vm");
		tc.setEntity("/template/entity.java.vm");
		tc.setMapper("/template/mapper.java.vm");
		tc.setServiceImpl("/template/serviceImpl.java.vm");
		tc.setService("/template/service.java.vm");

        tc.setXml(null);
		mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();

	}
}
