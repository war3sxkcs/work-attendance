package com.smallkk.workflow.service;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/26 22:38
 */
@Service("reAttendServiceImpl")
public class ReAttendServiceImpl implements ReAttendService {

    private static final java.lang.String RE_ATTEND_FLOW_ID = "re_attend";
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public void startReAttendFlow(Map varibles) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(RE_ATTEND_FLOW_ID, varibles);
        System.out.println(instance.getId() + "-----" + instance.getActivityId());
        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println(task.getId() + "--" + task.getName());
        Map<String, Object> map = new HashMap();
        map.put("attend_moring", "09:00");
        map.put("attend_evening", "18:30");
        taskService.complete(task.getId(), map);
    }

    @Override
    public List<org.activiti.engine.task.Task> listTasks(Map varibles) {
        List<Task> taskList = taskService.createTaskQuery().taskDefinitionKey("re_attend_approve").active().list();
        Map<String, Object> param = taskService.getVariables(taskList.get(0).getId());
        return taskList;
    }

    @Override
    public void approve(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        taskService.complete(taskId);
    }
}
