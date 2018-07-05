package wangli.activity.demo;

import org.activiti.engine.*;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class HelloWorld {


    /**
     * 创建日志答应对象信息
     */
    private static Log log = LogFactory.getLog(HelloWorld.class);

    /**
     * 创建工作流引擎实例
     */
    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 流程部署信息
     */
    @Test
    public void deplyment(){
       Deployment deployment =  processEngine.getRepositoryService()
                    .createDeployment()
                    .name("hello")
                    .addClasspathResource("diagrams/helloworld/helloworldprocess.bpmn")
                    .addClasspathResource("diagrams/helloworld/helloworldprocess.png")
                    .deploy();
        log.info("流程部署id："+deployment.getId());
        log.info("流程部署名称："+deployment.getName());

    }

    /**
     * 启动流程实例
     */
    @Test
    public void startProcess(){

        /**
         * 使用流程实例启动流程  key只的是流程实例id
         */
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("请假天数",3);
        map.put("请假原因","回家探亲");
        String key = "helloworldprocess";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                    //.startProcessInstanceById(key);
                    //.startProcessInstanceByKey(key);
                      .startProcessInstanceByKey(key,map);
        log.info("流程实例id:"+key);
        log.info("流程定义Id:"+processInstance.getId());
    }

    /**
     * 查看流程实例信息 --根据执行人的姓名查询到制定的信息
     */
    @Test
    public void findByName(){
        String taskName = "王五";
        List<Task> pd = processEngine.getTaskService()
                .createTaskQuery()
                //.taskCandidateOrAssigned(taskName)
                .list();
        for(Task t:pd){
            log.info("任务ID："+t.getId());
            log.info("任务执行ID:"+t.getExecutionId());
            log.info("任务名称:"+t.getName());
            log.info("流程创建时间："+t.getCreateTime());
            log.info("流程实例ID："+t.getProcessInstanceId());
            log.info("流程定义ID："+t.getProcessDefinitionId());
            log.info("==============================end");
        }

    }

    /**
     * 完成任务实例
     */
    @Test
    public void completeTask(){
        String taskId = "7502";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务流程的实例Id信息:"+taskId);
    }

    /**
     * 删除流程定义信息
     */
    @Test
    public void deleteProcessDefined(){
        //根据流程实例Id删除对应的信息
        String pid = "";
        processEngine.getRepositoryService()
                    .deleteDeployment(pid,true);  //true表示级联删除 cascade

    }

    /**
     * 查看流程变量信息
     */
    @Test
    public void getVariable(){
        TaskService taskService = processEngine.getTaskService();

        Map<String,Object> map = taskService.getVariables("5002");

        System.out.println(map.toString());
    }

}
