package wangli.activity.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * @Auther: Wang Li
 * @Date: 2018/5/26 08:07
 * @Description:
 */
public class HelloWoldProcess {

    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void deployment(){
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("helloworld20")
                .addClasspathResource("diagrams/myactiviti.png")
                .addClasspathResource("diagrams/myactiviti.bpmn")
                .deploy();
        System.out.println("流程Id:"+deployment.getId());
        System.out.println("流程name:"+deployment.getName());
    }

    @Test
    public void startProcess(){
        String key = "helloworld";
        processEngine.getRuntimeService()
                .startProcessInstanceByKey(key);

    }
}
