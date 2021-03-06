package com.finance.activiti.listener;

import org.activiti.bpmn.constants.BpmnXMLConstants;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WorkflowTaskCreateListener {

    @SuppressWarnings("unchecked")
    @Transactional
    public void onEvent(ActivitiEvent event) {
        ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
        TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
        //没有参数直接返回
        if (taskEntity.getVariables() == null) {
            return;
        }
        //判断assignee
        if (taskEntity.getVariable(BpmnXMLConstants.ATTRIBUTE_TASK_USER_ASSIGNEE) != null) {
            taskEntity.setAssignee(taskEntity.getVariable(BpmnXMLConstants.ATTRIBUTE_TASK_USER_ASSIGNEE).toString());
        }
        //判断users
        if (taskEntity.getVariable(BpmnXMLConstants.ATTRIBUTE_TASK_USER_CANDIDATEUSERS) != null) {
            taskEntity.addCandidateUsers((List<String>) taskEntity.getVariable(BpmnXMLConstants.ATTRIBUTE_TASK_USER_CANDIDATEUSERS));
        }
        //判断groups
        if (taskEntity.getVariable(BpmnXMLConstants.ATTRIBUTE_TASK_USER_CANDIDATEGROUPS) != null) {
            taskEntity.addCandidateGroups((List<String>) taskEntity.getVariable(BpmnXMLConstants.ATTRIBUTE_TASK_USER_CANDIDATEGROUPS));
        }
    }
}
