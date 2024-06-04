package com.yuqn.utils;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class ActivitiCache {

    private final static String SUCCESS = "成功";
    private final static String FAIL = "失败";

    /**
     * 因为使用了整合框架，所以可以直接使用注入的方式来使用相关的类
     */
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    /**
     * @author: yuqn
     * @Date: 2024/6/2 18:25
     * @description:
     * 统一日志打印
     * @param: taskName：任务名字
     * @param: summary：任务摘要
     * @param: status：任务状态
     * @param: mes：日志信息
     * @return: null
     */
    public void logInfo(String taskName,String summary,String status,String mes){
        log.info("Activiti 日志打印开始：");
        log.info("任务名字：" + taskName);
        log.info("日志摘要：" + summary);
        log.info("任务状态：" + status);
        log.info("日志信息：" + mes);
        log.info("Activiti 日志打印结束！");
    }
    public void logError(String taskName,String summary,String status,String mes){
        log.error("Activiti 日志打印开始：");
        log.error("任务名字：" + taskName);
        log.error("日志摘要：" + summary);
        log.error("任务状态：" + status);
        log.error("日志信息：" + mes);
        log.error("Activiti 日志打印结束！");
    }

    /**
     * @author: yuqn
     * @Date: 2024/4/29 17:22
     * @description:
     * 启动流程实例
     * act_hi_actinst 流程实例执行历史
     * act_hi_identitylink 流程的参与用户历史信息
     * act_hi_procinst 流程实例历史信息
     * act_hi_taskinst 流程任务历史信息
     * act_ru_execution 流程正在执行信息
     * act_ru_identitylink 流程的参与用户信息
     * act_ru_task 任务信息
     * @param: null
     * @return: null
     */
    public ProcessInstance startProcess(String proc_key){
        ProcessInstance instance = null;
        try {
            // 根据流程定义的id启动流程
            instance = runtimeService.startProcessInstanceByKey(proc_key);
            String mes = "流程定义id：" + instance.getProcessDefinitionId() + " " + "流程实例id：" + instance.getId() + " " + "当前活动的id：" + instance.getActivityId();
            logInfo(instance.getName(), "启动任务",SUCCESS, mes);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            logError(proc_key,"启动失败",FAIL,e.getMessage());
        }
        return instance;
    }

    /**
     * @author: yuqn
     * @Date: 2024/6/2 17:05
     * @description:
     * 根据任务id完成对应的流程实例
     * @param: 任务id
     * @return: null
     */
    public boolean completTask(String taskId){
        // 根据任务id完成对应的流程实例
        try {
            taskService.complete(taskId);
            logInfo(taskId,"完成任务",SUCCESS,"根据id完成任务完成");
            return true;
        }catch (Exception e){
            logError(taskId,"完成任务失败",FAIL,e.getMessage());
            return false;
        }
    }

    /**
     * @author: yuqn
     * @Date: 2024/6/2 16:58
     * @description:
     * 完成个人任务
     * @param: 流程id，完成人
     * @return: null
     */
    public boolean completTask(String proc_key,String assignee){
        // 根据key和完成人获取对应的流程实例
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(proc_key)
                .taskAssignee(assignee)
                .singleResult();
        if (Objects.nonNull(task)){
            // 完成jerry的任务
            taskService.complete(task.getId());
            logInfo(task.getName(),"完成个人任务",SUCCESS,"任务完成");
            log.info("任务：" + task.getId() + " 状态：已完成");
            return true;
        }else {
            log.info("找不到"+ proc_key + "->" + assignee + " 对应的任务");
            return false;
        }
    }

    /**
     * @author: yuqn
     * @Date: 2024/5/5 23:41
     * @description:
     * 删除流程部署信息
     * @param: null
     * @return: null
     * 当前流程如果没有全部完成，想要删除的话需要使用特殊方式，原理就是 级联删除
     */
    public boolean deleteDeployMentCascade(String deploymentId){
        try {
            // 级联删除，流程没有完全完成也能删除
            repositoryService.deleteDeployment(deploymentId,true);
            logInfo(deploymentId,"删除任务",SUCCESS,"联级删除任务成功");
            return true;
        }catch (Exception e){
            logError(deploymentId,"删除任务",FAIL,e.getMessage());
            return false;
        }

    }

    /**
     * @author: yuqn
     * @Date: 2024/5/5 23:41
     * @description:
     * 删除流程部署信息
     * @param: null
     * @return: null
     * 当前流程如果没有全部完成，想要删除的话需要使用特殊方式，原理就是 级联删除
     */
    public void deleteDeployMentNoCascade(String deploymentId){
        // 非级联删除
        repositoryService.deleteDeployment(deploymentId);
    }

}
