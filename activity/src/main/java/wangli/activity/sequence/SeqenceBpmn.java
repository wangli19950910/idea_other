package wangli.activity.sequence;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lenovo
 * @Date: 2018/6/7 19:25
 * @Description:
 */
public class SeqenceBpmn {

    /**
     * 创建日志答应对象信息
     */
    private static Log log = LogFactory.getLog(SeqenceBpmn.class);
    /**
     * 创建工作流引擎实例
     */
    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void deplyment(){


        InputStream inputStreambpmn = this.getClass().getClassLoader().getResourceAsStream("diagrams/sequenceFlow/sequenceFlow.bpmn");
        InputStream inputStreampng = this.getClass().getClassLoader().getResourceAsStream("diagrams/sequenceFlow/sequenceFlow.png");


        Deployment deployment =  processEngine.getRepositoryService()
                .createDeployment()
                .name("连线")
                .addInputStream("sequenceFlow.bpmn",inputStreambpmn)
                .addInputStream("sequenceFlow.png",inputStreampng)
                .deploy();
        log.info("流程部署id："+deployment.getId());
        log.info("流程部署名称："+deployment.getName());
    }

    @Test
    public void startProcess(){

        String key = "sequenceFlow";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(key);
        log.info("流程实例id:"+key);
        log.info("流程定义Id:"+processInstance.getId());
    }
    @Test
    public void findByName(){
        String taskName = "赵六";
        List<Task> pd = processEngine.getTaskService()
                .createTaskQuery()
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
    @Test
    public void completeTask(){
        Map<String,Object> map = new HashMap<String, Object>();
        String taskId = "12503";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务流程的实例Id信息:"+taskId);
    }
}
