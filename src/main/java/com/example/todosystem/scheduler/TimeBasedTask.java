package com.example.todosystem.scheduler;

import com.example.todosystem.pojo.Task;
import com.example.todosystem.pojo.User;
import com.example.todosystem.service.UserService;
import com.example.todosystem.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import com.example.todosystem.service.TaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class TimeBasedTask {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    private final EmailUtil emailUtil = new EmailUtil();

    @Scheduled(fixedRate = 30000) // 每分钟检查一次
    public void checkDatabaseTime() {

        LocalDateTime now = LocalDateTime.now();

        String output = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("当前时间：" + output);



        taskService.getAllTaskList().forEach(task -> {

            String taskTime =LocalDateTime.parse(task.getTasktime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

//            System.out.println("任务时间：" + taskTime);
            if (taskTime.equals(output)) {
                performActionIfTimeMatches(task);
            }
        });

    }

    private void performActionIfTimeMatches(Task task) {


        User user = userService.getUserById(task.getUserid());

        String toEmail = user.getEmail(); // 获取任务关联的用户邮箱

        System.out.println("发送邮件给：" + toEmail);

        String subject = "ToDoSystem 提醒邮件"; // 邮件主题
        String body = "Your task '" + task.getTitle() + "' has been executed."; // 邮件正文

        // 使用 EmailUtil 发送邮件
        emailUtil.sendEmail(toEmail, subject, body);

    }
}