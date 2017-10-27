package com.smallkk.workflow.service;

import java.util.List;
import java.util.Map;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2017/10/26 22:32
 */

public interface ReAttendService {

    void startReAttendFlow(Map varibles);

    List<org.activiti.engine.task.Task> listTasks(Map varibles);

    void approve(String taskId);
}
