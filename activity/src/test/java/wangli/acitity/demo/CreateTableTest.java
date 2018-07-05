package wangli.acitity.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * 通过流程引擎调用其自动创建表信息
 */
public class CreateTableTest {

    private Log log = LogFactory.getLog(CreateTableTest.class);

    @Test
    public void createTableCode(){

        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("oracle.jdbc.OracleDriver");
        processEngineConfiguration.setJdbcUrl("jdbc:oracle:thin:@192.168.50.39:1521/orcl");
        processEngineConfiguration.setJdbcUsername("cbs");
        processEngineConfiguration.setJdbcPassword("cbs123");
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

        log.info("创建流程实例成功"+processEngine.toString());

    }

    /**使用配置文件创建工作流需要的23张表*/
    @Test
    public void createTable_2(){
//		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//		//工作流的核心对象，ProcessEnginee对象
//		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")	//
                .buildProcessEngine();
        System.out.println("processEngine:"+processEngine);
    }
}
